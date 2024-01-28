package com.iec3.smarthome.controller;

import com.iec3.smarthome.dto.DeviceDTO;
import com.iec3.smarthome.entity.Device;
import com.iec3.smarthome.service.DeviceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public List<Device> listDevices() {
        return deviceService.getDevice();
    }

    @GetMapping("{id}")
    public Device getDeviceId(@PathVariable("id") Integer id) {
        return deviceService.getDevice(id);
    }

    @PostMapping
    public void addDevice(@RequestBody DeviceDTO device) {
        deviceService.addNewDevice(device);
    }

    @DeleteMapping("{id}")
    public void deleteDevice(@PathVariable("id") Integer id) {
        deviceService.deleteDevice(id);
    }

    // TODO: Update device
}