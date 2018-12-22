package fi.eashin.taskManager.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {
	List<Status> findByStatus(String status);
	
}
