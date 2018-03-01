package ch.gruner.dbs.aie.xmlexport.fibu;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Transaction {
	
	private List<Entry> entries;
	private Integer id;
	
	public Transaction() {}
	
	public Transaction(Integer id) {
		this.id = id;
	}
	
	/**
	 * Constructor f�r KreditorenbuchaltungsExport
	 * @param document
	 */
	public Transaction(Integer id, List<Entry> entries) {
		super();
		this.id = id;
		this.entries = entries;
	}

	/**
	 * @return the document
	 */
	@XmlElement (name = "Entry")
	public List<Entry> getEntries() {
		return entries;
	}

	/**
	 * @param document the document to set
	 */
	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	public Integer getId() {
		return id;
	}

	@XmlAttribute (name = "id")
	public void setId(Integer id) {
		this.id = id;
	}

	
	
	
	

}
