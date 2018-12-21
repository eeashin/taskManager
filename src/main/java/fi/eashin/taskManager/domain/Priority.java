package fi.eashin.taskManager.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Priority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long priorityId;
	
	private String priority;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "priority")
	private List<Task> tasks;

	public Priority() {
		super();
	}

	public Priority(String priority) {
		super();
		this.priority = priority;
	}

	public long getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(long priorityId) {
		this.priorityId = priorityId;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public String toString() {
		return this.priority;
	}
	
		

}
