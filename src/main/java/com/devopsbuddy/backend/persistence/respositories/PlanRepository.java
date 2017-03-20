package com.devopsbuddy.backend.persistence.respositories;

import org.springframework.data.repository.CrudRepository;
import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import org.springframework.stereotype.Repository;

/**
 * Created by sadap on 3/19/2017.
 */
@Repository
public interface PlanRepository extends CrudRepository<Plan, Integer>
{


}
