package ch.gruner.dbs.aie.xmlexport.fibu;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "AmountData")
public class AmountData {

	private String mode;
	private String currency;
	private Double amount;
	
	public AmountData() {}
	
	/**
	 * @param mode
	 * @param currency
	 * @param amount
	 */
	public AmountData(String mode, String currency, Double amount) {
		this.mode = mode;
		this.currency = currency;
		this.amount = amount;
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
	 * @return the currency
	 */
	@XmlElement (name = "Currency")
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the amount
	 */
	@XmlElement (name = "Amount")
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	
	
}
