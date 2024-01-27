package com.iec3.smarthome.device;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class DeviceRowMapper implements RowMapper<Device> {

    @Override
    public Device mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Device(
                rs.getInt("id"),
                rs.getString("name"),
                Objects.equals(rs.getString("status"), "active"));
    }
}
