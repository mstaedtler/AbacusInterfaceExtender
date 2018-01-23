package ch.gruner.dbs.aie.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.gruner.dbs.aie.businessobjects.DetailsByCostCenter;
import ch.gruner.dbs.aie.businessobjects.DetailsByProfile;
import ch.gruner.dbs.aie.businessobjects.InvoiceWV;
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
	
}
