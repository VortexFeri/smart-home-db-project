package com.iec3.smarthome.dao;

import com.iec3.smarthome.entity.Room;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class RoomDAO implements Dao<Room> {
    private final JdbcTemplate jdbcTemplate;

    public RoomDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Room> getAll() {
        var sql = """
                SELECT id, room_type, room_display_name
                FROM room
                LIMIT 100
                """;
        return jdbcTemplate.query(sql, new RoomRowMapper());
    }

    @Override
    public int insert(Room room) {
        var sql = """
                INSERT INTO room(room_type, room_display_name)
                VALUES (?, ?);
                """;
        return jdbcTemplate.update(
                sql,
                room.getType(),
                room.getName());
    }

    @Override
    public int delete(int id) {
        var sql = """
                DELETE FROM room
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int update(int id, Room room) {
        // TODO: implement update
        return 0;
    }

    @Override
    public Optional<Room> getById(int id) {
        var sql = """
                SELECT id, room_type, room_display_name
                FROM room
                WHERE id = ?
                """;
        return jdbcTemplate.query(sql, new RoomRowMapper(), id)
                .stream()
                .findFirst();
    }

    public static class RoomRowMapper implements RowMapper<Room> {

        @Override
        public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Room(
                    Room.RoomType.valueOf(rs.getString("room_type").toUpperCase()),
                    rs.getInt("id"),
                    rs.getString("room_display_name"));
        }
    }
}
