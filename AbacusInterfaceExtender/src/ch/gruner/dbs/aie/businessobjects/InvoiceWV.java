package ch.gruner.dbs.aie.businessobjects;

import java.util.HashMap;
import java.util.List;

public class InvoiceWV {

	public InvoiceWV(List<WVImportBooking> importBookings) {
		this.importBookings = importBookings;
	}

	private List<WVImportBooking> importBookings;
	private Debitorenadresse adresse;
	private String projektReferenz;
	private Integer projektNummer;
	private HashMap<Integer, HashMap<String, Double>> lineItems;

	public List<WVImportBooking> getImportBookings() {
		return importBookings;
	}

	public void setImportBookings(List<WVImportBooking> importBookings) {
		this.importBookings = importBookings;
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
		if (importBookings != null) {
			if (lineItems == null) {
				lineItems = new HashMap<>();
			}
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
		return lineItems;
	}

	public void setLineItems(HashMap<Integer, HashMap<String, Double>> lineItems) {
		this.lineItems = lineItems;
	}

}