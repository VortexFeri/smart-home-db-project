package com.iec3.smarthome.room;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoomDataAccessService {
    private final JdbcTemplate jdbcTemplate;

    public RoomDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Room> selectRooms() {
        var sql = """
                SELECT id, type, total_usage
                FROM room
                LIMIT 100
                """;
        return jdbcTemplate.query(sql, new RoomRowMapper());
    }

    public int insertRoom(Room room) {
        var sql = """
                INSERT INTO room(type, total_usage)
                VALUES (?, ?);
                """;
        return jdbcTemplate.update(
                sql,
                room.name(),
                room.total_usage());
    }

    public int deleteRoom(int id) {
        var sql = """
                DELETE FROM room
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, id);
    }

    public Optional<Room> selectRoomById(int id) {
        var sql = """
                SELECT id, type, total_usage
                FROM room
                WHERE id = ?
                """;
        return jdbcTemplate.query(sql, new RoomRowMapper(), id)
                .stream()
                .findFirst();
    }
}
