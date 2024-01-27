package com.iec3.smarthome.device;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DeviceDataAccessService implements DeviceDao {
    private final JdbcTemplate jdbcTemplate;

    public DeviceDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Device> selectDevices() {
        var sql = """
                SELECT id, name, status
                FROM device
                LIMIT 100
                """;
        return jdbcTemplate.query(sql, new DeviceRowMapper());
    }

    @Override
    public int insertDevice(Device device) {
        var sql = """
                INSERT INTO device(name, status)
                VALUES (?, ?);
                """;
        return jdbcTemplate.update(
                sql,
                device.name(),
                device.active() ? "active" : "inactive");
    }

    @Override
    public int deleteDevice(int id) {
        var sql = """
                DELETE FROM device
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Device> selectDeviceById(int id) {
        var sql = """
                SELECT id, name
                FROM device
                WHERE id = ?
                """;
        return jdbcTemplate.query(sql, new DeviceRowMapper(), id)
                .stream()
                .findFirst();
    }
}
