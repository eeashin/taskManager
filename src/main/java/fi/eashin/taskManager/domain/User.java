package fi.eashin.taskManager.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "userId", nullable = false, updatable = false)
	    private Long userId;
	 
	// Username with unique constraint
	    @Column(name = "username", nullable = false, unique = true)
	    private String username;

	    @Column(name = "password", nullable = false)
	    private String passwordHash;

	    @Column(name = "role", nullable = false)
	    private String role;
	    

		@OneToMany(cascade = CascadeType.ALL,mappedBy = "username")
		private List<Task> tasks;
		
	    public User() {
	    }

		public User(String username, String passwordHash, String role) {
			super();
			this.username = username;
			this.passwordHash = passwordHash;
			this.role = role;
		}

		

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public List<Task> getTasks() {
			return tasks;
		}

		public void setTasks(List<Task> tasks) {
			this.tasks = tasks;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username.toLowerCase();
		}

		public String getPasswordHash() {
			return passwordHash;
		}

		public void setPasswordHash(String passwordHash) {
			this.passwordHash = passwordHash;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String toString() {
			return this.username;
		}
	
	

}
