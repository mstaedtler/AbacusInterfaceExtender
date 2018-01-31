package unused;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Set;

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
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.gruner.dbs.aie.gui.PanelFactory;
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
		fileSelectionPanel.setBorder(BorderFactory.createTitledBorder("Importdatei für Mietweiterverrechnung wählen"));
		
		jButFileSelection = new JButton("Pfad auswählen");
		jButFileSelection.addActionListener(buttonActions);
        jButFileSelection.setActionCommand(ActionCommands.SELECT_PATH.toString());
		JLabel pathLabel = new JLabel("Importdatei wählen");
		
		jButExport = new JButton("Exportieren");
		jButExport.addActionListener(buttonActions);
		jButExport.setActionCommand(ActionCommands.EXPORT.toString());
		
//		JDateComponentFactory jdcf = new JDateComponentFactory();
//		JDatePicker jdp = jdcf.createJDatePicker();
		

		fileSelectionPanel.add(pathLabel);
		fileSelectionPanel.add(getjTextFieldPath());
		fileSelectionPanel.add(jButFileSelection);
//		fileSelectionPanel.add((Component) jdp);
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
		
//		try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            ex.printStackTrace();
//        }
//
//        setDefaultSize(24);
		
		// Create and set up the window.
		frame = new JFrame("DBS Abacus Extender - Handling Interfaces easy");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		MainFrame newContentPane = new MainFrame();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);
		//frame.setSize(new Dimension(1500, 700));

		// Display the window.
		frame.pack();
		frame.setVisible(true);

		LOG.info("Anwendung wird beendet");
	}
	
	public static void setDefaultSize(int size) {

        Set<Object> keySet = UIManager.getLookAndFeelDefaults().keySet();
        Object[] keys = keySet.toArray(new Object[keySet.size()]);

        for (Object key : keys) {

            if (key != null && key.toString().toLowerCase().contains("font")) {

                System.out.println(key);
                Font font = UIManager.getDefaults().getFont(key);
                if (font != null) {
                    font = font.deriveFont((float)size);
                    UIManager.put(key, font);
                }

            }

        }

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
