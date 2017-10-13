package ch.gruner.dbs.aie.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.gruner.dbs.aie.businessobjects.Debitorenadresse;
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
            String outputFile = "output/JasperTableExample.pdf"; 

            /* List to hold Items */
            List<DetailsByProfile> listItemsProfile = invoice.getBookingDetailsByProfile();

            /* Create Items */
            Debitorenadresse gks = new Debitorenadresse();
            gks.setName("Gruner Gruneko AG");
            gks.setAnschrift("St. Jakobs-Strasse 199");     
            gks.setOrt("4020 Basel");

            /* Add Items to List */
            
   

            /* Convert List to JRBeanCollectionDataSource */
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItemsProfile);         
         
            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("profileItemDataSource", itemsJRBean);
            //parameters.put("adressDataSource", gks);

            /* Using compiled version(.jasper) of Jasper report to generate PDF */
            JasperPrint jasperPrint = JasperFillManager.fillReport("ressources/template_mieteWV_GKS.jasper", parameters, new JREmptyDataSource());

            /* outputStream to create PDF */
            OutputStream outputStream = new FileOutputStream(new File(outputFile));
            /* Write content to PDF file */
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            System.out.println("File Generated");
        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
	}
	
	
}
