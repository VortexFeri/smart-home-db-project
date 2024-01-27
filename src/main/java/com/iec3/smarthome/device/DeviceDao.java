package com.iec3.smarthome.device;

import java.util.List;
import java.util.Optional;

public interface DeviceDao {
    List<Device> selectDevices();
    int insertDevice(Device device);
    int deleteDevice(int id);
    Optional<Device> selectDeviceById(int id);
    // TODO: Update
}
