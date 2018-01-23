package ch.gruner.dbs.aie.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;

import ch.gruner.dbs.aie.actions.ButtonActions;
import ch.gruner.dbs.aie.model.TableModelInvoicesWV;

public class MainFrame extends JPanel{

	public enum ActionCommands {
		SELECT_PATH, EXPORT, CLOSE, INFO
	}

	private static final long serialVersionUID = 3886715116607550812L;
	private static Logger LOG = LogManager.getLogger(MainFrame.class);
	
	private JTabbedPane tabedPane;
	private JTable table;
	private static JTextField jTextFieldPath;
	private JButton jButFileSelection;
	private JButton jButExport;
	private JButton jButClose;
	private JButton jButInfo;
	private static TableModelInvoicesWV model;
	
	public static JFrame frame;
	private ButtonActions buttonActions;
	
	public MainFrame() {
		super(new BorderLayout());
//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		model = new TableModelInvoicesWV();
		buttonActions = new ButtonActions(model);
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(1200, 500));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// JPanel northPanel = new JPanel(new BorderLayout());

		JPanel fileSelectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jButFileSelection = new JButton("Pfad auswählen");
		jButFileSelection.addActionListener(buttonActions);
        jButFileSelection.setActionCommand(ActionCommands.SELECT_PATH.toString());
		JLabel pathLabel = new JLabel("Importdatei wählen");
		
		jButExport = new JButton("Exportieren");
		jButExport.addActionListener(buttonActions);
		jButExport.setActionCommand(ActionCommands.EXPORT.toString());

		fileSelectionPanel.add(pathLabel);
		fileSelectionPanel.add(getjTextFieldPath());
		fileSelectionPanel.add(jButFileSelection);
		fileSelectionPanel.setBorder(BorderFactory.createTitledBorder("Importdatei für Mietweiterverrechnung wählen"));
		fileSelectionPanel.add(jButExport);
			
		//setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		//add(fileSelectionPanel, BorderLayout.NORTH);

		//SOUTH Panel (Info & Closebutton für alles)
		jButInfo= new JButton("Info");
		jButInfo.addActionListener(buttonActions);
		jButInfo.setActionCommand(ActionCommands.INFO.toString());
		
		jButClose = new JButton("Schliessen");
		jButClose.addActionListener(buttonActions);
		jButClose.setActionCommand(ActionCommands.CLOSE.toString());
		
		JPanel jSouthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jSouthPanel.add(jButInfo);
		jSouthPanel.add(jButClose);
		add(jSouthPanel, BorderLayout.SOUTH);
		// Add scrollpane with table
		
		
		JScrollPane scrollPaneMWV = new JScrollPane(table);
		JPanel tablePanelMWV = new JPanel(new BorderLayout());
		tablePanelMWV.add(fileSelectionPanel, BorderLayout.NORTH);
		tablePanelMWV.add(scrollPaneMWV, BorderLayout.CENTER);
		tablePanelMWV.add(PanelFactory.createAllNonePanel(model), BorderLayout.AFTER_LAST_LINE);
		
		
		JScrollPane scrollPaneDATEV_FIBU = new JScrollPane(table);
		JPanel tablePanelDATEV_FIBU = new JPanel(new BorderLayout());
		tablePanelMWV.add(scrollPaneDATEV_FIBU);
		
		JScrollPane scrollPaneDATEV_DEBI = new JScrollPane(table);
		JPanel tablePanelDATEV_DEBI = new JPanel(new BorderLayout());
		tablePanelMWV.add(scrollPaneDATEV_DEBI);
		
		JScrollPane scrollPaneIT = new JScrollPane(table);
		JPanel tablePanelIT = new JPanel(new BorderLayout());
		tablePanelMWV.add(scrollPaneIT);
		
		JScrollPane scrollPaneProjekte = new JScrollPane(table);
		JPanel tablePanelProjekte = new JPanel(new BorderLayout());
		tablePanelMWV.add(scrollPaneProjekte);
		
		
		getTabedPane().add("Miete Weiterverrechnung", tablePanelMWV);
		getTabedPane().add("Weiterverrechnung IT Kosten", tablePanelIT);
		getTabedPane().add("Weiterverrechnung Projekte CS/DE", tablePanelProjekte);
		
		getTabedPane().add("DATEV FIBU", tablePanelDATEV_FIBU);
		getTabedPane().add("DATEV DEBI", tablePanelDATEV_DEBI);
		
		getTabedPane().add("Logging", new JPanel()); //TODO ersetzen mit getErrorTabPanel()
//		getTabedPane().setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		add(getTabedPane(), BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		LOG.info("Anwendung gestartet");
		
		// Create and set up the window.
		frame = new JFrame("DBS Abacus Extender - Handling Interfaces easy");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		MainFrame newContentPane = new MainFrame();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);

		
		
//		LOG.info("Anzahl GB's: " + bookingByGb.size());

//		InvoiceWV invoice = new InvoiceWV(bookingByGb.get(11));
//		for (DetailsByCostCenter detByCC : invoice.getBookingDetailsByCostCenter()) {
//			LOG.info(detByCC);
//		}
//		LOG.info("Anzahl Kostenstellen: " + invoice.getBookingDetailsByCostCenter().size());
//		
//		for (DetailsByProfile detByProfile : invoice.getBookingDetailsByProfile()) {
//			LOG.info(detByProfile);
//		}
//		LOG.info("Anzahl Profile: " + invoice.getBookingDetailsByProfile().size());
		
		
		
		
//		Exporter exporter = new Exporter();
//		exporter.createMietWVPdf(invoice);
		
		
		


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


	
	/**
	 * @return the tabedPane
	 */
	public JTabbedPane getTabedPane() {
		if (tabedPane == null) {
			tabedPane = new JTabbedPane();
		}
		return tabedPane;
	}
	
	public static JTextField getjTextFieldPath() {
		if (jTextFieldPath == null) {
			jTextFieldPath = new JTextField(30);
		}
		return jTextFieldPath;
	}

}
