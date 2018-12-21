package fi.eashin.taskManager.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, Long> {
	List<Status> findByStatus(String status);
}
