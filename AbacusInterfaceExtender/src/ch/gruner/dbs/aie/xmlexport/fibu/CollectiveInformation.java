package ch.gruner.dbs.aie.xmlexport.fibu;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement (name = "CollectiveInformation")
@XmlType(propOrder = {"entryLevel", "entryType", "type", "debitCredit", "client", "division", "keyCurrency", "entryDate",
					  "valueDate", "amountData", "keyAmount", "exchangeRateData", "account", "taxAccount", "intercompanyId", 
					  "intercompanyCode", "bookingLevel1", "bookingLevel2", "bookingLevel3", "text1", "text2", 
					  "documentNumber", "singleCount", "bookingSource"
					 })
public class CollectiveInformation {
	
	private String mode;
	
	private String entryLevel;
	private String entryType;
	private String type;
	private String debitCredit;
	private String client;
	private String division;
	private String keyCurrency;
	private String entryDate;
	private String valueDate;
	private Double keyAmount;
	private String account;
	private String taxAccount;
	private String intercompanyId;
	private String intercompanyCode;
	private String bookingLevel1;
	private String bookingLevel2;
	private String bookingLevel3;
	private String text1;
	private String text2;
	private String documentNumber;
	private String singleCount;
	private String bookingSource;
	private AmountData amountData;
	private ExchangeRateData exchangeRateData;
	
	public CollectiveInformation() {}
		
	public CollectiveInformation(String mode, String entryLevel, String entryType, String type, String debitCredit,
			String client, String division, String keyCurrency, String entryDate, Double keyAmount, 
			String account, String bookingLevel1, String text1, String bookingSource) {
		this.mode = mode;
		this.entryLevel = entryLevel;
		this.entryType = entryType;
		this.type = type;
		this.debitCredit = debitCredit;
		this.client = client;
		this.division = division;
		this.keyCurrency = keyCurrency;
		this.entryDate = entryDate;
		this.keyAmount = keyAmount;
		this.account = account;
		this.bookingLevel1 = bookingLevel1;
		this.text1 = text1;
		this.bookingSource = bookingSource;
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
	 * @return the entryLevel
	 */
	@XmlElement (name = "EntryLevel")
	public String getEntryLevel() {
		return entryLevel;
	}



	/**
	 * @param entryLevel the entryLevel to set
	 */
	public void setEntryLevel(String entryLevel) {
		this.entryLevel = entryLevel;
	}



	/**
	 * @return the entryType
	 */
	@XmlElement (name = "EntryType")
	public String getEntryType() {
		return entryType;
	}



	/**
	 * @param entryType the entryType to set
	 */
	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}



	/**
	 * @return the type
	 */
	@XmlElement (name = "Type")
	public String getType() {
		return type;
	}



	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}



	/**
	 * @return the debitCredit
	 */
	@XmlElement (name = "DebitCredit")
	public String getDebitCredit() {
		return debitCredit;
	}



	/**
	 * @param debitCredit the debitCredit to set
	 */
	public void setDebitCredit(String debitCredit) {
		this.debitCredit = debitCredit;
	}



	/**
	 * @return the client
	 */
	@XmlElement (name = "Client")
	public String getClient() {
		return client;
	}



	/**
	 * @param client the client to set
	 */
	public void setClient(String client) {
		this.client = client;
	}



	/**
	 * @return the division
	 */
	@XmlElement (name = "Division")
	public String getDivision() {
		return division;
	}




	/**
	 * @param division the division to set
	 */
	public void setDivision(String division) {
		this.division = division;
	}




	/**
	 * @return the keyCurrency
	 */
	@XmlElement (name = "KeyCurrency")
	public String getKeyCurrency() {
		return keyCurrency;
	}



	/**
	 * @param keyCurrency the keyCurrency to set
	 */
	public void setKeyCurrency(String keyCurrency) {
		this.keyCurrency = keyCurrency;
	}



	/**
	 * @return the entryDate
	 */
	@XmlElement (name = "EntryDate")
	public String getEntryDate() {
		return entryDate;
	}



	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}



	/**
	 * @return the valueDate
	 */
	@XmlElement (name = "ValueDate")
	public String getValueDate() {
		return valueDate;
	}



	/**
	 * @param valueDate the valueDate to set
	 */
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}



	/**
	 * @return the keyAmount
	 */
	@XmlElement (name = "KeyAmount")
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
	 * @return the account
	 */
	@XmlElement (name = "Account")
	public String getAccount() {
		return account;
	}



	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}



	/**
	 * @return the taxAccount
	 */
	@XmlElement (name = "TaxAccount")
	public String getTaxAccount() {
		return taxAccount;
	}



	/**
	 * @param taxAccount the taxAccount to set
	 */
	public void setTaxAccount(String taxAccount) {
		this.taxAccount = taxAccount;
	}



	/**
	 * @return the intercompanyId
	 */
	@XmlElement (name = "IntercompanyId")
	public String getIntercompanyId() {
		return intercompanyId;
	}



	/**
	 * @param intercompanyId the intercompanyId to set
	 */
	public void setIntercompanyId(String intercompanyId) {
		this.intercompanyId = intercompanyId;
	}



	/**
	 * @return the intercompanyCode
	 */
	@XmlElement (name = "IntercompanyCode")
	public String getIntercompanyCode() {
		return intercompanyCode;
	}



	/**
	 * @param intercompanyCode the intercompanyCode to set
	 */
	public void setIntercompanyCode(String intercompanyCode) {
		this.intercompanyCode = intercompanyCode;
	}



	/**
	 * @return the bookingLevel1
	 */
	@XmlElement (name = "BookingLevel1")
	public String getBookingLevel1() {
		return bookingLevel1;
	}



	/**
	 * @param bookingLevel1 the bookingLevel1 to set
	 */
	public void setBookingLevel1(String bookingLevel1) {
		this.bookingLevel1 = bookingLevel1;
	}



	/**
	 * @return the bookingLevel2
	 */
	@XmlElement (name = "BookingLevel2")
	public String getBookingLevel2() {
		return bookingLevel2;
	}



	/**
	 * @param bookingLevel2 the bookingLevel2 to set
	 */
	public void setBookingLevel2(String bookingLevel2) {
		this.bookingLevel2 = bookingLevel2;
	}



	/**
	 * @return the bookingLevel3
	 */
	@XmlElement (name = "BookingLevel3")
	public String getBookingLevel3() {
		return bookingLevel3;
	}



	/**
	 * @param bookingLevel3 the bookingLevel3 to set
	 */
	public void setBookingLevel3(String bookingLevel3) {
		this.bookingLevel3 = bookingLevel3;
	}



	/**
	 * @return the text1
	 */
	@XmlElement (name = "Text1")
	public String getText1() {
		return text1;
	}



	/**
	 * @param text1 the text1 to set
	 */
	public void setText1(String text1) {
		this.text1 = text1;
	}



	/**
	 * @return the text2
	 */
	@XmlElement (name = "Text2")
	public String getText2() {
		return text2;
	}



	/**
	 * @param text2 the text2 to set
	 */
	public void setText2(String text2) {
		this.text2 = text2;
	}



	/**
	 * @return the documentNumber
	 */
	@XmlElement (name = "DocumentNumber")
	public String getDocumentNumber() {
		return documentNumber;
	}



	/**
	 * @param documentNumber the documentNumber to set
	 */
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}



	/**
	 * @return the singleCount
	 */
	@XmlElement (name = "SingleCount")
	public String getSingleCount() {
		return singleCount;
	}



	/**
	 * @param singleCount the singleCount to set
	 */
	public void setSingleCount(String singleCount) {
		this.singleCount = singleCount;
	}



	/**
	 * @return the bookingSource
	 */
	@XmlElement (name = "BookingSource")
	public String getBookingSource() {
		return bookingSource;
	}



	/**
	 * @param bookingSource the bookingSource to set
	 */
	public void setBookingSource(String bookingSource) {
		this.bookingSource = bookingSource;
	}



	/**
	 * @return the amountData
	 */
	@XmlElement (name = "AmountData")
	public AmountData getAmountData() {
		return amountData;
	}

	/**
	 * @param amountData the amountData to set
	 */
	public void setAmountData(AmountData amountData) {
		this.amountData = amountData;
	}

	/**
	 * @return the exchangeRateData
	 */
	@XmlElement (name = "ExchangeRateData")
	public ExchangeRateData getExchangeRateData() {
		return exchangeRateData;
	}

	/**
	 * @param exchangeRateData the exchangeRateData to set
	 */
	public void setExchangeRateData(ExchangeRateData exchangeRateData) {
		this.exchangeRateData = exchangeRateData;
	}
	
	
	
}
