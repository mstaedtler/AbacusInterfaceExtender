package ch.gruner.dbs.aie.businessobjects;

public class Debitorenadresse {
	
	private String Name;
	private String Anschrift;
	private String Ort;
	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * @return the anschrift
	 */
	public String getAnschrift() {
		return Anschrift;
	}
	/**
	 * @param anschrift the anschrift to set
	 */
	public void setAnschrift(String anschrift) {
		Anschrift = anschrift;
	}
	/**
	 * @return the ort
	 */
	public String getOrt() {
		return Ort;
	}
	/**
	 * @param ort the ort to set
	 */
	public void setOrt(String ort) {
		Ort = ort;
	}
	
	@Override
	public String toString() {
		return getName() + "; " + getAnschrift() + "; " + getOrt();
	}

}
