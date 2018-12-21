package fi.eashin.taskManager.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PriorityRepository extends CrudRepository<Priority, Long> {
	List<Priority> findByPriority(String priority);
}