package com.iec3.smarthome.dto;

import java.util.HashMap;

public record RoomListDTO(int roomID, String roomName, HashMap<String, Integer> map) {};
