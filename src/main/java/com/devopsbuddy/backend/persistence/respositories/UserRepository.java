package com.devopsbuddy.backend.persistence.respositories;

import org.springframework.data.repository.CrudRepository;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import org.springframework.stereotype.Repository;

/**
 * Created by sadap on 3/19/2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>
{

    public User findByUsername(String username);

}
