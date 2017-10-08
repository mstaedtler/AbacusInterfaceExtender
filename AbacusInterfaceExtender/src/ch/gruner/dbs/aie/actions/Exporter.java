package ch.gruner.dbs.aie.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.gruner.dbs.aie.businessobjects.Debitorenadresse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Exporter {

	public void createMietWVPdf() {
		
		try {
            /* Output file location */
            String outputFile = "output/JasperTableExample.pdf";

            /* List to hold Items */
            List<Debitorenadresse> listItems = new ArrayList<Debitorenadresse>();

            /* Create Items */
            Debitorenadresse boe = new Debitorenadresse();
            boe.setName("Gruner Böhringer AG");
            boe.setAnschrift("Teststrasse 22");

            Debitorenadresse gwz = new Debitorenadresse();
            gwz.setName("Gruner Wepf Zürich");
            gwz.setAnschrift("Dingensstrasse 11");

            /* Add Items to List */
            listItems.add(boe);
            listItems.add(gwz);

            /* Convert List to JRBeanCollectionDataSource */
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("ItemDataSource", itemsJRBean);

            /* Using compiled version(.jasper) of Jasper report to generate PDF */
            JasperPrint jasperPrint = JasperFillManager.fillReport("config/template_mieteWV.jasper", parameters, new JREmptyDataSource());

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
