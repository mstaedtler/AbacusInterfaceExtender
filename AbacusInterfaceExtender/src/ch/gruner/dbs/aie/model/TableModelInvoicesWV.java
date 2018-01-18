package ch.gruner.dbs.aie.model;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.gruner.dbs.aie.businessobjects.Debitorenadresse;
import ch.gruner.dbs.aie.businessobjects.InvoiceWV;

public class TableModelInvoicesWV extends DefaultTableModel {

	private static final long serialVersionUID = -7027568562678907318L;
	private ArrayList<InvoiceWV> allAccessFiles;
	private static Logger LOG = LogManager.getLogger(TableModelInvoicesWV.class);
	
	/**
	 * The default constructor
	 */
	public TableModelInvoicesWV() {
		super();
	}		

	/** 
	 * Return the number of Columns
	 * @see javax.swing.table.DefaultTableModel#getColumnCount()
	 */
	public int getColumnCount() {return 10;}

	/**
	 * Return the number of rows
	 * @see javax.swing.table.DefaultTableModel#getRowCount()
	 */
	public int getRowCount() {
		return getAllTableObjects().size();
	}

	/** (non-Javadoc)
	 * @see javax.swing.table.DefaultTableModel#getColumnName(int)
	 */
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
			case 0: return "GB";
	     	case 1: return "Adresse";
	     	case 2: return "Rechnungsnummer";
	     	case 3: return "Projektnummer";
	     	case 4: return "Referenz";
	        case 5: return "Betrag";
	        case 6: return "Mwst. Satz";
	        case 7: return "Mwst. Betrag";
	        case 8: return "Total Betrag";
	        case 9: return "Exportieren?";
		default: return null;
		}
	}

	/** (non-Javadoc)
	 * @see javax.swing.table.DefaultTableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(getAllTableObjects().size() > rowIndex){
			InvoiceWV invoiceWV = getAllTableObjects().get(rowIndex);
		      
		      switch( columnIndex ){
		      	 case 0: return invoiceWV.getGbNummer();
		      	 case 1: return invoiceWV.getAdresse();
		      	 case 2: return invoiceWV.getInvoiceNumber();
		      	 case 3: return invoiceWV.getProjektNummer();
		      	 case 4: return invoiceWV.getProjektReferenz();
		         case 5: return invoiceWV.getTotalInvoiceAmount();
		         case 6: return invoiceWV.getMwstSatz();
		         case 7: return invoiceWV.getMwstBetrag();
		         case 8: return invoiceWV.getTotalInvoiceAmountInclMwst();
		         case 9: return invoiceWV.isExport();		         
		         default: return null;
		      } 
		}
		return null;
	}
	
	/** (non-Javadoc)
	 * @see javax.swing.table.DefaultTableModel#setValueAt(java.lang.Object, int, int)
	 */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		InvoiceWV invoiceWV = getAllTableObjects().get(rowIndex);
		
		switch (columnIndex) {
		case 2:
			if(aValue instanceof Integer) {
				invoiceWV.setInvoiceNumber((Integer) aValue);
			}
			break;
		case 3:
			if(aValue instanceof Integer) {
				invoiceWV.setProjektNummer((Integer) aValue);
			}
			break;
		case 4:
			invoiceWV.setProjektReferenz((String) aValue);
			break;
		case 9:
			invoiceWV.setExport((Boolean) aValue);
			break;
		}

	}

	/** (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0: 
			return Integer.class;
		case 1:
			return Debitorenadresse.class;
		case 2:
			return Integer.class;
		case 3:
			return Integer.class;
		case 4:
			return String.class;
		case 5:
			return Double.class;
		case 6:
			return Double.class;
		case 7:
			return Double.class;
		case 8:
			return Double.class;
		case 9:
			return Boolean.class;
		default:
			return null;
		}
	}
	
	/**
	 * Get all bjects of this table model.
	 * @return all tableObjects in an ArrayList
	 */
	public ArrayList<InvoiceWV> getAllTableObjects() {
		if(allAccessFiles == null){
			allAccessFiles = new ArrayList<InvoiceWV>();
		}
		return allAccessFiles;
	}

	/** (non-Javadoc)
	 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {		
		if (columnIndex == 2 || columnIndex == 3 || columnIndex == 4 || columnIndex == 9) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Adds an new object to the table model
	 * @param object the object to add.
	 */
	public void addObjectToTable(InvoiceWV object) {
		if(object instanceof InvoiceWV){
			getAllTableObjects().add((InvoiceWV) object);
			fireTableDataChanged();
			LOG.info("Die hinzugefügte Invoice ist : " + object);
		}
	}

	/**
	 * Resets the table model to its initial state and removes all entries from this model.
	 */
	public void resetModel() {
		allAccessFiles = null;
		fireTableDataChanged();
	}	
	
}
