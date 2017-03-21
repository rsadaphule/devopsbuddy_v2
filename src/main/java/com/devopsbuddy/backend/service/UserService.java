package com.devopsbuddy.backend.service;

import com.devopsbuddy.backend.persistence.domain.backend.PasswordResetToken;
import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.persistence.respositories.PasswordResetTokenRepository;
import com.devopsbuddy.backend.persistence.respositories.PlanRepository;
import com.devopsbuddy.backend.persistence.respositories.RoleRepository;
import com.devopsbuddy.backend.persistence.respositories.UserRepository;
import com.devopsbuddy.enums.PlansEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.Set;


/**
 * Created by sadap on 3/20/2017.
 */

@Service
@Transactional( readOnly = true)
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Transactional
    public User createUser(User user, PlansEnum plansEnum, Set<UserRole> userRoles)
    {
        Plan plan = new Plan(plansEnum);
        if(!planRepository.exists(plansEnum.getId()))
        {
            plan = planRepository.save(plan);
        }

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        user.setPlan(plan);

        for(UserRole ur: userRoles)
        {
            roleRepository.save(ur.getRole());
        }
        user.getUserRoles().addAll(userRoles);
        if(!userRepository.exists(user.getId()))
        {
            user = userRepository.save(user);
        }
        return user;



    }


    @Transactional
    public void updateUserPassword(long userId, String password) {
        password = passwordEncoder.encode(password);
        userRepository.updateUserPassword(userId, password);
        LOG.debug("Password updated successfully for user id {} ", userId);

        Set<PasswordResetToken> resetTokens = passwordResetTokenRepository.findAllByUserId(userId);
        if (!resetTokens.isEmpty()) {
            passwordResetTokenRepository.delete(resetTokens);
        }
    }





}
