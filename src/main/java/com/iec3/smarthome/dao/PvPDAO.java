package com.iec3.smarthome.dao;

import com.iec3.smarthome.entity.PvP;
import com.iec3.smarthome.entity.Room;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Repository
public class PvPDAO implements Dao<PvP,Integer> {
    private final JdbcTemplate jdbcTemplate;

    public PvPDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public int insert(PvP pvP) {
        return 0;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public int update(Integer id, PvP pvP) {
        return 0;
    }

    @Override
    public List<PvP> getAll() {
        var sql = """
                SELECT month,monthly_production,monthly_usage
                FROM pv_panel
                LIMIT 100
                """;
        return jdbcTemplate.query(sql, new PvPRowMapper());

    }

    @Override
    public Optional<PvP> getById(Integer id) {
        var sql = """
                SELECT month,monthly_production,monthly_usage
                FROM pv_panel
                WHERE month = ?
                """;
        return jdbcTemplate.query(sql, new PvPRowMapper(), id)
                .stream()
                .findFirst();

    }

    public static class PvPRowMapper implements RowMapper<PvP> {
        @Override
        public PvP mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new PvP(
                    rs.getInt("month"),
                    rs.getFloat("monthly_production"),
                    rs.getFloat("monthly_usage"));
        }

        }
    }

