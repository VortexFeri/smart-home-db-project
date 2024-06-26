package com.iec3.smarthome.dao;

import com.iec3.smarthome.entity.Device;
import com.iec3.smarthome.entity.Room;
import com.iec3.smarthome.entity.RoomDeviceList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RoomDeviceListDAO implements Dao<RoomDeviceList,Integer> {
    private final JdbcTemplate jdbcTemplate;
    private final DeviceDAO deviceDAO;
    private final RoomDAO roomDAO;
    public RoomDeviceListDAO(JdbcTemplate jdbcTemplate, DeviceDAO deviceDao, RoomDAO roomDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.deviceDAO = deviceDao;
        this.roomDAO = roomDAO;
    }

    @Override
    public int insert(RoomDeviceList roomDeviceList) {
        var sql = """
                INSERT INTO devices_in_room(room_id, device_id, device_count)
                VALUES (?, ?, ?);
                """;
        AtomicInteger affectedRows = new AtomicInteger();
        roomDeviceList.getDeviceMap().forEach( (device, c) ->
            affectedRows.addAndGet(jdbcTemplate.update(
                    sql,
                    roomDeviceList.getRoom().getId(),
                    device.id(),
                    c))
        );
        return affectedRows.get();
    }

    @Override
    public int delete(Integer id) {
        var sql = """
                DELETE FROM devices_in_room
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int update(Integer id, RoomDeviceList roomDeviceList) {
        // TODO: update
        return 0;
    }

    @Override
    public List<RoomDeviceList> getAll() {
        // TODO: implement
        return new ArrayList<RoomDeviceList>();
    }

    @Override
    public Optional<RoomDeviceList> getById(Integer id) {
        var sql = """
                SELECT room_id, device_id, device_count
                FROM devices_in_room
                WHERE room_id=?;
                """;
        return jdbcTemplate.query(sql, (rs, count) -> {
            Optional<Room> room = roomDAO.getById(rs.getInt("room_id"));
            Map<Device, Integer> map = new HashMap<>();
            do {
                if (rs.getInt("room_id") == id) {
                    Device device = deviceDAO.getById(rs.getInt("device_id")).get();
                    map.put(device, rs.getInt("device_count"));
                }
            } while (rs.next());

            return new RoomDeviceList(room.get(), map);
        },id).stream().findFirst();
    }

    public int removeDevice(int room_id, int device_id) {
        var sql = """
                DELETE FROM devices_in_room
                WHERE room_id = ? AND device_id = ?;
                """;
        return jdbcTemplate.update(sql, room_id, device_id);
    }

    public int editDevice(int roomId, int deviceId, int newVal) {
        var sql = """
                UPDATE devices_in_room
                SET device_count = ?
                WHERE room_id = ? AND device_id = ?;
                """;
        return jdbcTemplate.update(sql, newVal, roomId, deviceId);
    }

    public int addDevice(int room_id, int device_id) {
        var sql = """
                INSERT INTO devices_in_room(room_id, device_id, device_count)
                VALUES (?, ?, 1);
                """;
        return jdbcTemplate.update(sql, room_id, device_id);
    }
}
