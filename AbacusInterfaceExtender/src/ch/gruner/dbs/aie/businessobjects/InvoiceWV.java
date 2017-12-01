package ch.gruner.dbs.aie.businessobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvoiceWV {

	public enum Währung{
		CHF, EUR
	}
	
	public InvoiceWV(List<WVImportBooking> importBookings, Integer gbNummer) {
		this.importBookings = importBookings;
		this.gbNummer = gbNummer;
		init();
		initStammdaten();
	}

	private List<WVImportBooking> importBookings;
	private Debitorenadresse adresse;
	private String projektReferenz;
	private Integer projektNummer;
	private HashMap<Integer, HashMap<String, Double>> lineItems;
	private List<DetailsByCostCenter> bookingDetailsByCostCenter;
	private List<DetailsByProfile> bookingDetailsByProfile;
	private Double totalInvoiceAmount = 0.0d;
	private Double mwstSatz = 8.0d;
	private Double mwstBetrag;
	private Double totalInvoiceAmountInclMwst;
	private Währung währung;
	private Double umrechnungskurs;
	
	private Integer gbNummer;
	private Integer invoiceNumber;
	private boolean isExport = false;
	
	private String mwstNummer;
	private String zahlungskonditionen;
	private String postkontoNr;
	private String bankname;
	private String iban;
	
	private Integer kontoNrSoll;
	private Integer kontoNrHaben;
	
	
	private void initStammdaten() {
		
		//Gemeinsamkeiten setzen
		projektNummer 			= 195903001; //TODO aus Properties holen
		projektReferenz 		= "DBS"; //TODO aus Properties holen
		mwstNummer 				= "CHE-105.934.399 MWST";
		zahlungskonditionen 	= "Zahlungskonditionen: 30 Tage netto";
		postkontoNr 			= "PC 40-16339-7";
		bankname 				= "Basler Kantonalbank, 4002 Basel";
		iban 					= "CH83 0077 0016 0509 2614 1";
		umrechnungskurs 		= 1.0d;
		währung 				= Währung.CHF;
		
		
		
		//Adressnummer und Währung Abhängig vom GB setzen
		//TODO Währung und Kurs hinzufügen und bei Betragsberechnungen berücksichtigen
		//TODO Mwst. dynamisch setzen
		switch (gbNummer) {
		case 11:
			adresse = new Debitorenadresse("Gruner AG", "Gellertstrasse 55", "4020 Basel");
			break;
		case 12:
			adresse = new Debitorenadresse("Gruner Böhringer AG", "Mühlegasse 10", "4104 Oberwil");
			break;
		case 13:
			adresse = new Debitorenadresse("Gruner Lüem AG", "St. Jakobs-Strasse 199", "4020 Basel");
			break;
		case 15:
			adresse = new Debitorenadresse("Gruner Wepf AG, Zürich", "Thurgauerstrasse 56", "8050 Zürich");
			break;
		case 20:
			adresse = new Debitorenadresse("Gruner Berchtold Eicher AG", "Chamerstrasse 170", "6300 Zug");
			break;
		case 22:
			adresse = new Debitorenadresse("Gruner Roschi AG", "Sägestrasse 73", "3098 Köniz");
			break;
		case 23:
			adresse = new Debitorenadresse("Gruner Wepf AG, St. Gallen", "Oberstrasse 153", "9000 St. Gallen");
			break;
		case 24:
			adresse = new Debitorenadresse("Gruner Gruneko AG", "St. Jakobs-Strasse 199", "4020 Basel");
			break;
		case 27:
			adresse = new Debitorenadresse("Stucky SA", "Rue du Lac 33", "1020 Renens");	
			break;
		case 80:
			adresse = new Debitorenadresse("Gruner GmbH, Wien", "Otto-Bauer-Gasse 6/10", "AT-1060 Wien");
			währung = Währung.EUR;
			umrechnungskurs = 1.15d;
			mwstNummer = "DE180945107";
			break;
		case 81:
			adresse = new Debitorenadresse("Gruner GmbH", "Dufourstrasse 28", "DE-04107 Leipzig");
			währung = Währung.EUR;
			umrechnungskurs = 1.15d;
			mwstNummer = "DE180945107";
			break;
		case 83:
			adresse = new Debitorenadresse("Gruner GmbH, Stuttgart", "Zettachring 8", "DE-70567 Stuttgart");
			währung = Währung.EUR;
			umrechnungskurs = 1.15d;
			mwstNummer = "DE180945107";
			break;
		case 92:
			adresse = new Debitorenadresse("Gruner GmbH, Köln", "Kaiser-Wilhelm-Ring 27-29", "DE-50672 Köln");
			währung = Währung.EUR;
			umrechnungskurs = 1.15d;
			mwstNummer = "DE180945107";
			break;
		case 93:
			adresse = new Debitorenadresse("Gruner GmbH, Hamburg", "Raboisen 16", "DE-20095 Hamburg");
			währung = Währung.EUR;
			umrechnungskurs = 1.15d;
			mwstNummer = "DE180945107";
			break;
		default:
			break;
		}
	}
	
	private void init() {
		
		if (lineItems == null) {
			lineItems = new HashMap<>();
			if (importBookings != null) {
				HashMap<String, Double> innerMap;
				for (WVImportBooking wvImportBooking : importBookings) {
					Integer costCenter = wvImportBooking.getCostCenter();
					String profile = wvImportBooking.getProfile();
					innerMap = lineItems.get(costCenter);
					if (innerMap == null) {
						innerMap = new HashMap<>();
					}
					Double oldAmount = innerMap.get(profile);
					if (oldAmount == null) {
						oldAmount = 0.0d;
					}
					Double newAmount = oldAmount + wvImportBooking.getCost();
					innerMap.put(profile, newAmount);
					lineItems.put(costCenter, innerMap);
				}
	
			}
		initDetails();
		}
	}
	
	private void initDetails() {
		
		//Bookings by CostCenter aufbereiten
		if(bookingDetailsByCostCenter == null) {
			bookingDetailsByCostCenter = new ArrayList<DetailsByCostCenter>();
		}
		for (Integer costCenter : lineItems.keySet()) {
			DetailsByCostCenter detByCC = new DetailsByCostCenter();
			detByCC.setCostCenter(costCenter);
			HashMap<String, Double> innerMap = lineItems.get(costCenter);
			Double amount = 0.0d;
			for (String profile : innerMap.keySet()) {
				amount = amount + innerMap.get(profile);
				//detByCC.addAmount(innerMap.get(profile));
			}
			detByCC.setAmount(amount);
			bookingDetailsByCostCenter.add(detByCC);
		}
		
		//Bookings by Profile aufbereiten
		if(bookingDetailsByProfile == null) {
			bookingDetailsByProfile = new ArrayList<DetailsByProfile>();
		}
		
		HashMap<String, Double> mapByProfile = new HashMap<>();
		
		for (HashMap<String, Double> innerMap : lineItems.values()) {
			for (String profile : innerMap.keySet()) {
				Double amount = mapByProfile.get(profile);
				if(amount == null) {
					amount = 0.0d;
				}
				amount = amount + innerMap.get(profile); 
				mapByProfile.put(profile, amount);
			}
		}
		
		for (String profile : mapByProfile.keySet()) {
			DetailsByProfile detByProfile = new DetailsByProfile();
			detByProfile.setAmount(mapByProfile.get(profile));
			detByProfile.setProfile(profile);
			totalInvoiceAmount = totalInvoiceAmount + mapByProfile.get(profile);
			bookingDetailsByProfile.add(detByProfile);
		}
	}
	
	
	public List<WVImportBooking> getImportBookings() {
		return importBookings;
	}

	public Debitorenadresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Debitorenadresse adresse) {
		this.adresse = adresse;
	}

	public String getProjektReferenz() {
		return projektReferenz;
	}

	public void setProjektReferenz(String projektReferenz) {
		this.projektReferenz = projektReferenz;
	}

	public Integer getProjektNummer() {
		return projektNummer;
	}

	public void setProjektNummer(Integer projektNummer) {
		this.projektNummer = projektNummer;
	}

	public HashMap<Integer, HashMap<String, Double>> getLineItems() {
		return lineItems;
	}

	public List<DetailsByCostCenter> getBookingDetailsByCostCenter() {
		return bookingDetailsByCostCenter;
	}

	public List<DetailsByProfile> getBookingDetailsByProfile() {
		return bookingDetailsByProfile;
	}

	public Double getTotalInvoiceAmount() {
		return totalInvoiceAmount;
	}

	public Double getMwstSatz() {
		return mwstSatz;
	}

	public void setMwstSatz(Double mwst) {
		this.mwstSatz = mwst;
	}
	
	public Double getMwstBetrag() {
		mwstBetrag = (getTotalInvoiceAmount() * getMwstSatz())/100;
		return mwstBetrag;
	}

	public Double getTotalInvoiceAmountInclMwst() {
		totalInvoiceAmountInclMwst = getTotalInvoiceAmount()+((getTotalInvoiceAmount() * getMwstSatz())/100);
		return totalInvoiceAmountInclMwst;
	}

	public boolean isExport() {
		return isExport;
	}

	public void setExport(boolean isExport) {
		this.isExport = isExport;
		
	}

	public Integer getGbNummer() {
		return gbNummer;
	}

	public void setGbNummer(Integer gbNummer) {
		this.gbNummer = gbNummer;
	}

	public Integer getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(Integer invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	

	

}