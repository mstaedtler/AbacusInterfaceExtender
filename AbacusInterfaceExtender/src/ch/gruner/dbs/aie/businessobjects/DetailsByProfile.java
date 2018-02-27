package ch.gruner.dbs.aie.businessobjects;

public class DetailsByProfile {
	
	public DetailsByProfile() {
		// TODO Auto-generated constructor stub
	}
	
	public DetailsByProfile(String whr) {
		this.whr = whr;
	}
	
	private String profile;
	private Double amount;
	private String whr;
	
	public String getProfile() {
		return profile;
	}
	
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
		
	public String getWhr() {
		return whr;
	}

	public void setWhr(String whr) {
		this.whr = whr;
	}

	@Override
	public String toString() {
		return getProfile() + ": " + getAmount();
	}
}
