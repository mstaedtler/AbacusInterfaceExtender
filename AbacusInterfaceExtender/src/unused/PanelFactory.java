/**
 * 
 */
package unused;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.gruner.dbs.aie.businessobjects.InvoiceWV;
import ch.gruner.dbs.aie.model.TableModelInvoicesWV;

public class PanelFactory extends JPanel {


	private static final long serialVersionUID = 2397809639996593131L;
	
	public static JPanel createAllNonePanel(final TableModelInvoicesWV tableObjects){
		JButton selectAll = new JButton("Alle");
        JButton selectNone = new JButton("Keine");
        selectAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					for (InvoiceWV aDatabase : tableObjects.getAllTableObjects()) {
						aDatabase.setExport(true);
						tableObjects.fireTableDataChanged();
					}
				} catch (Exception e) {
					
					//LOG.error(ExceptionMessages.UNHANDLED_EXCEPTION, e);
					//MainFrame.showDialog(e, ExceptionMessages.UNHANDLED_EXCEPTION);
				}
				
			}
		});
        
        selectNone.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					for (InvoiceWV aDatabase : tableObjects.getAllTableObjects()) {
						aDatabase.setExport(false);
						tableObjects.fireTableDataChanged();
					}
				} catch (Exception e) {
					//LOG.error("", e);
					//MainFrame.showDialog(e, ExceptionMessages.UNHANDLED_EXCEPTION);
				}
				
			}
		});
        
        JPanel selectAllNonePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        selectAllNonePanel.add(selectAll);
        selectAllNonePanel.add(selectNone);
        return selectAllNonePanel;
	}
	
}
