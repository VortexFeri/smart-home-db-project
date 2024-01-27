package com.iec3.smarthome.room;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomRowMapper implements RowMapper<Room> {

    @Override
    public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Room(
                rs.getInt("id"),
                rs.getString("type"),
                rs.getDouble("total_usage"));
    }
}
