package com.iec3.smarthome.room;

import com.iec3.smarthome.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomDataAccessService roomDao;

    public RoomService(RoomDataAccessService roomDao) {
        this.roomDao = roomDao;
    }

    public List<Room> getRoom() {
        return roomDao.selectRooms();
    }

    public void addNewRoom(Room room) {
        // TODO: check if room exists
        int result = roomDao.insertRoom(room);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }
    }

    public void deleteRoom(Integer id) {
        Optional<Room> rooms = roomDao.selectRoomById(id);
        rooms.ifPresentOrElse(room -> {
            int result = roomDao.deleteRoom(id);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete room");
            }
        }, () -> {
            throw new NotFoundException(String.format("Room with id %s not found", id));
        });
    }

    public Room getRoom(int id) {
        return roomDao.selectRoomById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Room with id %s not found", id)));
    }
}
