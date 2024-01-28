package com.iec3.smarthome.dao;

import com.iec3.smarthome.entity.Device;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class DeviceDAO implements Dao<Device> {
    private final JdbcTemplate jdbcTemplate;

    public DeviceDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Device> getAll() {
        var sql = """
                SELECT id, device_name, device_wattage
                FROM device
                LIMIT 100
                """;
        return jdbcTemplate.query(sql, new DeviceRowMapper());
    }

    @Override
    public int insert(Device device) {
        var sql = """
                INSERT INTO device(device_name, device_wattage)
                VALUES (?, ?);
                """;
        return jdbcTemplate.update(
                sql,
                device.name(),
                device.wattage());
    }

    @Override
    public int delete(int id) {
        var sql = """
                DELETE FROM device
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int update(int id, Device device) {
        // TODO: update
        return 0;
    }

    @Override
    public Optional<Device> getById(int id) {
        var sql = """
                SELECT id, device_name, device_wattage
                FROM device
                WHERE id = ?
                """;
        return jdbcTemplate.query(sql, new DeviceRowMapper(), id)
                .stream()
                .findFirst();
    }

    public static class DeviceRowMapper implements RowMapper<Device> {

        @Override
        public Device mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Device(
                    rs.getInt("id"),
                    rs.getString("device_name"),
                    rs.getInt("device_wattage"));
        }
    }
}