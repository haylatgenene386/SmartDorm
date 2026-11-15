
package com.smartdorm.device;

public class SmartDoorLock extends SmartDevice {

    private boolean locked;

    // Constructor
    public SmartDoorLock(String deviceId, String deviceName) {

        super(deviceId, deviceName);

        // Door is locked by default
        this.locked = true;
    }

    // Check door status
    public boolean isLocked() {
        return locked;
    }

    // Activate device: Lock the door
    @Override
    public void activate() {

        locked = true;

        System.out.println(getDeviceName() + " is now LOCKED.");
    }

    // Deactivate device: Unlock the door
    @Override
    public void deactivate() {

        locked = false;

        System.out.println(getDeviceName() + " is now UNLOCKED.");
    }

    // Display current status
    @Override
    public void displayStatus() {

        System.out.println("\n===== SMART DOOR LOCK =====");
        System.out.println("Device ID: " + getDeviceId());
        System.out.println("Device Name: " + getDeviceName());
        System.out.println("Status: " + (locked ? "LOCKED" : "UNLOCKED"));
    }
}
