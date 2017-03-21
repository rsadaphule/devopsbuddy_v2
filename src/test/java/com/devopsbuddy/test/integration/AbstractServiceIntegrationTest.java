package com.devopsbuddy.test.integration;

import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RolesEnum;
import com.devopsbuddy.utils.UserUtils;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sadap on 3/21/2017.
 */
public abstract class AbstractServiceIntegrationTest {

    @Autowired
    protected UserService userService;

    protected User createUser(TestName testName) {
        String userName = testName.getMethodName();
        String userEmail = testName.getMethodName() + "@abc.com";

        Set<UserRole> userRoles = new HashSet<>();

        User basicUser = UserUtils.createBasicUser(userName, userEmail);
        userRoles.add(new UserRole(basicUser, new Role(RolesEnum.BASIC)));

        return userService.createUser(basicUser, PlansEnum.BASIC, userRoles);
    }
}
