package ch.gruner.dbs.aie.xmlexport.fibu;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Parameter {
	
	private String application; 
    private String id; 
    private String mapId;
    private String version;

    public Parameter() {}
    
	/**
	 * @param mapId
	 * @param application
	 * @param id
	 * @param version
	 */
	public Parameter(String application, String id, String mapId, String version) {
    	this.mapId = mapId;
    	this.application = application;
		this.id = id;
		this.version = version;
	}
    
	public String getApplication() {
		return application;
	}
	
	@XmlElement (name = "Application")
	public void setApplication(String application) {
		this.application = application;
	}
	public String getId() {
		return id;
	}
	
	@XmlElement (name = "Id")
	public void setId(String id) {
		this.id = id;
	}
	public String getMapId() {
		return mapId;
	}
	
	@XmlElement (name = "MapId")
	public void setMapId(String mapId) {
		this.mapId = mapId;
	}
	public String getVersion() {
		return version;
	}
	
	@XmlElement (name = "Version")
	public void setVersion(String version) {
		this.version = version;
	}

    
    
}
