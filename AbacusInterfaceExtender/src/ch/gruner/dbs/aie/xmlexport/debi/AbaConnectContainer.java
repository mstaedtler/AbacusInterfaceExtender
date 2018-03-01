package ch.gruner.dbs.aie.xmlexport.debi;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "AbaConnectContainer")
public class AbaConnectContainer {
	
	private Integer taskCount;
	private ArrayList<Task> tasks;
	
	public AbaConnectContainer() {}
	
	public AbaConnectContainer(Integer taskCount, ArrayList<Task> tasks) {
		this.taskCount = taskCount;
		this.tasks = tasks;
	}
	
	public Integer getTaskCount() {
		return taskCount;
	}
	
	@XmlElement (name="TaskCount")
	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}
	
	@XmlElement (name = "Task")
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	
	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
	
	
	
	

}
