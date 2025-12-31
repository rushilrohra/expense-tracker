package com.sgt.expense_tracker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public class AuthRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addUser(String name,String username, String email, String password, String mobile){
        String query="INSERT INTO Users(name,username,email,password,mobile) VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(query, name, username, email, password, mobile);
    }

    public boolean emailAlreadyExists(String email){
        String query = "SELECT count(id) as count FROM Users where email = ?";

        Map<String,Object> result = jdbcTemplate.queryForMap(query,email);
        return (long) result.get("count") > 0;
    }

    public boolean usernameAlreadyExists(String username){
        String query = "SELECT count(id) as count FROM Users where username = ?";

        Map<String,Object> result = jdbcTemplate.queryForMap(query,username);
        return (long) result.get("count") > 0;
    }
}
