package ch.gruner.dbs.aie.xmlexport.debi;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author SAY
 * Document f�r DebitorenbuchhaltungsExport
 * Enth�lt einige fixe Values und 1..n LineItems und AdressData
 *
 */
@XmlRootElement
public class Document {
	
	private String mode = "SAVE";
	private String documentCode;
	private String reference;
	private String text;
	private Integer customerNumber;
	private Long number;
	private String accountReceivableDate; // DebiBelegdatum
	private String generalLedgerDate; // FibuDatum
	private String currency;
	private Double amount;
	private Double keyAmount;
	private String reminderProcedure;
	private Integer paymentOrderProcedure;
	private String paymentReferenceLine;
	private Integer collectiveAccount;
	private Integer collectiveCostCentre1;
	
	private AddressData addressData;
	private List<LineItem> lineItems;
	private PaymentTerm paymentTerm;
	
	public Document() {}

	/**
	 * Construtor with needed fields. For optional fields, use the setter methods
	 * @param customerNumber die Kundennummer
	 * @param number die Belegnummer
	 * @param documentCode die Belegart
	 * @param currency die W�hrung
	 * @param reminderProcedure 
	 * @param accountReceivableDate Debi-Belegdatum
	 * @param amount Betrag in Belegw�hrung
	 * @param keyAmount Betrag in Leitw�hrung
	 * @param addressData Einzeladresse
	 * @param lineItems Positionen
	 * @param paymentTerm Zahlungskonditionen
	 */
	public Document(Integer customerNumber,
					Long number,
					String documentCode,
					String currency,
					String reminderProcedure,
					String accountReceivableDate,
					String generalLedgerDate,
					Double amount,
					Double keyAmount,
					AddressData addressData, 
					List<LineItem> lineItems,
					PaymentTerm paymentTerm) {
		super();
		this.customerNumber = customerNumber;
		this.number = number;
		this.documentCode = documentCode;
		this.reminderProcedure = reminderProcedure;
		this.currency = currency;
		this.accountReceivableDate = accountReceivableDate;
		this.generalLedgerDate = generalLedgerDate;
		this.amount = amount;
		this.keyAmount = keyAmount;
		this.addressData = addressData;
		this.lineItems = lineItems;
		this.paymentTerm = paymentTerm;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the customerNumber
	 */
	public Integer getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * @param customerNumber the customerNumber to set
	 */
	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	/**
	 * @return the number
	 */
	public Long getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Long number) {
		this.number = number;
	}

	/**
	 * @return the accountReceivableDate
	 */
	public String getAccountReceivableDate() {
		return accountReceivableDate;
	}

	/**
	 * @param accountReceivableDate the accountReceivableDate to set
	 */
	public void setAccountReceivableDate(String accountReceivableDate) {
		this.accountReceivableDate = accountReceivableDate;
	}
	
	/**
	 * @return the generalLedgerDate
	 */
	public String getGeneralLedgerDate() {
		return generalLedgerDate;
	}

	/**
	 * @param generalLedgerDate the generalLedgerDate to set
	 */
	public void setGeneralLedgerDate(String generalLedgerDate) {
		this.generalLedgerDate = generalLedgerDate;
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
	 * @return the paymentOrderProcedure
	 */
	public Integer getPaymentOrderProcedure() {
		return paymentOrderProcedure;
	}

	/**
	 * @param paymentOrderProcedure the paymentOrderProcedure to set
	 */
	public void setPaymentOrderProcedure(Integer paymentOrderProcedure) {
		this.paymentOrderProcedure = paymentOrderProcedure;
	}

	/**
	 * @return the paymentReferenceLine
	 */
	public String getPaymentReferenceLine() {
		return paymentReferenceLine;
	}

	/**
	 * @param paymentReferenceLine the paymentReferenceLine to set
	 */
	public void setPaymentReferenceLine(String paymentReferenceLine) {
		this.paymentReferenceLine = paymentReferenceLine;
	}

	/**
	 * @return the collectiveAccount
	 */
	public Integer getCollectiveAccount() {
		return collectiveAccount;
	}

	/**
	 * @param collectiveAccount the collectiveAccount to set
	 */
	public void setCollectiveAccount(Integer collectiveAccount) {
		this.collectiveAccount = collectiveAccount;
	}

	/**
	 * @return the collectiveCostCentre1
	 */
	public Integer getCollectiveCostCentre1() {
		return collectiveCostCentre1;
	}

	/**
	 * @param collectiveCostCentre1 the collectiveCostCentre1 to set
	 */
	public void setCollectiveCostCentre1(Integer collectiveCostCentre1) {
		this.collectiveCostCentre1 = collectiveCostCentre1;
	}

	/**
	 * @return the addressData
	 */
	public AddressData getAddressData() {
		return addressData;
	}

	/**
	 * @param addressData the addressData to set
	 */
	public void setAddressData(AddressData addressData) {
		this.addressData = addressData;
	}

	/**
	 * @return the lineItems
	 */
	public List<LineItem> getLineItems() {
		return lineItems;
	}

	/**
	 * @param lineItems the lineItems to set
	 */
	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	/**
	 * @return the paymentTerm
	 */
	public PaymentTerm getPaymentTerm() {
		return paymentTerm;
	}

	/**
	 * @param paymentTerm the paymentTerm to set
	 */
	public void setPaymentTerm(PaymentTerm paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @return the documentCode
	 */
	public String getDocumentCode() {
		return documentCode;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @return the reminderProcedure
	 */
	public String getReminderProcedure() {
		return reminderProcedure;
	}

	/**
	 * @param documentCode the documentCode to set
	 */
	public void setDocumentCode(String documentCode) {
		this.documentCode = documentCode;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @param reminderProcedure the reminderProcedure to set
	 */
	public void setReminderProcedure(String reminderProcedure) {
		this.reminderProcedure = reminderProcedure;
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

	@XmlAttribute (name = "mode")
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	
}
