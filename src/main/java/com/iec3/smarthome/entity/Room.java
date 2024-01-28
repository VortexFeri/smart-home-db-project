package com.iec3.smarthome.entity;

public class Room {
    public enum RoomType {
        BEDROOM, BATHROOM, KITCHEN, LIVING_ROOM, OTHER
    }
    private final RoomType type;
    private final int id;
    private final String name;

    public Room(RoomType type, int id, String name) {
        this.type = type;
        this.id = id;
        this.name = name;
    }

    public RoomType getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Room{" +
                "type=" + type +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
