package fi.eashin.taskManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.eashin.taskManager.domain.Status;
import fi.eashin.taskManager.domain.StatusRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class StatusRepositoryTest {

	@Autowired
	private StatusRepository sRepo;

	@Test
	public void createNewStatus() {
		Status testStatus = new Status("Testing");
		sRepo.save(testStatus);
		assertThat(testStatus.getStatus()).isEqualTo("Testing");
	}

	@Test
	public void findByStatusShouldReturnStatus() {
		List<Status> statuses = sRepo.findByStatus("In Progress");
		assertThat(statuses).hasSize(1);
		assertThat(statuses.get(0).getStatus()).isEqualTo("In Progress");
	}

}