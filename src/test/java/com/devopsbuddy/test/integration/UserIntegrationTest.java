package com.devopsbuddy.test.integration;

import com.devopsbuddy.DevopsbuddyApplication;
import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.persistence.respositories.PlanRepository;
import com.devopsbuddy.backend.persistence.respositories.RoleRepository;
import com.devopsbuddy.backend.persistence.respositories.UserRepository;
import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RolesEnum;
import com.devopsbuddy.utils.UserUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sadap on 3/19/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DevopsbuddyApplication.class)
public class UserIntegrationTest extends AbstractIntegrationTest {


    private static final String ROLE_USER = "admin role";



    @Before
    public void init()
    {
        Assert.assertNotNull(planRepository);
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(roleRepository);

    }


    @Test
    public void testCreateNewPlan() throws Exception {

        Plan basicPlan = createPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);
        Plan retrievePlan = planRepository.findOne(PlansEnum.BASIC.getId());
        Assert.assertNotNull(retrievePlan);



    }


    @Test
    public void testCreateNewRole() throws Exception {

        Role basicRole = createRole(RolesEnum.BASIC);
        roleRepository.save(basicRole);
        Role retrieveRole = roleRepository.findOne(RolesEnum.BASIC.getId());
        Assert.assertNotNull(retrieveRole);



    }



    @Test
    public void testCreateNewUser() throws Exception {

        User basicUser = createUser("testcreatenewuser", "testcreatenewUser@abc.com");
        User newlyCreatedUser = userRepository.findOne(basicUser.getId());
        Assert.assertNotNull(newlyCreatedUser);
        Assert.assertTrue(newlyCreatedUser.getId() != 0);
        Assert.assertNotNull(newlyCreatedUser.getPlan());
        Assert.assertNotNull(newlyCreatedUser.getPlan().getId());

        Set<UserRole> newlyCreatedUserRoles = newlyCreatedUser.getUserRoles();
        for(UserRole ur : newlyCreatedUserRoles)
        {
            Assert.assertNotNull(ur.getRole());
            Assert.assertNotNull(ur.getRole().getId());
        }

    }

    @Test
    public void testDeleteUser()  throws Exception
    {
        User basicUser = createUser("testdeleteuser", "testdeleteuser@abc.com");
        userRepository.delete(basicUser.getId());
    }







}
