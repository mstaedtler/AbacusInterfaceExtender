package ch.gruner.dbs.aie.xmlexport;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "Task")
public class Task {
	
	private Parameter parameter;
	private ArrayList<Transaction> transactions;
	
	public Task() {}
	
	public Task(Parameter parameter, ArrayList<Transaction> transactions) {
		this.parameter = parameter;
		this.transactions = transactions;
	}
	
	@XmlElement (name = "Parameter")
	public Parameter getParameter() {
		return parameter;
	}
	
	
	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}
	
	@XmlElement (name = "Transaction")
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	

}
