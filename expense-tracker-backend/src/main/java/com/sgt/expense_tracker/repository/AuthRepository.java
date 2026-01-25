package com.sgt.expense_tracker.repository;

import com.sgt.expense_tracker.Mapper.UserMapper;
import com.sgt.expense_tracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;


@Repository
public class AuthRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addUser(String name,String username, String email, String password, String mobile){
        String query="INSERT INTO Users(name,username,email,password,mobile) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(query, name, username, email, password, mobile);
    }

    public User findByEmail(String email){
        String query = "SELECT id, name, username, email, password, mobile, active_yn FROM Users where email = ?";
        try{
            User user = jdbcTemplate.queryForObject(query,(resultSet,rownum)->{
                User u = new User();
                u.setId(resultSet.getInt("id"));
                u.setName(resultSet.getString("name"));
                u.setUsername(resultSet.getString("username"));
                u.setEmail(resultSet.getString("email"));
                u.setPassword(resultSet.getString("password"));
                u.setMobile(resultSet.getString("mobile"));
                u.setActive_yn(resultSet.getInt("active_yn"));
                return u;
            },email);
            return user;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public User findByUsername(String username){
        String query = "SELECT id, name, username, email, password, mobile, active_yn FROM Users where username = ?";
        try{
            User user = jdbcTemplate.queryForObject(query,(resultSet,rownum)->{
                User u = new User();
                u.setId(resultSet.getInt("id"));
                u.setName(resultSet.getString("name"));
                u.setUsername(resultSet.getString("username"));
                u.setEmail(resultSet.getString("email"));
                u.setPassword(resultSet.getString("password"));
                u.setMobile(resultSet.getString("mobile"));
                u.setActive_yn(resultSet.getInt("active_yn"));
                return u;
            },username);
            return user;
        }catch (EmptyResultDataAccessException e){
            return null;
        }


    }
    public int saveToken(String token, LocalDateTime expiry, int id){
        String query="INSERT INTO Auth_Tokens(token, user_id, expiry) VALUES (?, ?, ?)";

        return jdbcTemplate.update(query, token, id, expiry);
    }

    public Integer validateToken(String token){
        String query="SELECT user_id FROM Auth_Tokens WHERE expiry > CURRENT_TIMESTAMP AND token = token AND used_yn = 0";

        try{
            return jdbcTemplate.queryForObject(query, Integer.class, token);
        }catch(EmptyResultDataAccessException e){
            return null;
        }

    }
}
