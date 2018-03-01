package ch.gruner.dbs.aie.xmlexport.fibu;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement (name = "SingleInformation")
@XmlType(propOrder = {"type", "debitCredit", "entryDate", "valueDate", "amountData", "keyAmount",  
					  "account", "taxAccount", "intercompanyId", "intercompanyCode", "bookingLevel1", "bookingLevel2", 
					  "bookingLevel3", "text1", "text2", "documentNumber", "selectionCode"
					 })
public class SingleInformation {

	private String mode;
	private String type;
	private String debitCredit;
	private String entryDate;
	private String valueDate;
	private AmountData amountData;
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
	private String selectionCode;
	
	public SingleInformation() {}
	
	/**
	 * @param mode
	 * @param type
	 * @param debitCredit
	 * @param entryDate
	 * @param keyAmount
	 * @param account
	 * @param bookingLevel1
	 * @param text1
	 */
	public SingleInformation(String mode, String type, String debitCredit, String entryDate, Double keyAmount,
			String account, String bookingLevel1, String text1) {
		this.mode = mode;
		this.type = type;
		this.debitCredit = debitCredit;
		this.entryDate = entryDate;
		this.keyAmount = keyAmount;
		this.account = account;
		this.bookingLevel1 = bookingLevel1;
		this.text1 = text1;
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
	 * @return the selectionCode
	 */
	@XmlElement (name = "SelectionCode")
	public String getSelectionCode() {
		return selectionCode;
	}

	/**
	 * @param selectionCode the selectionCode to set
	 */
	public void setSelectionCode(String selectionCode) {
		this.selectionCode = selectionCode;
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
	
}
