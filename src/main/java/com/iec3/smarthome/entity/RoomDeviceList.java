package com.iec3.smarthome.entity;

import java.util.Map;

public class RoomDeviceList {
    private Room room;
    private Map<Device, Integer> deviceMap;

    public RoomDeviceList(Room room, Map<Device, Integer> deviceMap) {
        this.room = room;
        this.deviceMap = deviceMap;
    }

    public Room getRoom() {
        return room;
    }

    public Map<Device, Integer> getDeviceMap() {
        return deviceMap;
    }

    public Device findDeviceById(int id) {
        for (Device device : deviceMap.keySet()) {
            if (device.id() == id) {
                return device;
            }
        }
        return null;
    }

    public int getCount(int device_id) {
        return deviceMap.get(findDeviceById(device_id));
    }

}
