package com.devopsbuddy.backend.persistence.respositories;

import org.springframework.data.repository.CrudRepository;
import com.devopsbuddy.backend.persistence.domain.backend.Role;
import org.springframework.stereotype.Repository;

/**
 * Created by sadap on 3/19/2017.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>
{


}
