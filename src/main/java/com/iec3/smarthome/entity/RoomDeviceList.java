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
}
