package ch.gruner.dbs.aie.xmlexport.debi;

import org.apache.commons.lang3.StringUtils;

public class AddressData {
	
	private final String mode = "SAVE";
	
	private String codeName;
	private String name;
	private String firstName;
	private String line1; // used for company
	private String line2; // used for street
	private String line3; // used for postfach
	private String line4;
	private String line5;
	private String country;
	private String zip;
	private String city;
	private String language;
	
	private boolean isFirma;
	private String firma;
	private String firma2;
	private String street;
	private String postfach;
	private String syntAnrede;
	private String syntNachname;
	private String syntVorname;
	
	public AddressData() {}

	/**
	 * Construtor with needed fields. For optional fields, use the setter methods
	 * @param codeName
	 * @param name
	 * @param country
	 * @param city
	 * @param language
	 */
	public AddressData(String country,
					   String city, String language) {
		super();
		this.country = country;
		this.city = city;
		this.language = language;
	}

	/**
	 * @return the codeName
	 */
	public String getCodeName() {
		if(StringUtils.isNotEmpty(getName())){
			codeName = StringUtils.substring(getName(), 0, 7);
		}else if(StringUtils.isNotEmpty(getFirstName())){
			codeName = StringUtils.substring(getFirstName(), 0, 7);
		}else if(StringUtils.isNotEmpty(getLine1())){
			codeName = StringUtils.substring(getLine1(), 0, 7);
		}else{
			codeName = StringUtils.substring("UNDEFINED", 0, 7);
		}
		if(country != null && zip != null){
			codeName = StringUtils.substring(codeName + country + zip, 0, 15);
		}else{
			codeName = StringUtils.substring(codeName + "CH0000", 0, 15);
		}
		//someText.replaceAll("[_[^\\w\\d������\\+\\- ]]", "")
		return StringUtils.upperCase(codeName.replaceAll("[^A-Za-z0-9+-]", ""));
	}

	/**
	 * @param codeName the codeName to set
	 */
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		if(isFirma()){
			name = getFirma();
		}else{
			name = getSyntNachname();
		}
		if(StringUtils.isEmpty(name)){
			name = "";
		}
		return  StringUtils.substring(name, 0, 49);
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		if(!isFirma()){
			firstName = getSyntVorname();
		}else{
			firstName = "";
		}
		return StringUtils.substring(firstName, 0, 29);
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the line1
	 */
	public String getLine1() {
		if(isFirma()){
			line1 = firma2;
		}else{
			line1 = "";
		}
		return StringUtils.substring(line1, 0, 49);
	}

	/**
	 * @param line1 the line1 to set
	 */
	public void setLine1(String line1) {
		this.line1 = line1;
	}

	/**
	 * @return the line2
	 */
	public String getLine2() {
		if(isFirma() && StringUtils.isNotEmpty(getSyntNachname())){
			line2 = getSyntAnrede() + " " + getSyntVorname() + " " + getSyntNachname();
		}
		return StringUtils.trim(StringUtils.substring(line2, 0, 49));
	}

	/**
	 * @param line2 the line2 to set
	 */
	public void setLine2(String line2) {
		this.line2 = line2;
	}

	/**
	 * @return the line3
	 */
	public String getLine3() {
		if(StringUtils.isNotEmpty(street)){
			line3 = street;
		}else{
			line3 = "";
		}
		return StringUtils.substring(line3, 0, 49);
	}

	/**
	 * @param line3 the line3 to set
	 */
	public void setLine3(String line3) {
		this.line3 = line3;
	}

	/**
	 * @return the line4
	 */
	public String getLine4() {
		if(StringUtils.isNotEmpty(postfach)){
			line4 = postfach;
		}else{
			line4 = "";
		}
		return StringUtils.substring(line4, 0, 49);
	}

	/**
	 * @param line4 the line4 to set
	 */
	public void setLine4(String line4) {
		this.line4 = line4;
	}

	/**
	 * @return the line5
	 */
	public String getLine5() {
		return StringUtils.substring(line5, 0, 49);
	}

	/**
	 * @param line5 the line5 to set
	 */
	public void setLine5(String line5) {
		this.line5 = line5;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return StringUtils.substring(country, 0, 3);
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return StringUtils.substring(city, 0, 49);
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @return the isFirma
	 */
	public boolean isFirma() {
		if(StringUtils.isNotEmpty(getFirma())){
			isFirma = true;
		}else{
			isFirma = false;
		}
		return isFirma;
	}

	/**
	 * @return the firma
	 */
	public String getFirma() {
		return firma;
	}

	/**
	 * @param firma the firma to set
	 */
	public void setFirma(String firma) {
		this.firma = firma;
	}

	/**
	 * @return the firma2
	 */
	public String getFirma2() {
		return firma2;
	}

	/**
	 * @param firma2 the firma2 to set
	 */
	public void setFirma2(String firma2) {
		this.firma2 = firma2;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the postfach
	 */
	public String getPostfach() {
		return postfach;
	}

	/**
	 * @param postfach the postfach to set
	 */
	public void setPostfach(String postfach) {
		this.postfach = postfach;
	}

	/**
	 * @return the syntNachname
	 */
	public String getSyntNachname() {
		if(syntNachname == null){
			syntNachname = "";
		}
		return syntNachname;
	}

	/**
	 * @param syntNachname the syntNachname to set
	 */
	public void setSyntNachname(String syntNachname) {
		this.syntNachname = syntNachname;
	}

	/**
	 * @return the syntVorname
	 */
	public String getSyntVorname() {
		if(syntVorname == null){
			syntVorname = "";
		}
		return syntVorname;
	}

	/**
	 * @param syntVorname the syntVorname to set
	 */
	public void setSyntVorname(String syntVorname) {
		this.syntVorname = syntVorname;
	}

	/**
	 * @return the syntAnrede
	 */
	public String getSyntAnrede() {
		if(syntAnrede == null){
			syntAnrede = "";
		}
		return syntAnrede;
	}

	/**
	 * @param syntAnrede the syntAnrede to set
	 */
	public void setSyntAnrede(String syntAnrede) {
		this.syntAnrede = syntAnrede;
	}
	
	
	
}
