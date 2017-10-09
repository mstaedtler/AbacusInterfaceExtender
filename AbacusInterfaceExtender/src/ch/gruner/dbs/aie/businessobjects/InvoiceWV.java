package ch.gruner.dbs.aie.businessobjects;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

public class InvoiceWV {
	
	public InvoiceWV(List<WVImportBooking> importBookings) {
		this.importBookings = importBookings;
	}
	
	private List<WVImportBooking> importBookings;
	private Debitorenadresse adresse;
	private String projektReferenz;
	private Integer projektNummer;
	private HashMap<Integer, HashMap<String, Double>> lineItems;
	
	private void test() {
		
	}
	 
	
}
