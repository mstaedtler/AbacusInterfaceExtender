package ch.gruner.dbs.aie.controller;

import java.io.File;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.gruner.dbs.aie.businessobjects.InvoiceWV;
import ch.gruner.dbs.aie.businessobjects.WVImportBooking;
import ch.gruner.dbs.aie.gui.Main;
import ch.gruner.dbs.aie.xmlexport.fibu.AbaConnectContainer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class MainSceneController {

	private static Logger LOG = LogManager.getLogger(MainSceneController.class);
	
	// Reference to the main application.
    private Main mainApp;
    private ObservableList<InvoiceWV> invoiceData = FXCollections.observableArrayList();
    private File importFileMWV;
    private File importFileDatevFibu;
    private boolean datevFibuPreview = true;
	
    /*Field Definition for MWV Tab*/
    
	@FXML TextField txtFieldPath;
	@FXML TextField txtFieldKurs;
	@FXML TextField txtFieldRgNr;
	@FXML DatePicker datePicker;
	
	/*Field Definition for DATEV FIBU Tab*/
	@FXML TextField txtImportPathDATEVFibu;
	@FXML TextField txtFieldKursDATEVFibu;
	@FXML TextArea txtAreaDATEVFibu;
	@FXML TextField txtFieldTextDATEVFibu;
	@FXML DatePicker datePickerDATEVFibu;
	
	
	/*Table Definition for MWV Tab*/
	
	@FXML
    public TableView<InvoiceWV> invoiceTable;
    @FXML
    private TableColumn<InvoiceWV, Integer> gbColumn;
    @FXML
    private TableColumn<InvoiceWV, String> firmaColumn;
    @FXML
    private TableColumn<InvoiceWV, String> whrColumn;
    @FXML
    private TableColumn<InvoiceWV, Double> betragColumn;
    @FXML
    private TableColumn<InvoiceWV, Double> mwstSatzColumn;
    @FXML
    private TableColumn<InvoiceWV, Double> mwstBetragColumn;
    @FXML
    private TableColumn<InvoiceWV, Double> betragAllInclColumn;

	
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	
    	/*Init for MWV Tab*/
    	gbColumn.setCellValueFactory(cellData -> cellData.getValue().gbNrProperty().asObject());
    	firmaColumn.setCellValueFactory(cellData -> cellData.getValue().firmaProperty());
    	whrColumn.setCellValueFactory(cellData -> cellData.getValue().währungProperty());
    	betragColumn.setCellValueFactory(cellData -> cellData.getValue().totalInvoiceAmountProperty().asObject());
    	mwstSatzColumn.setCellValueFactory(cellData -> cellData.getValue().mwstSatzProperty().asObject());
    	mwstBetragColumn.setCellValueFactory(cellData -> cellData.getValue().mwstBetragProperty().asObject());
    	betragAllInclColumn.setCellValueFactory(cellData -> cellData.getValue().totalInvoiceAmountInclMwstProperty().asObject());
    	datePicker.setValue(LocalDate.now());  	
    	/*Init for DATEV FIBU Tab*/
    	datePickerDATEVFibu.setValue(LocalDate.now());
    	txtFieldKursDATEVFibu.setText("1.2036801");
    	txtFieldTextDATEVFibu.setText("Abschluss");
    }
    
    @FXML 
	protected void eventMWVExport(ActionEvent event) {
    	
    	Exporter exporter = new Exporter();
		
    	File fileXML = new File("output/XML_MietWV_WP4CAD_12.xml");
		File filePDF = new File("output/Rechnung_MietWV_WP4CAD_12.pdf");
		exporter.createXML();
		exporter.createZipFile(filePDF, fileXML);
		
		LOG.info("Export wird gestartet.");
		
		
		if(invoiceData != null && invoiceData.size() > 0) {
			//Exporter exporter = new Exporter();
			for (InvoiceWV inv : invoiceData) {
				exporter.createMietWVPdf(inv);
			};
		}else {
			LOG.info("Tabelle leer. Kein Input File vorhanden");
		}
    }
    
    @FXML 
   	protected void eventClose(ActionEvent event) {
    	Platform.exit();
        System.exit(0);
    }
    
	@FXML 
	protected void eventMWVSelectPath(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.setInitialDirectory(new File("input"));
		importFileMWV = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
		if(importFileMWV != null) {
			txtFieldPath.setText(importFileMWV.getAbsolutePath());
			LOG.info("Datei ausgewählt: " + importFileMWV.getPath());
            fillMietWVPreviewTable(importFileMWV);
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

//		invoiceData.clear();
		Integer invNumberCounter;
		if(!txtFieldRgNr.getText().isEmpty()) {
			invNumberCounter = Integer.parseInt(txtFieldRgNr.getText());
		}else {
			invNumberCounter = 0;
		}
		Double kurs = 1.0d;
		if(!txtFieldRgNr.getText().isEmpty()) {
			kurs = Double.parseDouble(txtFieldKurs.getText());
		}
		
		for (Integer gbNummer : bookingByGb.keySet()) {
			InvoiceWV invoice = new InvoiceWV(bookingByGb.get(gbNummer), gbNummer, invNumberCounter, kurs, datePicker.getValue());
			invoiceData.add(invoice);
			invNumberCounter++;
		}
	
	
	}
	
	/**
	 * 
	 **/
	@FXML
	public void eventSelectPathDATEVFibu() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.setInitialDirectory(new File("input"));
		importFileDatevFibu = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
		if(importFileDatevFibu != null) {
			txtImportPathDATEVFibu.setText(importFileDatevFibu.getAbsolutePath());
			LOG.info("Datei ausgewählt: " + importFileDatevFibu.getPath());
		}
	}
	
	/**
	 * 
	 **/
	@FXML
	public void eventCreatPreviewDATEVFibu() {
		CSVReader csvReader = new CSVReader();
		String buchungsText = txtFieldTextDATEVFibu.getText();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String buchungsDatum = datePickerDATEVFibu.getValue().format(dtf);
		Double kurs = Double.parseDouble(txtFieldKursDATEVFibu.getText());

		AbaConnectContainer abaConnectContainer = csvReader.readDatevDiffBuchungen(importFileDatevFibu.getAbsolutePath(), buchungsText, buchungsDatum, kurs);
		txtAreaDATEVFibu.setText("");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(AbaConnectContainer.class);
//			StringWriter writer = new StringWriter();
//		    writer.append("<?xml version='1.0' encoding='UTF-8'?>");
			
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			jaxbMarshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml version='1.0' encoding='UTF-8'?>");
			
			if(!datevFibuPreview) {
				File file = new File("output/DATEV_FIBU.xml");
				jaxbMarshaller.marshal(abaConnectContainer, file);
			}
			
			
//			jaxbMarshaller.marshal(abaConnectContainer, System.out);
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(abaConnectContainer, sw);
			txtAreaDATEVFibu.appendText(sw.toString());


			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 **/
	@FXML
	public void eventExportDATEVFibu() {
		datevFibuPreview = false;
		eventCreatPreviewDATEVFibu();
		datevFibuPreview = true;
	}

	
	public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        invoiceTable.setItems(invoiceData);
       
        //personTable.setItems(mainApp.getPersonData());
    }
	
	/**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<InvoiceWV> getInvoiceData() {
        return invoiceData;
    }

	/**
	 * @return the importFileMWV
	 */
	public File getImportFileMWV() {
		return importFileMWV;
	}

	/**
	 * @return the importFileDatevFibu
	 */
	public File getImportFileDatevFibu() {
		return importFileDatevFibu;
	}
    
    
	
}
