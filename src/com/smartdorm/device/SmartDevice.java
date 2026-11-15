
package com.smartdorm.device;

public abstract class SmartDevice {

    private String deviceId;
    private String deviceName;

    // Constructor
    public SmartDevice(String deviceId, String deviceName) {

        this.deviceId = deviceId;
        this.deviceName = deviceName;
    }

    // Getters
    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    // Abstract methods
    public abstract void activate();

    public abstract void deactivate();

    public abstract void displayStatus();
}
