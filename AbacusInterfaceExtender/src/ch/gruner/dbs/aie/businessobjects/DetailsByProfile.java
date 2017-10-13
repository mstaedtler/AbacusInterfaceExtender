package ch.gruner.dbs.aie.businessobjects;

public class DetailsByProfile {
	
	public DetailsByProfile() {
		// TODO Auto-generated constructor stub
	}
	
	private String profile;
	private Double amount;
	
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
		
	@Override
	public String toString() {
		return getProfile() + ": " + getAmount();
	}
}
