package ch.gruner.dbs.aie.actions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.gruner.dbs.aie.businessobjects.WVImportBooking;

public class CSVReader {

	private static Logger LOG = LogManager.getLogger(CSVReader.class);
    
	public List<WVImportBooking> readMietCSV(String inputFilepath) {

        String csvFile = inputFilepath;
        String line = "";
        String cvsSplitBy = ";";
        List <WVImportBooking> list = new ArrayList<WVImportBooking>();

        WVImportBooking wvBooking;
         
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	LOG.info("Lese file: " + csvFile);
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] vmLine = line.split(cvsSplitBy);
                
                Double cost = Double.parseDouble(vmLine[8]);
                Integer costCenter = Integer.parseInt(vmLine[9]);
           
                wvBooking = new WVImportBooking();
                wvBooking.setPeriod(vmLine[0]);
                wvBooking.setName(vmLine[1]);
                wvBooking.setType(vmLine[2]);
                wvBooking.setUserName(vmLine[3]);
                wvBooking.setTags(vmLine[4]);
                wvBooking.setGroups(vmLine[5]);
                wvBooking.setPublishedName(vmLine[6]);
                wvBooking.setProfile(vmLine[7]);
                wvBooking.setCost(cost);
                wvBooking.setCostCenter(costCenter);
                
                LOG.info("Zeile gelesen: " + wvBooking);
                list.add(wvBooking);
            }
            LOG.info("Es wurden: " + list.size() + " Zeilen gelesen und hinzugefügt.");
            br.close();
            LOG.info("BufferedReader geschlossen");
        } catch (IOException e) {      	
        	e.printStackTrace();
        }
        
        return list;
    }
   

}