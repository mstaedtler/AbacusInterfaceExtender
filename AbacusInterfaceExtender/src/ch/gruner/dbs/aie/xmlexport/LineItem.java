package ch.gruner.dbs.aie.xmlexport;

public class LineItem {
	
	private final String mode = "SAVE";
	
	/**
	 * Pflichtfelder
	 **/
	private Integer number; 				// Positionsnummer	(6)	
	private Double amount;					// Positionsbetrag in Belegw�hrung	(12)
	private Double keyAmount;				// Postionsbetrag in Leitw�hrung	(12)	
	private Integer creditAccount; 			// Habenkonto (11)
	
	/**
	 * Optionale Felder
	 ***/
	private Integer project; 				// Projekt(11)
	private Integer creditCostCentre1; 		// Haben-Kostenstelle 1 (11)
	private Integer creditCostCentre2; 		// Haben-Kostenstelle 2 (11)
	private Integer taxMethod; 				// MWST-Methode (1)
	private String 	taxCode; 				// MWST-Code (3)
    private Integer taxIncluded;			// MWST-Art	LineItem (1)
    private String 	taxDateValidFrom;		// MWST-Satz berechnen per Datum (3)
    private Double 	taxAmount;				// MWST-Betrag in Belegw�hrung (12)
    private Double 	keyTaxAmount; 			// MWST-Betrag in Leitw�hrung (12)
    private String 	text;					// Positionstext LineItem (30)
    private Integer quantity;				// Menge	LineItem (12)
    private String 	quantityCode;			// Mengencode (4)
    private String 	generalLedgerCode;		// FIBU-Code (1)
    private Integer division; 				// Gesch�ftsbereich (6)
    private String 	consolidationCode;		// Konsolidierungscode (8)
    private Integer externalOrder;			// Externer Auftrag (12)
    private Integer externalLineItem; 		// Externe Position (6)
    private String 	externalReference;		// Externe Referenz	(20)
	
	/**
	 * Default constructor
	 */
	public LineItem(){}

	/**
	 * Constructor with mandatory fields
	 * @param number
	 * @param amount
	 * @param keyAmount
	 * @param creditAccount
	 */
	public LineItem(Integer number, 
					Double amount, 
					Double keyAmount,
					Integer creditAccount,
					String taxDateValidFrom) {
		super();
		this.number = number;
		this.amount = amount;
		this.keyAmount = keyAmount;
		this.creditAccount = creditAccount;
		this.taxDateValidFrom = taxDateValidFrom;
	}

	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the keyAmount
	 */
	public Double getKeyAmount() {
		return keyAmount;
	}

	/**
	 * @param keyAmount the keyAmount to set
	 */
	public void setKeyAmount(Double keyAmount) {
		this.keyAmount = keyAmount;
	}

	/**
	 * @return the creditAccount
	 */
	public Integer getCreditAccount() {
		return creditAccount;
	}

	/**
	 * @param creditAccount the creditAccount to set
	 */
	public void setCreditAccount(Integer creditAccount) {
		this.creditAccount = creditAccount;
	}

	/**
	 * @return the project
	 */
	public Integer getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Integer project) {
		this.project = project;
	}

	/**
	 * @return the creditCostCentre1
	 */
	public Integer getCreditCostCentre1() {
		return creditCostCentre1;
	}

	/**
	 * @param creditCostCentre1 the creditCostCentre1 to set
	 */
	public void setCreditCostCentre1(Integer creditCostCentre1) {
		this.creditCostCentre1 = creditCostCentre1;
	}

	/**
	 * @return the creditCostCentre2
	 */
	public Integer getCreditCostCentre2() {
		return creditCostCentre2;
	}

	/**
	 * @param creditCostCentre2 the creditCostCentre2 to set
	 */
	public void setCreditCostCentre2(Integer creditCostCentre2) {
		this.creditCostCentre2 = creditCostCentre2;
	}

	/**
	 * @return the taxMethod
	 */
	public Integer getTaxMethod() {
		return taxMethod;
	}

	/**
	 * @param taxMethod the taxMethod to set
	 */
	public void setTaxMethod(Integer taxMethod) {
		this.taxMethod = taxMethod;
	}

	/**
	 * @return the taxCode
	 */
	public String getTaxCode() {
		return taxCode;
	}

	/**
	 * @param taxCode the taxCode to set
	 */
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	/**
	 * @return the taxIncluded
	 */
	public Integer getTaxIncluded() {
		return taxIncluded;
	}

	/**
	 * @param taxIncluded the taxIncluded to set
	 */
	public void setTaxIncluded(Integer taxIncluded) {
		this.taxIncluded = taxIncluded;
	}

	/**
	 * @return the taxDateValidFrom
	 */
	public String getTaxDateValidFrom() {
		return taxDateValidFrom;
	}

	/**
	 * @param taxDateValidFrom the taxDateValidFrom to set
	 */
	public void setTaxDateValidFrom(String taxDateValidFrom) {
		this.taxDateValidFrom = taxDateValidFrom;
	}

	/**
	 * @return the taxAmount
	 */
	public Double getTaxAmount() {
		return taxAmount;
	}

	/**
	 * @param taxAmount the taxAmount to set
	 */
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * @return the keyTaxAmount
	 */
	public Double getKeyTaxAmount() {
		return keyTaxAmount;
	}

	/**
	 * @param keyTaxAmount the keyTaxAmount to set
	 */
	public void setKeyTaxAmount(Double keyTaxAmount) {
		this.keyTaxAmount = keyTaxAmount;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the quantityCode
	 */
	public String getQuantityCode() {
		return quantityCode;
	}

	/**
	 * @param quantityCode the quantityCode to set
	 */
	public void setQuantityCode(String quantityCode) {
		this.quantityCode = quantityCode;
	}

	/**
	 * @return the generalLedgerCode
	 */
	public String getGeneralLedgerCode() {
		return generalLedgerCode;
	}

	/**
	 * @param generalLedgerCode the generalLedgerCode to set
	 */
	public void setGeneralLedgerCode(String generalLedgerCode) {
		this.generalLedgerCode = generalLedgerCode;
	}

	/**
	 * @return the division
	 */
	public Integer getDivision() {
		return division;
	}

	/**
	 * @param division the division to set
	 */
	public void setDivision(Integer division) {
		this.division = division;
	}

	/**
	 * @return the consolidationCode
	 */
	public String getConsolidationCode() {
		return consolidationCode;
	}

	/**
	 * @param consolidationCode the consolidationCode to set
	 */
	public void setConsolidationCode(String consolidationCode) {
		this.consolidationCode = consolidationCode;
	}

	/**
	 * @return the externalOrder
	 */
	public Integer getExternalOrder() {
		return externalOrder;
	}

	/**
	 * @param externalOrder the externalOrder to set
	 */
	public void setExternalOrder(Integer externalOrder) {
		this.externalOrder = externalOrder;
	}

	/**
	 * @return the externalLineItem
	 */
	public Integer getExternalLineItem() {
		return externalLineItem;
	}

	/**
	 * @param externalLineItem the externalLineItem to set
	 */
	public void setExternalLineItem(Integer externalLineItem) {
		this.externalLineItem = externalLineItem;
	}

	/**
	 * @return the externalReference
	 */
	public String getExternalReference() {
		return externalReference;
	}

	/**
	 * @param externalReference the externalReference to set
	 */
	public void setExternalReference(String externalReference) {
		this.externalReference = externalReference;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}
	
	
	

}
