package fi.eashin.taskManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.eashin.taskManager.domain.Priority;
import fi.eashin.taskManager.domain.PriorityRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PriorityRepositoryTest {

	@Autowired
	private PriorityRepository pRepo;

	@Test
	public void createNewPriority() {
		Priority testPriority = new Priority("Test");
		pRepo.save(testPriority);
		assertThat(testPriority.getPriority()).isEqualTo("Test");
	}

	@Test
	public void findByPriorityShouldReturnPriority() {
		List<Priority> priorities = pRepo.findByPriority("High");
		assertThat(priorities).hasSize(1);
		assertThat(priorities.get(0).toString()).isEqualTo("High");
	}

}
