package ch.gruner.dbs.aie.xmlexport.fibu;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "ExchangeRateData")
public class ExchangeRateData {
	
	
	private String mode;
	private String fromCurrency;
	private String toCurrency;
	private Double exchangeRate;
	
	public ExchangeRateData() {}
	
	public ExchangeRateData(String mode, String fromCurrency, String toCurrency, Double exchangeRate) {
		this.mode = mode;
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.exchangeRate = exchangeRate;
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
	 * @return the fromCurrency
	 */
	@XmlElement (name = "FromCurrency")
	public String getFromCurrency() {
		return fromCurrency;
	}

	/**
	 * @param fromCurrency the fromCurrency to set
	 */
	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	/**
	 * @return the toCurrency
	 */
	@XmlElement (name = "ToCurrency")
	public String getToCurrency() {
		return toCurrency;
	}

	/**
	 * @param toCurrency the toCurrency to set
	 */
	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	/**
	 * @return the exchangeRate
	 */
	@XmlElement (name = "ExchangeRate")
	public Double getExchangeRate() {
		return exchangeRate;
	}

	/**
	 * @param exchangeRate the exchangeRate to set
	 */
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	
	
	
}
