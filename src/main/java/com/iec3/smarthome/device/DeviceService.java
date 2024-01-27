package com.iec3.smarthome.device;

import com.iec3.smarthome.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceDao deviceDao;

    public DeviceService(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    public List<Device> getDevice() {
        return deviceDao.selectDevices();
    }

    public void addNewDevice(Device device) {
        // TODO: check if device exists
        int result = deviceDao.insertDevice(device);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }
    }

    public void deleteDevice(Integer id) {
        Optional<Device> devices = deviceDao.selectDeviceById(id);
        devices.ifPresentOrElse(device -> {
            int result = deviceDao.deleteDevice(id);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete device");
            }
        }, () -> {
            throw new NotFoundException(String.format("Device with id %s not found", id));
        });
    }

    public Device getDevice(int id) {
        return deviceDao.selectDeviceById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Device with id %s not found", id)));
    }
}
