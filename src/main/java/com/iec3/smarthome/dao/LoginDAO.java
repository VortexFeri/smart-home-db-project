package com.iec3.smarthome.dao;

import com.iec3.smarthome.entity.Device;
import com.iec3.smarthome.entity.Login;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Repository
public class LoginDAO implements Dao<Login,String> {

    private final JdbcTemplate jdbcTemplate;

    public LoginDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(Login login) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public int update(String id, Login login) {
        return 0;
    }

    @Override
    public List getAll() {
        return null;
    }


    @Override
    public Optional<Login> getById(String id) {
        var sql = """
                SELECT user, password
                FROM user
                WHERE user = ?
                """;
        return jdbcTemplate.query(sql, new LoginDAO.LoginRowMapper(), id)
                .stream()
                .findFirst();

    }
    public static class LoginRowMapper implements RowMapper<Login> {

        @Override
        public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Login(
                    rs.getString("user"),
                    rs.getString("password"));

        }
    }
}
