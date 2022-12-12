package com.nest.nestdigitalappbackend.dao;

import com.nest.nestdigitalappbackend.model.LeaveCount;
import org.springframework.data.repository.CrudRepository;

public interface LeaveCountDao extends CrudRepository<LeaveCount, Integer> {
}
