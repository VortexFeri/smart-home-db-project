package com.iec3.smarthome.room;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> listRooms() {
        return roomService.getRoom();
    }

    @GetMapping("{id}")
    public Room getRoomId(@PathVariable("id") Integer id) {
        return roomService.getRoom(id);
    }

    @PostMapping
    public void addRoom(@RequestBody Room room) {
        roomService.addNewRoom(room);
    }

    @DeleteMapping("{id}")
    public void deleteRoom(@PathVariable("id") Integer id) {
        roomService.deleteRoom(id);
    }

    // TODO: Update room
}
