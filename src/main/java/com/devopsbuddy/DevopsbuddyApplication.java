package com.devopsbuddy;

import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.service.PlanService;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RolesEnum;
import com.devopsbuddy.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DevopsbuddyApplication implements CommandLineRunner
{
	private static final Logger LOG = LoggerFactory.getLogger(DevopsbuddyApplication.class);


	@Autowired
	private UserService userService;

	@Autowired
	private PlanService planService;

	@Value("${webmaster.username}")
	private String webmasterUsername;

	@Value("${webmaster.password}")
	private String webmasterPassword;

	@Value("${webmaster.email}")
	private String webmasterEmail;



	public static void main(String[] args) {
		SpringApplication.run(DevopsbuddyApplication.class, args);
	}


    @Override
	public void run(String... strings) throws Exception {
		LOG.info("Creating basic and pro plans");
		planService.createPlan(PlansEnum.BASIC.getId());
		planService.createPlan(PlansEnum.PRO.getId());

		User user = UserUtils.createBasicUser(this.webmasterUsername, this.webmasterEmail);
		user.setPassword(webmasterPassword);
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, new Role(RolesEnum.ADMIN)));
		LOG.debug("creating user withusername {}", user.getUsername());
		userService.createUser(user, PlansEnum.PRO, userRoles);
		LOG.info("user {} created ", user.getUsername());

	}

}
