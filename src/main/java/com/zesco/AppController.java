package com.zesco;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AppController {
	
	private final UserService userService;
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private EquipmentsRepository equiRepo;
	
	@Autowired
	private IssuesRepository issuesRepo;
	
	@GetMapping("")
	public String viewHomePage(){
		return "index";
	}
	
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegistration (User user) {
		
		BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		repo.save(user);
		
		return "register_success";
		
	}
	
	@GetMapping("list_users")
	public String viewUsersList(Model model) {
		List<User> listUsers = repo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
	
	@GetMapping("/open_dashboard")
	public String openDashboard() {
		
		return "dashboard";
	}
	
	
	@GetMapping("/equipments")
	public String openEquipment(Model model) {
		model.addAttribute("equipments", new Equipments());
		
		List<Equipments> listEquipments = equiRepo.findAll();
		model.addAttribute("listEquipments", listEquipments);
		
		
		return "equipments";
		
	}
	
	@GetMapping("/about")
	public String viewAboutUs() {
		return "about";
	}
	
	@PostMapping("/process_equipments")
	public String processEquipments(Equipments equipments) {
		equiRepo.save(equipments);
		
		return "equipment_added";
	}
	
	@GetMapping("/issues")
	public String viewIssues(Model model) {
		model.addAttribute("issues", new Issues());
		
		List<Issues> listIssues = issuesRepo.findAll(); 
		model.addAttribute("listIssues",listIssues);
		return "issues";
	}
	
	@PostMapping("/process_issues")
	public String processIssues(Issues issues) {
		issuesRepo.save(issues);
		
		return "issue_added";
	}
	
	@ModelAttribute("listEquipments")
	public List <Equipments> getEquipments() {
		List <Equipments> listEquip = equiRepo.findAll();
		
		return listEquip;
		
	}
	
	@Autowired
	public AppController(UserService userService) {
		
		this.userService= userService;
	}
	

	@GetMapping("/process_register")
	public String returnError(User user) {
		
		if (userService.userExists(user.getEmail())) {
			return "error";
		}
		
		return "index";
		
	}
	
}
