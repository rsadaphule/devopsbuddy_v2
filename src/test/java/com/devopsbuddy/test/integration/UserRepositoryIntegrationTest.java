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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by sadap on 3/19/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =  DevopsbuddyApplication.class)
public class UserRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Rule
    public TestName testName = new TestName();

    private static final String ROLE_USER = "admin role";

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


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

    @Test
    public void testFindUserbyEmail() throws Exception {

        User basicUser = createUser(testName);
        User newlyCreatedUser = userRepository.findByEmail(basicUser.getEmail());
        Assert.assertNotNull(newlyCreatedUser);
        Assert.assertTrue(newlyCreatedUser.getId() != 0);


    }


    @Test
    public void testUpdateUserPassword() throws Exception {
        User user = createUser(testName);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());

        String newPassword = UUID.randomUUID().toString();

        userRepository.updateUserPassword(user.getId(), newPassword);

        user = userRepository.findOne(user.getId());
        Assert.assertEquals(newPassword, user.getPassword());

    }










}
