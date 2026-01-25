package com.sgt.expense_tracker.Mapper;

import com.sgt.expense_tracker.model.User;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        int id = rs.getInt("id");
        String name = rs.getString("name");
        String username = rs.getString("username");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String mobile = rs.getString("mobile");


        user.setId(id);
        user.setEmail(email);
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setMobile(mobile);

        return user;
    }
}
