package com.iec3.smarthome.device;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

class DeviceControllerTest {

    @Mock
    private DeviceService deviceService;

    private DeviceController underTest;

    @BeforeEach
    void setUp() {
        underTest = new DeviceController(deviceService);
    }
}