package ch.gruner.dbs.aie.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.gruner.dbs.aie.businessobjects.InvoiceWV;
import ch.gruner.dbs.aie.businessobjects.WVImportBooking;
import ch.gruner.dbs.aie.gui.MainFrame;
import ch.gruner.dbs.aie.model.TableModelInvoicesWV;

public class ButtonActions implements ActionListener{

	private TableModelInvoicesWV tableModelInvoiceWV;
	private static Logger LOG = LogManager.getLogger(ButtonActions.class);
	public ButtonActions() {}
	
	public ButtonActions(TableModelInvoicesWV tableModelInvoiceWV){
		this.tableModelInvoiceWV = tableModelInvoiceWV;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		CSVReader csvReader = new CSVReader();
		java.util.List<WVImportBooking> list = csvReader.readMietCSV("input/vmAccounting_20170930.csv");

		// Aufteilen GB's nach GB Nummer
		HashMap<Integer, java.util.List<WVImportBooking>> bookingByGb = new HashMap<>();
		for (WVImportBooking wvBooking : list) {
			Integer gb = wvBooking.getGb();
			java.util.List<WVImportBooking> innerList;
			// Testen ob innerList null und neue anlegen. Sonst innerList holen.
			if (bookingByGb.get(gb) == null) {
				innerList = new ArrayList<>();
			} else {
				innerList = bookingByGb.get(gb);
			}
			innerList.add(wvBooking);
			bookingByGb.put(gb, innerList);
		}
		
		for (Integer gbNummer : bookingByGb.keySet()) {
			InvoiceWV invoice = new InvoiceWV(bookingByGb.get(gbNummer), gbNummer);
			tableModelInvoiceWV.addObjectToTable(invoice);
			LOG.info("Total Betrag ohne Mwst.: " 	+ 	invoice.getTotalInvoiceAmount());
			LOG.info("Total Betrag Mwst.: " 		+ 	invoice.getMwstBetrag());
			LOG.info("Total Betrag inkl Mwst.: " 	+ 	invoice.getTotalInvoiceAmountInclMwst());
		}
		
	}

}
