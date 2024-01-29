package com.iec3.smarthome.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iec3.smarthome.entity.Room;
import com.iec3.smarthome.entity.RoomDeviceList;
import com.iec3.smarthome.service.DeviceService;
import com.iec3.smarthome.service.PvPService;
import com.iec3.smarthome.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "rooms")
public class RoomController {

    private final PvPService pvPService;
    private final RoomService roomService;
    private final DeviceService deviceService;

    @Autowired
    private ObjectMapper objectMapper;

    public RoomController(RoomService roomService, DeviceService deviceService, PvPService pvPService) {
        this.pvPService = pvPService;
        this.roomService = roomService;
        this.deviceService = deviceService;
    }

    @GetMapping
    public String listRooms(Model model) {
        var DeviceL=new ArrayList<RoomDeviceList>();
        var allRooms = roomService.getRoom();
        allRooms.forEach(room -> DeviceL.add(roomService.getDevices(room.getId())));
        model.addAttribute("list", DeviceL);
        model.addAttribute("pvpList",pvPService.getPvP());
        return "rooms";
    }

    @GetMapping("{id}")
    public String getRoomId(@PathVariable("id") Integer id, Model model) {
        RoomDeviceList devicesList = roomService.getDevices(id);
        model.addAttribute("devicesList", devicesList);
        model.addAttribute("roomid", id);
        return "room";
    }

    @PostMapping
    public void addRoom(@RequestBody Room room) {
        roomService.addNewRoom(room);
    }

    @DeleteMapping("{roomid}/delete-device/{id}")
    public String deleteRoom(@PathVariable("id") Integer id) {
        //roomService.deleteRoom(id);
        return "redirect:/Deleted";
    }

    @PutMapping("{roomid}/edit-device/{id}")
    public String editRoomDevice(
            @PathVariable("roomid") Integer room_id,
            @PathVariable("id") Integer device_id,
            @RequestBody String body) throws JsonProcessingException {
                Integer newVal = objectMapper.readValue(body, Integer.class);
                roomService.editDevice(room_id, device_id, newVal);
                return "redirect:/rooms";
    }
}