package fi.eashin.taskManager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String taskName;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "priorityId")
	private Priority priority;

	private String notes;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "userId")
	private User username;

	private String dueDate;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "statusId")
	private Status status;

	public Task() {
		super();
	}

	public Task(String taskName, Priority priority, String notes, User username, String dueDate, Status status) {
		super();
		this.taskName = taskName;
		this.priority = priority;
		this.notes = notes;
		this.username = username;
		this.dueDate = dueDate;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	// Set taskName to first letter to Upper Case
	public void setTaskName(String taskName) {
		this.taskName = taskName.substring(0, 1).toUpperCase() + taskName.substring(1);
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public User getUsername() {
		return username;
	}

	public void setUsername(User username) {
		this.username = username;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
