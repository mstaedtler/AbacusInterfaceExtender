package ch.gruner.dbs.aie.businessobjects;

public class DetailsByProfile {
	
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
	
	public void addAmount(Double newAmount) {
		if(amount == null) {
			amount = 0.0d;
		}else {
			amount = amount + newAmount;
		}
	}
	
	@Override
	public String toString() {
		return getProfile() + ": " + getAmount();
	}
}
