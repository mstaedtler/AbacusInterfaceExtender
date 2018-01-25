package ch.gruner.dbs.aie.xmlexport.debi;

public class PaymentTerm {

	private final String mode = "SAVE";
	
	private Integer number;
	private boolean copyFromTable;
	private Integer type;
	private String deadlineAsDate;
	
	/**
	 * Default constructor
	 */
	public PaymentTerm(){}

	/**
	 * @param number
	 * @param copyFromTable
	 * @param type
	 * @param deadlineAsDate
	 */
	public PaymentTerm(Integer number, boolean copyFromTable, Integer type,
			String deadlineAsDate) {
		super();
		this.number = number;
		this.copyFromTable = copyFromTable;
		this.type = type;
		this.deadlineAsDate = deadlineAsDate;
	}

	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * @return the copyFromTable
	 */
	public boolean isCopyFromTable() {
		return copyFromTable;
	}

	/**
	 * @param copyFromTable the copyFromTable to set
	 */
	public void setCopyFromTable(boolean copyFromTable) {
		this.copyFromTable = copyFromTable;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the deadlineAsDate
	 */
	public String getDeadlineAsDate() {
		return deadlineAsDate;
	}

	/**
	 * @param deadlineAsDate the deadlineAsDate to set
	 */
	public void setDeadlineAsDate(String deadlineAsDate) {
		this.deadlineAsDate = deadlineAsDate;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}
	
	
	
	 
}
