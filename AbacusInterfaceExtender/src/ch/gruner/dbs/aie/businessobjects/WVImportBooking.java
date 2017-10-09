package ch.gruner.dbs.aie.businessobjects;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

public class WVImportBooking {
	
	public WVImportBooking() {
		
	}
		
//	private enum Profile {
//		RAM, CAD_STANDARD, STANDARD, SPECIAL
//	}
	
	private Calendar date;
	private String period;
	private String name;
	private String type;
	private String userName;
	private String tags;
	private String groups;
	private String publishedName;
	private String profile;
	private Double cost;
	private Integer costCenter;
	private Integer gb;
	
	/**
	 * @return the month
	 */
	public Calendar getDate() {
		Calendar cal = Calendar.getInstance();
		if(getPeriod() != null && getPeriod() != "") {
			String[] monthYear = getPeriod().split(".");
	        int month = Integer.parseInt(monthYear[0]);
	        int year = Integer.parseInt(monthYear[1]);
	        cal.set(Calendar.MONTH, month);
	        cal.set(Calendar.YEAR, year);
		}
		return date;
	}
	
	public Integer getGb() {
		if(getCostCenter() != null) {
			gb = Integer.parseInt(StringUtils.left(getCostCenter().toString(), 2));
			return gb;
		}else {
			
			return 0;
		}
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the type
	 */
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	/**
	 * @return the groups
	 */
	public String getGroups() {
		return groups;
	}
	/**
	 * @param groups the groups to set
	 */
	public void setGroups(String groups) {
		this.groups = groups;
	}
	/**
	 * @return the publishedName
	 */
	public String getPublishedName() {
		return publishedName;
	}
	/**
	 * @param publishedName the publishedName to set
	 */
	public void setPublishedName(String publishedName) {
		this.publishedName = publishedName;
	}
	/**
	 * @return the profile
	 */
	public String getProfile() {
		return profile;
	}
	/**
	 * @param profile the profile to set
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}
	/**
	 * @return the cost
	 */
	public Double getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	public void setCost(Double cost) {
		this.cost = cost;
	}
	/**
	 * @return the costCenter
	 */
	public Integer getCostCenter() {
		return costCenter;
	}
	/**
	 * @param costCenter the costCenter to set
	 */
	public void setCostCenter(Integer costCenter) {
		this.costCenter = costCenter;
	}
	
	/**
	 * @return the period
	 */
	public String getPeriod() {
		return period;
	}
	/**
	 * @param period the period to set
	 */
	public void setPeriod(String period) {
		this.period = period;
	}
	@Override
	public String toString() {	
		return getGb() + ":" + getCostCenter() + ": " + getName() + " / " + getUserName() + " / " + getProfile() + " / " + getCost();
	}
	
}
