package ch.gruner.dbs.aie.xmlexport.debi;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Transaction {
	
	private List<Document> documents;
	private Integer id;
	
	public Transaction() {}
	
	public Transaction(Integer id) {
		this.id = id;
	}
	
	/**
	 * Constructor f�r KreditorenbuchaltungsExport
	 * @param document
	 */
	public Transaction(Integer id, List<Document> documents) {
		super();
		this.id = id;
		this.documents = documents;
	}

	/**
	 * @return the document
	 */
	@XmlElement (name = "Document")
	public List<Document> getDocuments() {
		return documents;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(List<Document> documents) {
		this.documents = documents;
	}

	public Integer getId() {
		return id;
	}

	@XmlAttribute (name = "id")
	public void setId(Integer id) {
		this.id = id;
	}

	
	
	
	

}
