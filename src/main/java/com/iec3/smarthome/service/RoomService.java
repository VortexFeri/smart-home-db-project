package com.iec3.smarthome.service;

import com.iec3.smarthome.dao.RoomDAO;
import com.iec3.smarthome.dao.RoomDeviceListDAO;
import com.iec3.smarthome.entity.Room;
import com.iec3.smarthome.entity.RoomDeviceList;
import com.iec3.smarthome.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomDAO roomDao;
    private final RoomDeviceListDAO roomDeviceListDAO;

    public RoomService(RoomDAO roomDao, RoomDeviceListDAO roomDeviceListDAO) {
        this.roomDao = roomDao;
        this.roomDeviceListDAO = roomDeviceListDAO;
    }

    public List<Room> getRoom() {
        return roomDao.getAll();
    }

    public void addNewRoom(Room room) {
        // TODO: check if room exists
        int result = roomDao.insert(room);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }
    }

    public void deleteRoom(Integer id) {
        Optional<Room> rooms = roomDao.getById(id);
        rooms.ifPresentOrElse(room -> {
            int result = roomDao.delete(id);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete room");
            }
        }, () -> {
            throw new NotFoundException(String.format("Room with id %s not found", id));
        });
    }

    public Room getRoom(int id) {
        return roomDao.getById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Room with id %s not found", id)));
    }

    public RoomDeviceList getDevices(int id) {
        roomDao.getById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Room with id %s not found", id)));
        return roomDeviceListDAO.getById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Room with id %s empty", id)));
    }
}