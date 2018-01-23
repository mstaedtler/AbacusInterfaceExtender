package ch.gruner.dbs.aie.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFileChooser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.gruner.dbs.aie.businessobjects.InvoiceWV;
import ch.gruner.dbs.aie.businessobjects.WVImportBooking;
import ch.gruner.dbs.aie.gui.MainFrame;
import ch.gruner.dbs.aie.gui.MainFrame.ActionCommands;
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
		
		if(e.getActionCommand().equals(ActionCommands.SELECT_PATH.toString())){
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File("input/vmAccounting_20170930.csv"));
			int fcReturnValue = fc.showOpenDialog(MainFrame.frame);
			if (fcReturnValue == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            MainFrame.getjTextFieldPath().setText(file.getPath());
	            LOG.info("Datei ausgewählt: " + file.getPath());
	            fillMietWVPreviewTable(file);
			}else {
				LOG.info("File Chooser canceled by user.");
			}
		}else if(e.getActionCommand().equals(ActionCommands.EXPORT.toString())){
			LOG.info("Export wird gestartet.");
			if(tableModelInvoiceWV != null && tableModelInvoiceWV.getRowCount() > 0) {
				Exporter exporter = new Exporter();
				for (InvoiceWV inv : tableModelInvoiceWV.getAllTableObjects()) {
					exporter.createMietWVPdf(inv);
				};
			}else {
				//TODO Message das keine Daten exportiert werden können.
			}
			
		}else if(e.getActionCommand().equals(ActionCommands.CLOSE.toString())) {
			MainFrame.frame.dispose();
			LOG.info("Programm wurde geschlossen.");
		}
		
	}
	
	private void fillMietWVPreviewTable(File inputFile) {
		//"input/vmAccounting_20170930.csv"
		CSVReader csvReader = new CSVReader();
		java.util.List<WVImportBooking> list = csvReader.readMietCSV(inputFile.getAbsolutePath());

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
		tableModelInvoiceWV.resetModel();
		for (Integer gbNummer : bookingByGb.keySet()) {
			InvoiceWV invoice = new InvoiceWV(bookingByGb.get(gbNummer), gbNummer);
			tableModelInvoiceWV.addObjectToTable(invoice);
//			LOG.info("Total Betrag ohne Mwst.: " 	+ 	invoice.getTotalInvoiceAmount());
//			LOG.info("Total Betrag Mwst.: " 		+ 	invoice.getMwstBetrag());
//			LOG.info("Total Betrag inkl Mwst.: " 	+ 	invoice.getTotalInvoiceAmountInclMwst());
		}
//		return tableModelInvoiceWV;
	}

}
