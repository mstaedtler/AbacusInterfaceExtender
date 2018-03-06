package ch.gruner.dbs.aie.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.gruner.dbs.aie.businessobjects.WVImportBooking;
import ch.gruner.dbs.aie.util.MathFunctions;
import ch.gruner.dbs.aie.xmlexport.fibu.AbaConnectContainer;
import ch.gruner.dbs.aie.xmlexport.fibu.AmountData;
import ch.gruner.dbs.aie.xmlexport.fibu.CollectiveInformation;
import ch.gruner.dbs.aie.xmlexport.fibu.Entry;
import ch.gruner.dbs.aie.xmlexport.fibu.ExchangeRateData;
import ch.gruner.dbs.aie.xmlexport.fibu.Parameter;
import ch.gruner.dbs.aie.xmlexport.fibu.SingleInformation;
import ch.gruner.dbs.aie.xmlexport.fibu.Task;
import ch.gruner.dbs.aie.xmlexport.fibu.Transaction;

public class CSVReader {

	private static Logger LOG = LogManager.getLogger(CSVReader.class);
    
	public AbaConnectContainer readDatevDiffBuchungen(String inputFilepath, String buchungstext, String buchungsDatum, Double kurs) {
		
		String line = "";
        String cvsSplitBy = ", ";
        
        ArrayList<Transaction> transactions = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
   
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilepath))) {
        	LOG.info("Lese file: " + inputFilepath);
        	//Skip first line (Header)
        	line = br.readLine();
        	line = null;
        	int transactionId = 1;
        	while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] vmLine = line.split(cvsSplitBy);
                int flagEurKonto = Integer.parseInt(vmLine[2]);
                Double betrag = Double.parseDouble(vmLine[8]);
                //﻿GB, KST, Flag EUR, Mapping Konto, KTO, KTONAME, Betrag Abacus, Betrag DATEV, Dif. Buchug
                CollectiveInformation ci = new CollectiveInformation("SAVE",
																	 "A",
																	 "S",
																	 "Normal",
																	 "D",
																	 "4020",
																	 vmLine[0],
																	 "CHF",
																	 buchungsDatum,
																	 betrag,															
																	 vmLine[3],															
																	 vmLine[1],															 
																	 buchungstext,															 
																	 "F"
																	);
                SingleInformation si = new SingleInformation("SAVE", "Normal", "D", buchungsDatum, betrag, "900000", "0", buchungstext);
                if(flagEurKonto == 1) {
                	ExchangeRateData erd = new ExchangeRateData("SAVE", "EUR", "CHF", kurs);
                	ci.setExchangeRateData(erd);
                	Double calcAmount = betrag / kurs;
                	AmountData amountData = new AmountData("SAVE", "EUR", MathFunctions.round(calcAmount, 2));
                	ci.setAmountData(amountData);
                	si.setAmountData(amountData);
                }else {
                	AmountData amountData = new AmountData("SAVE", "CHF", betrag);
                	ci.setAmountData(amountData);
                	si.setAmountData(amountData);
                }
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
        	br.close();
        } catch (IOException e) {      	
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