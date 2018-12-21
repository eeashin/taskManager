package fi.eashin.taskManager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.eashin.taskManager.domain.Priority;
import fi.eashin.taskManager.domain.PriorityRepository;
import fi.eashin.taskManager.domain.Status;
import fi.eashin.taskManager.domain.StatusRepository;
import fi.eashin.taskManager.domain.Task;
import fi.eashin.taskManager.domain.TaskRepository;
import fi.eashin.taskManager.domain.User;
import fi.eashin.taskManager.domain.UserRepository;

@SpringBootApplication
public class TaskManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

	@Bean
	public CommandLineRunner taskManagerDemo(TaskRepository tRepo, PriorityRepository pRepo, UserRepository uRepo,
			StatusRepository sRepo) {

		return (args) -> {
			// add some demo data to priority list
			Priority high = new Priority("High");
			Priority medium = new Priority("Medium");
			Priority low = new Priority("Low");

			pRepo.save(high);
			pRepo.save(medium);
			pRepo.save(low);

			// add some demo data to user list
			User user = new User("user", "$2a$04$zW7a9ExTTBqT7Pxlh8p6.Ow.ABtR31nJjeF8EDUsrC/PoRCAoDYdu", "USER");
			User admin = new User("admin", "$2a$04$.nYxVYPFmDGr9zxYIm9PxuK5FU05OwPvSxstDok9YCYr9UM/eN/MG", "ADMIN");
			User eashin = new User("eashin", "$2a$04$kWdyvMOThd7lgLUYQzMis.Au5ReR8MSnNyQJPw3mpZ3NAZe3EHnbu", "ADMIN");
			User juha = new User("juha", "$2a$04$Dp./XDpuZYpBitp0fcEZ6O2Tpky/BXDRJQVpqFCQElRYc9o4nrBbu", "ADMIN");
			uRepo.save(user);
			uRepo.save(admin);
			uRepo.save(eashin);
			uRepo.save(juha);
			// add some demo data to status list

			Status wip = new Status("In Progress");
			Status done = (new Status("Completed"));
			Status action = (new Status("Wait for Action"));

			sRepo.save(wip);
			sRepo.save(done);
			sRepo.save(action);

			// add some demo data to task list

			Task task1 = (new Task("Server project", high, "Extended time", user, "2019-01-12", wip));
			Task task2 = (new Task("React project", low, "No extended time", admin, "2019-02-25", done));
			Task task3 = (new Task("Movie App Documentation", low, "Be careful about mistake", eashin, "2018-12-30",
					done));
			Task task4 = (new Task("Security issue in Server", high, "Contact with System Admin", juha, "2018-12-29",
					wip));
			Task task5 = (new Task("Training Web App", high, "Project of Health Ministry", juha, "2018-12-29", action));
			tRepo.save(task1);
			tRepo.save(task2);
			tRepo.save(task3);
			tRepo.save(task4);
			tRepo.save(task5);

		};
	}

}
