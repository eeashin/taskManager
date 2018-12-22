package fi.eashin.taskManager.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.eashin.taskManager.domain.Priority;
import fi.eashin.taskManager.domain.PriorityRepository;
import fi.eashin.taskManager.domain.SignUp;
import fi.eashin.taskManager.domain.Status;
import fi.eashin.taskManager.domain.StatusRepository;
import fi.eashin.taskManager.domain.Task;
import fi.eashin.taskManager.domain.TaskRepository;
import fi.eashin.taskManager.domain.User;
import fi.eashin.taskManager.domain.UserRepository;

@Controller
public class TaskController {

	@Autowired
	private TaskRepository tRepo;

	@Autowired
	private PriorityRepository pRepo;

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private StatusRepository sRepo;

	// user login as authentication process

	@RequestMapping(value = { "/", "/login" })
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/signup")
	public String addUser(Model model) {
		model.addAttribute("signUp", new SignUp());
		return "signup";
	}

	/**
	 * Create new user Check if user already exists & form validation
	 * 
	 * @param signUp
	 * @param bindingResult
	 * @return
	 */
	@PostMapping(value = "/saveuser")
	public String save(@Valid @ModelAttribute("signUp") SignUp signUp, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) { // validation errors
			if (signUp.getPassword().equals(signUp.getPasswordCheck())) { // check password match
				String pwd = signUp.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				User newUser = new User();
				newUser.setPasswordHash(hashPwd);
				newUser.setUsername(signUp.getUsername());
				newUser.setRole("USER");
				if (uRepo.findByUsername(signUp.getUsername()) == null) { // Check if user exists
					uRepo.save(newUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Error to create user");
					return "signup";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "signup";
			}
		} else {
			return "signup";
		}
		return "redirect:/login";
	}

	// Show all tasks
	@RequestMapping(value = "/tasklist")
	public String taskList(Model model) {
		model.addAttribute("tasks", tRepo.findAll());
		return "tasklist";
	}

	// Add new task
	@GetMapping("/add")
	public String addTask(Model model) {
		model.addAttribute("task", new Task());
		model.addAttribute("priorities", pRepo.findAll());
		model.addAttribute("usernames", uRepo.findAll());
		model.addAttribute("statuses", sRepo.findAll());

		return "addtask";
	}

	// Save new task
	@PostMapping(value = "/save")
	public String save(Task task) {
		tRepo.save(task);
		return "redirect:tasklist";
	}

	// Delete Task
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/delete/{id}")
	public String deleteTask(@PathVariable("id") Long taskId, Model model) {
		tRepo.deleteById(taskId);
		return "redirect:/tasklist";
	}

	// Edit a task
	@RequestMapping(value = "/edit/{id}")
	public String editTask(@PathVariable("id") Long taskId, Model model) {
		model.addAttribute("task", tRepo.findById(taskId));
		model.addAttribute("priorities", pRepo.findAll());
		model.addAttribute("usernames", uRepo.findAll());
		model.addAttribute("statuses", sRepo.findAll());
		return "edittask";
	}

	// Search by task name
	@GetMapping(value = "/search")
	public String searchName(@RequestParam(name = "bytaskName") String bytaskName, Model model) {
		model.addAttribute("tasks", tRepo.findByTaskNameIgnoreCaseContaining(bytaskName));
		return "tasklist";
	}

	/***
	 * RESTful services
	 ***/

	// RESTful service to get all tasks

	@GetMapping("/tasks")
	public @ResponseBody List<Task> taskList() {
		return (List<Task>) tRepo.findAll();

	}

	// RESTful service to get task by id
	@GetMapping("/tasks/{id}")
	public @ResponseBody Optional<Task> findTaskRest(@PathVariable("id") Long taskId) {
		return tRepo.findById(taskId);
	}

	// RESTful service to get all users data
	@GetMapping(value = "/users")
	public @ResponseBody List<User> getAllUsers() {
		return (List<User>) uRepo.findAll();
	}

	// RESTful service to get individual user's data by id
	@GetMapping(value = "/users/{id}")
	public @ResponseBody Optional<User> findUserRest(@PathVariable("id") Long userId) {
		return uRepo.findById(userId);
	}

	// RESTful service to get priority list
	@GetMapping(value = "/priorities")
	public @ResponseBody List<Priority> getAllPriority() {
		return (List<Priority>) pRepo.findAll();
	}

	// RESTful service to get statuses list
	@GetMapping(value = "/statuses")
	public @ResponseBody List<Status> getAllStatuses() {
		return (List<Status>) sRepo.findAll();
	}

}
