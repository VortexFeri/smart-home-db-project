//package com.iec3.smarthome.controller;
//
//import com.iec3.smarthome.dto.DeviceDTO;
//import com.iec3.smarthome.entity.Device;
//import com.iec3.smarthome.service.RoomService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "rooms")
//public class RoomJSONController {
//
//    private final RoomService roomService;
//
//    public RoomJSONController(RoomService deviceService) {
//        this.roomService = deviceService;
//    }
//
//    @GetMapping("{id}")
//    public Device getDeviceId(@PathVariable("id") Integer id) {
//        return roomService.;
//    }
//
//    @PostMapping
//    public void addDevice(@RequestBody DeviceDTO device) {
//        roomService.addNewDevice(device);
//    }
//
//    @DeleteMapping("{id}")
//    public void deleteDevice(@PathVariable("id") Integer id) {
//        roomService.deleteDevice(id);
//    }
//
//    // TODO: Update device
//}