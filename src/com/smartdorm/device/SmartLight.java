
package com.smartdorm.device;

public class SmartLight extends SmartDevice {

    private boolean lightOn;
    private int brightness;

    // Constructor
    public SmartLight(String deviceId, String deviceName) {

        super(deviceId, deviceName);

        this.lightOn = false;
        this.brightness = 0;
    }

    // Check whether light is on
    public boolean isLightOn() {
        return lightOn;
    }

    // Get brightness level
    public int getBrightness() {
        return brightness;
    }

    // Activate device: Turn light ON
    @Override
    public void activate() {

        lightOn = true;
        brightness = 100;

        System.out.println(getDeviceName()
                + " is now ON at 100% brightness.");
    }

    // Deactivate device: Turn light OFF
    @Override
    public void deactivate() {

        lightOn = false;
        brightness = 0;

        System.out.println(getDeviceName() + " is now OFF.");
    }

    // Set brightness level
    public void setBrightness(int brightness) {

        if (brightness < 0 || brightness > 100) {

            System.out.println(
                    "Invalid brightness! Enter a value from 0 to 100.");

            return;
        }

        this.brightness = brightness;
        this.lightOn = brightness > 0;

        System.out.println("Brightness set to "
                + brightness + "%.");
    }

    // Display device status
    @Override
    public void displayStatus() {

        System.out.println("\n===== SMART LIGHT =====");
        System.out.println("Device ID: " + getDeviceId());
        System.out.println("Device Name: " + getDeviceName());
        System.out.println("Light Status: "
                + (lightOn ? "ON" : "OFF"));
        System.out.println("Brightness: " + brightness + "%");
    }
}
