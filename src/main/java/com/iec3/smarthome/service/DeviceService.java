package com.iec3.smarthome.service;

import com.iec3.smarthome.dao.DeviceDAO;
import com.iec3.smarthome.dto.DeviceDTO;
import com.iec3.smarthome.entity.Device;
import com.iec3.smarthome.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceDAO deviceDao;

    public DeviceService(DeviceDAO deviceDao) {
        this.deviceDao = deviceDao;
    }

    public List<Device> getDevice() {
        return deviceDao.getAll();
    }

    public void addNewDevice(DeviceDTO device) {
        // TODO: check if device exists
        int result = deviceDao.insert(new Device(0, device.name(), device.wattage()));
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }
    }

    public void deleteDevice(Integer id) {
        Optional<Device> devices = deviceDao.getById(id);
        devices.ifPresentOrElse(device -> {
            int result = deviceDao.delete(id);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete device");
            }
        }, () -> {
            throw new NotFoundException(String.format("Device with id %s not found", id));
        });
    }

    public Device getDevice(int id) {
        return deviceDao.getById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Device with id %s not found", id)));
    }
}