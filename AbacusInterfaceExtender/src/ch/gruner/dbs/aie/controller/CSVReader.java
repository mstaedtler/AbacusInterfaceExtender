package ch.gruner.dbs.aie.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.gruner.dbs.aie.businessobjects.WVImportBooking;
import ch.gruner.dbs.aie.xmlexport.fibu.AbaConnectContainer;
import ch.gruner.dbs.aie.xmlexport.fibu.AmountData;
import ch.gruner.dbs.aie.xmlexport.fibu.CollectiveInformation;
import ch.gruner.dbs.aie.xmlexport.fibu.Entry;
import ch.gruner.dbs.aie.xmlexport.fibu.Parameter;
import ch.gruner.dbs.aie.xmlexport.fibu.SingleInformation;
import ch.gruner.dbs.aie.xmlexport.fibu.Task;
import ch.gruner.dbs.aie.xmlexport.fibu.Transaction;

public class CSVReader {

	private static Logger LOG = LogManager.getLogger(CSVReader.class);
    
	public AbaConnectContainer readDatevDiffBuchungen(String inputFilepath, String buchungstext, String buchungsDatum, Double kurs) {
		
        ArrayList<Transaction> transactions = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        try {
        	//char delimiter = ',';
        	//char quotes = '"';
        	//String[] HEADERS = {"GB","KST","FLAG_KTO_EUR","MAPPING_KTO","KTO_ABACUS","KTO_ABACUS_NAME","BETRAG_ABACUS","BETRAG_DATEV","BETRAG_DIFF"};
        	Reader in = new FileReader(inputFilepath);
        	Iterable<CSVRecord> records = CSVFormat.DEFAULT
//        										   .withHeader(HEADERS) 
												   .withFirstRecordAsHeader()
//												   .withDelimiter(delimiter)
//												   .withQuote(quotes)
//												   .withQuoteMode(QuoteMode.MINIMAL)
												   .parse(in);
        	
        	
/*
 * This is working.
 * */       
//            Iterable<CSVRecord> records = CSVFormat.DEFAULT
//            									   .withHeader(HEADERS) 
//            									   .withFirstRecordAsHeader()
//            									   .withDelimiter(delimiter)
//            									   .withQuote(quotes)
//            									   .withQuoteMode(QuoteMode.MINIMAL)
//            									   .parse(in);
            
        	
        	LOG.info("Lese file: " + inputFilepath);
        	int transactionId = 1;
        	for (CSVRecord record : records) {
        		String division = record.get(0); //Has to be the number. Not working with Headername.
        		//int flagEurKonto = Integer.parseInt(record.get("FLAG_KTO_EUR"));
                Double betrag = Double.parseDouble(record.get("BETRAG_DIFF"));
                String bookinLevel1 = "0";
                String account = record.get("MAPPING_KTO");
                if(Integer.parseInt(account) >= 300000) {
                	bookinLevel1 = record.get("KST");
                }
                if(betrag != 0.00d) {
                
	                CollectiveInformation ci = new CollectiveInformation("SAVE",
																		 "A",
																		 "S",
																		 "Normal",
																		 "D",
																		 "4020",
																		 division,
																		 "CHF",
																		 buchungsDatum,
																		 betrag,															
																		 account,															
																		 bookinLevel1,														 
																		 buchungstext,													 
																		 "F"
																		);
	                SingleInformation si = new SingleInformation("SAVE", "Normal", "D", buchungsDatum, betrag, "900000", "0", buchungstext);
	                AmountData amountData = new AmountData("SAVE", "CHF", betrag);
	            	ci.setAmountData(amountData);
	            	si.setAmountData(amountData); 
	//                if(flagEurKonto == 1) {
	//                	ExchangeRateData erd = new ExchangeRateData("SAVE", "EUR", "CHF", kurs);
	//                	ci.setExchangeRateData(erd);
	//                	Double calcAmount = betrag / kurs;
	//                	AmountData amountData = new AmountData("SAVE", "EUR", MathFunctions.round(calcAmount, 2));
	//                	ci.setAmountData(amountData);
	//                	si.setAmountData(amountData);
	//                }else {
	//                	AmountData amountData = new AmountData("SAVE", "CHF", betrag);
	//                	ci.setAmountData(amountData);
	//                	si.setAmountData(amountData);
	//                }
	                ci.setTaxAccount("0");
	        		ci.setIntercompanyId("0");	
	        		ci.setSingleCount("0");
	        		ArrayList<Entry> entries = new ArrayList<>();
	        		Entry entry = new Entry("SAVE", ci, si);
	        		entries.add(entry);
	        		Transaction transaction = new Transaction(transactionId, entries);
	        		transactionId ++;
	        		transactions.add(transaction);
                }
        }
//        	br.close();
        } catch (IOException e) {      	
        	e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
		}

		Parameter param = new Parameter("FIBU", "XML Buchungen", "AbaDefault", "2015.00");		
		Task task = new Task(param, transactions);		
		tasks.add(task);		
		AbaConnectContainer abaConnectContainer = new AbaConnectContainer(1, tasks);   
        
		return abaConnectContainer;
	}
	
	public List<WVImportBooking> readMietCSV(String inputFilepath) {

        String csvFile = inputFilepath;
        String line = "";
        String cvsSplitBy = ";";
        List <WVImportBooking> list = new ArrayList<WVImportBooking>();

        WVImportBooking wvBooking;
         
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	LOG.info("Lese file: " + csvFile);
        	//Skip first line (Header)
        	line = br.readLine();
        	line = null;
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