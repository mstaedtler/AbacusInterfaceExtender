package unused;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ch.gruner.dbs.aie.xmlexport.fibu.AbaConnectContainer;
import ch.gruner.dbs.aie.xmlexport.fibu.AmountData;
import ch.gruner.dbs.aie.xmlexport.fibu.CollectiveInformation;
import ch.gruner.dbs.aie.xmlexport.fibu.Entry;
import ch.gruner.dbs.aie.xmlexport.fibu.ExchangeRateData;
import ch.gruner.dbs.aie.xmlexport.fibu.Parameter;
import ch.gruner.dbs.aie.xmlexport.fibu.SingleInformation;
import ch.gruner.dbs.aie.xmlexport.fibu.Task;
import ch.gruner.dbs.aie.xmlexport.fibu.Transaction;

public class TestExport {

	public static void main(String[] args) {
		createFibuXML();

	}
	
	public static File createFibuXML() {
		File file = new File("output/Testing_ExchangeRate.xml");
	
		ExchangeRateData erd = new ExchangeRateData("SAVE", "EUR", "CHF", 1.2036801d);
		AmountData amountData = new AmountData("SAVE", "EUR", 109905.19d);
		CollectiveInformation ci = new CollectiveInformation("SAVE",
															 "A",
															 "S",
															 "Normal",
															 "D",
															 "4020",
															 "92",
															 "CHF",
															 "2018-01-31",
															 132290.69d,															
															 "301000",															
															 "920350",															 
															 "Abschluss Jan 2018",															 
															 "F"
															);
		
		//Pflichtwerte CollectiveInformation
		ci.setAmountData(amountData);
		ci.setExchangeRateData(erd);
		//Standardwerte CollectiveInformation
		ci.setTaxAccount("0");
		ci.setIntercompanyId("0");
		ci.setBookingLevel2("0");
		ci.setBookingLevel3("0");		
		
		SingleInformation si = new SingleInformation("SAVE", "Normal", "D", "2018-01-31", 132290.69d, "900000", "0", "Abschluss");
		
		Entry entry = new Entry("SAVE", ci, si);
		ArrayList<Entry> entries = new ArrayList<>();
		entries.add(entry);
		Transaction transaction = new Transaction(1, entries);
		ArrayList<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);
		Parameter param = new Parameter("FIBU", "XML Buchungen", "AbaDefault", "2015.00");
		
		Task task = new Task(param, transactions);
		ArrayList<Task> tasks = new ArrayList<>();
		tasks.add(task);
		AbaConnectContainer abaConnectContainer = new AbaConnectContainer(1, tasks);
				
		
		try {
		
			JAXBContext jaxbContext = JAXBContext.newInstance(AbaConnectContainer.class);
//			StringWriter writer = new StringWriter();
//		    writer.append("<?xml version='1.0' encoding='UTF-8'?>");
			
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			jaxbMarshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml version='1.0' encoding='UTF-8'?>");
			
			jaxbMarshaller.marshal(abaConnectContainer, file);
			jaxbMarshaller.marshal(abaConnectContainer, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return file;
	}

}
