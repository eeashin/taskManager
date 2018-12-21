package fi.eashin.taskManager.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
	List<Task> findByTaskName(String taskName);

	List<Task> findByDueDate(String dueDate);

	// Enabling ignore case
	List<Task> findByTaskNameIgnoreCase(String taskName);

	// Enabling ORDER BY for a query
	List<Task> findByTaskNameOrderByTaskNameAsc(String taskName);

	List<Task> findByTaskNameIgnoreCaseContaining(String byTaskName);

	//
}
