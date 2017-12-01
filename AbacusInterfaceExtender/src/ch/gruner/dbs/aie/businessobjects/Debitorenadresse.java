package ch.gruner.dbs.aie.businessobjects;

public class Debitorenadresse {
	
	private String name;
	private String anschrift;
	private String ort;
	
	public Debitorenadresse() {
		// TODO Auto-generated constructor stub
	}
	
	public Debitorenadresse(String name, String anschrift, String ort) {
		this.ort = ort;
		this.name = name;
		this.anschrift = anschrift;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the anschrift
	 */
	public String getAnschrift() {
		return anschrift;
	}
	/**
	 * @param anschrift the anschrift to set
	 */
	public void setAnschrift(String anschrift) {
		this.anschrift = anschrift;
	}
	/**
	 * @return the ort
	 */
	public String getOrt() {
		return ort;
	}
	/**
	 * @param ort the ort to set
	 */
	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	@Override
	public String toString() {
		return getName() + "; " + getAnschrift() + "; " + getOrt();
	}

}
