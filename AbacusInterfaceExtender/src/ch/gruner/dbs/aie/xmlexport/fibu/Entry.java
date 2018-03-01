package ch.gruner.dbs.aie.xmlexport.fibu;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "Entry")
public class Entry {
	
	private String mode;
	private CollectiveInformation collectiveInformation;
	private SingleInformation singleInformation;
	
	public Entry() {}
	
	/**
	 * @param mode
	 * @param collectiveInformation
	 * @param singleInformation
	 */
	public Entry(String mode, CollectiveInformation collectiveInformation, SingleInformation singleInformation) {
		super();
		this.mode = mode;
		this.collectiveInformation = collectiveInformation;
		this.singleInformation = singleInformation;
	}
	/**
	 * @return the mode
	 */
	@XmlAttribute (name = "mode")
	public String getMode() {
		return mode;
	}
	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	/**
	 * @return the collectiveInformation
	 */
	@XmlElement (name = "CollectiveInformation")
	public CollectiveInformation getCollectiveInformation() {
		return collectiveInformation;
	}
	/**
	 * @param collectiveInformation the collectiveInformation to set
	 */
	public void setCollectiveInformation(CollectiveInformation collectiveInformation) {
		this.collectiveInformation = collectiveInformation;
	}
	/**
	 * @return the singleInformation
	 */
	@XmlElement (name = "SingleInformation")
	public SingleInformation getSingleInformation() {
		return singleInformation;
	}
	/**
	 * @param singleInformation the singleInformation to set
	 */
	public void setSingleInformation(SingleInformation singleInformation) {
		this.singleInformation = singleInformation;
	}
	
	

}
