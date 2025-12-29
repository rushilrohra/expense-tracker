package com.sgt.expense_tracker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class AuthRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
}
