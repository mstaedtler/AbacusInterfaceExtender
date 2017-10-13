package ch.gruner.dbs.aie.businessobjects;

public class DetailsByCostCenter {
	
	private Integer costCenter;
	private Double amount;
	
	public Integer getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(Integer costCenter) {
		this.costCenter = costCenter;
	}
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return getCostCenter() + ": " + getAmount();
	}

}
