package ie.conor;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import ie.conor.entities.Job;
import ie.conor.entities.MyApiUser;
import ie.conor.entities.MyUser;
import ie.conor.entities.Role;
import ie.conor.entities.Bid;
import ie.conor.services.JobService;
import ie.conor.services.MyApiUserService;
import ie.conor.services.MyUserService;
import ie.conor.services.RoleService;
import ie.conor.services.BidService;

@SpringBootApplication
@EnableScheduling
public class WebMvcApplication  {

	public static void main(String[] args) {
		SpringApplication.run(WebMvcApplication.class, args);
	}

	@Autowired
	private MyUserService myUserService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired 
	private MyApiUserService myApiUserService;

	@Autowired
	private JobService jobService;
	
	@Autowired
	private BidService bidService;
	
	@PostConstruct	 // Another new annotation for you...
	public void loadData() {
		Role role1 = new Role("mickey.mouse@cit.ie", "ROLE_USER");
		Role role2 = new Role("minnie.mouse@cit.ie", "ROLE_API");
		Role role3 = new Role("donald.duck@cit.ie", "ROLE_ADMIN");
		roleService.save(role1);
		roleService.save(role2);
		roleService.save(role3);

        MyUser user1 = new MyUser("mickey.mouse@cit.ie", passwordEncoder.encode("password"), role1,true, "Minnie", "Mouse");
		MyApiUser user2 = new MyApiUser("minnie.mouse@cit.ie", passwordEncoder.encode("password"), role2, true);
		MyUser user3 = new MyUser("donald.duck@cit.ie", passwordEncoder.encode("password"), role3, true, "Donald", "Duck");
		myUserService.save(user1);
		myApiUserService.save(user2);
		myUserService.save(user3);
		
		Job roofing = new Job("Roofing", "roofing job", LocalDate.now(), user3);		
		jobService.save(roofing);
		Job construction = new Job("Construction","construction job", LocalDate.now(), user3);
		jobService.save(construction);
		
		Job tiling = new Job("Tiling","tiling job", LocalDate.now(), user3);
		jobService.save(tiling);
		
		bidService.save(new Bid(50, construction));
		bidService.save(new Bid(75, construction));
		bidService.save(new Bid(80, construction));
		bidService.save(new Bid(45, construction));
		
		bidService.save(new Bid(1000, roofing));
		bidService.save(new Bid(975, roofing));
		bidService.save(new Bid(780, roofing));
		bidService.save(new Bid(760, roofing));
		
		bidService.save(new Bid(430, tiling));
		bidService.save(new Bid(425, tiling));
		bidService.save(new Bid(385, tiling));
		bidService.save(new Bid(350, tiling));
	}
}