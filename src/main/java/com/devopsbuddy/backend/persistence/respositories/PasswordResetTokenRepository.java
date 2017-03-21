package com.devopsbuddy.backend.persistence.respositories;

import com.devopsbuddy.backend.persistence.domain.backend.PasswordResetToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by sadap on 3/21/2017.
 */
@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    @Query("select ptr from PasswordResetToken ptr inner join ptr.user u where ptr.user.id=?1")
    Set<PasswordResetToken> findAllByUserId(long userId);


}
