package fi.eashin.taskManager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.eashin.taskManager.domain.User;
import fi.eashin.taskManager.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	@Autowired
	private UserRepository uRepo;

	@Test
	public void findByUsernameShouldReturnUser() {
		User users = uRepo.findByUsername("user");
		assertThat(users.getUsername()).isEqualTo("user");
	}

	@Test
	public void createUser() {
		User user3 = new User("eashin", "$2a$04$W9/8o3R2mZ/fZ4DbsHXtWu4v1atTMvNFPdlqezfV6w0ZLT92VrnvC", "USER");
		uRepo.save(user3);
		assertThat(user3.getUserId()).isNotNull();
	}

}
