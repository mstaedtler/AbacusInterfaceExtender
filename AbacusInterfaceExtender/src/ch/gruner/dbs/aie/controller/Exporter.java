package ch.gruner.dbs.aie.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ch.gruner.dbs.aie.businessobjects.DetailsByCostCenter;
import ch.gruner.dbs.aie.businessobjects.DetailsByProfile;
import ch.gruner.dbs.aie.businessobjects.InvoiceWV;
import ch.gruner.dbs.aie.xmlexport.debi.AbaConnectContainer;
import ch.gruner.dbs.aie.xmlexport.debi.Document;
import ch.gruner.dbs.aie.xmlexport.debi.Parameter;
import ch.gruner.dbs.aie.xmlexport.debi.Task;
import ch.gruner.dbs.aie.xmlexport.debi.Transaction;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Exporter {


	public void createMietWVPdf(InvoiceWV invoice) {
		
		try {
            /* Output file location */
            String outputFile = "output/Rechnung_MietWV_WP4CAD_"+invoice.getGbNummer()+".pdf"; 

            /* List to hold Items */
            List<DetailsByProfile> listItemsProfile = invoice.getBookingDetailsByProfile();
            List<DetailsByCostCenter> listItemsCostCenter = invoice.getBookingDetailsByCostCenter();

            /* Convert List to JRBeanCollectionDataSource */
            JRBeanCollectionDataSource itemsJRBeanProfiles = new JRBeanCollectionDataSource(listItemsProfile);
            JRBeanCollectionDataSource itemsJRBeanCostCenter = new JRBeanCollectionDataSource(listItemsCostCenter);
         
            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("pMWVDetailsByProfile", 			itemsJRBeanProfiles);
            parameters.put("pMWVDetailsByCostCenter", 		itemsJRBeanCostCenter);
            parameters.put("pMWVAdresseName", 	 			invoice.getAdresse().getName());
            parameters.put("pMWVAdresseStrasse", 			invoice.getAdresse().getAnschrift());
            parameters.put("pMWVAdresseOrt", 	 			invoice.getAdresse().getOrt());
            
            parameters.put("pMWVIban", 	 					invoice.getIban());
            parameters.put("pMWVBankname", 	 				invoice.getBankname());
            parameters.put("pMWVPostkontoNr", 	 			invoice.getPostkontoNr());
            
            parameters.put("pMWVZahlungskonditionen",		invoice.getZahlungskonditionen());
            parameters.put("pMWVMwstNummer", 	 			invoice.getMwstNummer());
            parameters.put("pMWVMwstSatz", 	 				invoice.getMwstSatz());
            parameters.put("pMWVWaehrung",	 	 			invoice.getWährung().toString());
            parameters.put("pMWVProjektReferenz", 	 		invoice.getProjektReferenz());
            parameters.put("pMWVProjektNummer", 	 		invoice.getProjektNummer());
            
            parameters.put("pMWVRechnungsNummer", 	 		invoice.getInvoiceNumber());
            parameters.put("pMWVRechnungsPeriode", 	 		invoice.getRgPeriode());
            parameters.put("pMWVRechnungsDatum", 	 		invoice.getRgDatumString());

            parameters.put("pMWVTotalBetragMwst",			invoice.getMwstBetrag());
            parameters.put("pMWVTotalBetragNetto",			invoice.getTotalInvoiceAmount());
            parameters.put("pMWVTotalBetragBrutto",			invoice.getTotalInvoiceAmountInclMwst());							
            

            /* Using compiled version(.jasper) of Jasper report to generate PDF */
            JasperPrint jasperPrint = JasperFillManager.fillReport("ressources/Template_MWV_WP4CAD.jasper", parameters, new JREmptyDataSource());

            /* outputStream to create PDF */
            OutputStream outputStream = new FileOutputStream(new File(outputFile));

            /* Write content to PDF file */
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            System.out.println("File: "+ outputFile + " generated");
            outputStream.close();
        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public File createXML() {
		File file = new File("output/XML_MietWV_WP4CAD_12.xml");
		AbaConnectContainer acc = new AbaConnectContainer();
		acc.setTaskCount(1);
		Task task = new Task();
		
		Parameter param = new Parameter("DEBI", "Belege", "AbaDefault", "2015.00");
		task.setParameter(param);
		
		ArrayList<Task> tasks = new ArrayList<Task>();
		tasks.add(task);
		acc.setTasks(tasks);
		
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		task.setTransactions(transactions);
		Transaction transaction = new Transaction(1);
		transactions.add(transaction);
		
		ArrayList<Document> documents = new ArrayList<Document>();
		transaction.setDocument(documents);
		Document document = new Document(12345, 9999L, "Blubb", "CHF", "MV11", "", "2017-31-12", 22.22, 19.19, null, null, null);
		documents.add(document);
		
		try {
		
			JAXBContext jaxbContext = JAXBContext.newInstance(AbaConnectContainer.class);
//			StringWriter writer = new StringWriter();
//		    writer.append("<?xml version='1.0' encoding='UTF-8'?>");
			
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			jaxbMarshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml version='1.0' encoding='UTF-8'?>");
			
			jaxbMarshaller.marshal(acc, file);
			jaxbMarshaller.marshal(acc, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	public void createZipFile(File pdfFile, File xmlFile) {
		
		List<File> srcFiles = Arrays.asList(pdfFile, xmlFile);
        FileOutputStream fos;
		try {
			fos = new FileOutputStream("output/Test_ZipContainer_Export.zip");
	        ZipOutputStream zipOut = new ZipOutputStream(fos);
	        for (File srcFile : srcFiles) {
	            //File fileToZip = new File(srcFile);
	            FileInputStream fis = new FileInputStream(srcFile);
	            ZipEntry zipEntry = new ZipEntry(srcFile.getName());
	            zipOut.putNextEntry(zipEntry);
	 
	            byte[] bytes = new byte[1024];
	            int length;
	            while((length = fis.read(bytes)) >= 0) {
	                zipOut.write(bytes, 0, length);
	            }
	            fis.close();
	        }
	        
	        zipOut.close();
	        fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
