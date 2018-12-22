package fi.eashin.taskManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.eashin.taskManager.domain.Priority;
import fi.eashin.taskManager.domain.Status;
import fi.eashin.taskManager.domain.Task;
import fi.eashin.taskManager.domain.TaskRepository;
import fi.eashin.taskManager.domain.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryTest {
	@Autowired
	private TaskRepository tRepo;

	@Test
	public void findByTaskNameShouldReturnTask() {
		List<Task> tasks = tRepo.findByTaskName("Server project");
		assertThat(tasks).hasSize(1);
		assertThat(tasks.get(0).getTaskName()).isEqualTo("Server project");
	}

	@Test
	public void createNewTask() {
		Task task1 = (new Task("TestProject", new Priority("High"), "Extended time",
				new User("tuser", "$2y$12$aQ3pEOJ2iRU7oZVZlaGBa.GJrn4PwlDdH8DFAtoTEjAPci8NrHUPy", "USER"), "2019-01-12",
				new Status("In Progress")));
		tRepo.save(task1);
		assertThat(task1.getId()).isNotNull();
		assertThat(task1.getTaskName()).isEqualTo("TestProject");
	}

	@Test
	public void deleteTask() {
		Task task2 = (new Task("TestProject", new Priority("High"), "Extended time",
				new User("tuser", "$2y$12$aQ3pEOJ2iRU7oZVZlaGBa.GJrn4PwlDdH8DFAtoTEjAPci8NrHUPy", "USER"), "2019-01-12",
				new Status("In Progress")));
		tRepo.save(task2);
		tRepo.delete(task2);
		assertThat(tRepo.existsById(task2.getId())).isFalse();
	}

}
