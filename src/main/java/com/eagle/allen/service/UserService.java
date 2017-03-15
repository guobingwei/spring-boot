package com.eagle.allen.service;

import com.eagle.allen.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by john on 2016/3/16.
 */
@Repository
public class UserService {
    private final JdbcTemplate jdbcTemplate;
    static Logger log = LoggerFactory.getLogger(UserService.class);


    @Autowired
    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List getAll() {
        String sql = "SELECT * FROM gb_boot_user";

        List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                log.info("user info - " + user);
                return user;
            }
        });

        return users;
    }

    public void insertData(User user) {
        String sql = "INSERT INTO gb_boot_user "
                + "(user_id, name) VALUES (?,?)";

        jdbcTemplate.update(sql, new Object[] { user.getId(), user.getName()});
    }
}
