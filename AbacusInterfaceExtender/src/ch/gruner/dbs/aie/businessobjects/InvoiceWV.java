package ch.gruner.dbs.aie.businessobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvoiceWV {

	public InvoiceWV(List<WVImportBooking> importBookings) {
		this.importBookings = importBookings;
		init();
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

	

}