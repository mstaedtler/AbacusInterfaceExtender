package ch.gruner.dbs.aie.gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;

import ch.gruner.dbs.aie.actions.CSVReader;
import ch.gruner.dbs.aie.actions.Exporter;
import ch.gruner.dbs.aie.businessobjects.DetailsByCostCenter;
import ch.gruner.dbs.aie.businessobjects.DetailsByProfile;
import ch.gruner.dbs.aie.businessobjects.InvoiceWV;
import ch.gruner.dbs.aie.businessobjects.WVImportBooking;

public class MainFrame {

	private static Logger LOG = LogManager.getLogger(MainFrame.class);

	public static void main(String[] args) {
		LOG.info("Anwendung gestartet");

		CSVReader csvReader = new CSVReader();
		java.util.List<WVImportBooking> list = csvReader.readMietCSV("input/vmAccounting_20170930.csv");

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
		LOG.info("Anzahl GB's: " + bookingByGb.size());

		InvoiceWV invoice = new InvoiceWV(bookingByGb.get(11));
		for (DetailsByCostCenter detByCC : invoice.getBookingDetailsByCostCenter()) {
			LOG.info(detByCC);
		}
		LOG.info("Anzahl Kostenstellen: " + invoice.getBookingDetailsByCostCenter().size());
		
		for (DetailsByProfile detByProfile : invoice.getBookingDetailsByProfile()) {
			LOG.info(detByProfile);
		}
		LOG.info("Anzahl Profile: " + invoice.getBookingDetailsByProfile().size());
		
		LOG.info("Total Betrag ohne Mwst.: " + invoice.getTotalInvoiceAmount());
		LOG.info("Total Betrag Mwst.: " + invoice.getMwstBetrag());
		LOG.info("Total Betrag inkl Mwst.: " + invoice.getTotalInvoiceAmountInclMwst());
		
		Exporter exporter = new Exporter();
		exporter.createMietWVPdf(invoice);
		
		
		
//		HashMap<Integer, HashMap<String, Double>> lineItems = invoice.getLineItems();
//		if (lineItems != null) {
//			TreeMap<Integer, HashMap<String, Double>> map = new TreeMap<>(lineItems);
//			for (Integer cc : map.keySet()) {
//				LOG.info("Kostenstelle: " + cc);
//				for (String profile : map.get(cc).keySet()) {
//					LOG.info("Profile: " + profile + " ; Gesamtbetrag: " + map.get(cc).get(profile));
//				}
//			}
//		}

		// TEST: One invoice per GB
		// int test = 0;
		// for (Integer gbNo : bookingByGb.keySet()) {
		// InvoiceWV invoice = new InvoiceWV(bookingByGb.get(gbNo));
		// LOG.info("Datensätze in GB: " + gbNo + " sind: " +
		// bookingByGb.get(gbNo).size());
		// test = test + bookingByGb.get(gbNo).size();
		// }
		// LOG.info("Total: " + test);

		// try {
		// createPdf("output/pdfexport.pdf");
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		LOG.info("Anwendung wird beendet");
	}

	public static void createXLSXFile() {
		try {

			// Create blank workbook
			XSSFWorkbook workbook = new XSSFWorkbook();
			// Create a blank sheet
			XSSFSheet spreadsheet = workbook.createSheet(" Employee Info ");
			// Create row object
			XSSFRow row;
			// This data needs to be written (Object[])
			Map<String, Object[]> empinfo = new TreeMap<String, Object[]>();
			empinfo.put("1", new Object[] { "EMP ID", "EMP NAME", "DESIGNATION" });
			empinfo.put("2", new Object[] { "tp01", "Gopal", "Technical Manager" });
			empinfo.put("3", new Object[] { "tp02", "Manisha", "Proof Reader" });
			empinfo.put("4", new Object[] { "tp03", "Masthan", "Technical Writer" });
			empinfo.put("5", new Object[] { "tp04", "Satish", "Technical Writer" });
			empinfo.put("6", new Object[] { "tp05", "Krishna", "Technical Writer" });
			// Iterate over data and write to sheet
			Set<String> keyid = empinfo.keySet();
			int rowid = 0;
			for (String key : keyid) {
				row = spreadsheet.createRow(rowid++);
				Object[] objectArr = empinfo.get(key);
				int cellid = 0;
				for (Object obj : objectArr) {
					Cell cell = row.createCell(cellid++);
					cell.setCellValue((String) obj);
				}
			}
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("output/Sheet.xlsx"));
			workbook.write(out);

			out.close();
			workbook.close();
			System.out.println("Writesheet.xlsx written successfully");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void createPdf(String dest) throws IOException {
		// Initialize PDF writer
		PdfWriter writer = new PdfWriter(dest);

		// Initialize PDF document
		PdfDocument pdf = new PdfDocument(writer);

		// Initialize document
		com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdf);

		// Create a PdfFont
		PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
		// Add a Paragraph
		document.add(new Paragraph("iText is:").setFont(font));
		// Create a List
		List list = new List().setSymbolIndent(12).setListSymbol("\u2022").setFont(font);
		// Add ListItem objects
		list.add(new ListItem("Never gonna give you up")).add(new ListItem("Never gonna let you down"))
				.add(new ListItem("Never gonna run around and desert you"))
				.add(new ListItem("Never gonna make you cry")).add(new ListItem("Never gonna say goodbye"))
				.add(new ListItem("Never gonna tell a lie and hurt you"));
		// Add the list
		document.add(list);

		// Close document
		document.close();
	}

	public static void writeDocument() {
		/* Write XML File from Document */

		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			FileWriter fileWriter = new FileWriter("output/output.xml");
			XMLWriter writer = new XMLWriter(fileWriter, format);
			writer.write(createXMLDocument());
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static Document createXMLDocument() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root");

		Element author1 = root.addElement("author").addAttribute("name", "James").addAttribute("location", "UK")
				.addText("James Strachan");

		Element author2 = root.addElement("author").addAttribute("name", "Bob").addAttribute("location", "US")
				.addText("Bob McWhirter");

		return document;
	}

}
