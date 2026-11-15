
package com.smartdorm.device;

public class SmartAlarm extends SmartDevice {

    private boolean alarmActive;
    private String alarmType;

    // Constructor
    public SmartAlarm(String deviceId, String deviceName,
                      String alarmType) {

        super(deviceId, deviceName);

        this.alarmType = alarmType;
        this.alarmActive = false;
    }

    // Check alarm status
    public boolean isAlarmActive() {
        return alarmActive;
    }

    // Get alarm type
    public String getAlarmType() {
        return alarmType;
    }

    // Activate emergency alarm
    @Override
    public void activate() {

        alarmActive = true;

        System.out.println("!!! EMERGENCY ALARM ACTIVATED !!!");
        System.out.println("Alarm: " + getDeviceName());
        System.out.println("Type: " + alarmType);
    }

    // Deactivate alarm
    @Override
    public void deactivate() {

        alarmActive = false;

        System.out.println("Emergency alarm deactivated.");
    }

    // Display alarm information
    @Override
    public void displayStatus() {

        System.out.println("\n===== SMART ALARM =====");
        System.out.println("Device ID: " + getDeviceId());
        System.out.println("Device Name: " + getDeviceName());
        System.out.println("Alarm Type: " + alarmType);
        System.out.println("Status: "
                + (alarmActive ? "ACTIVE" : "INACTIVE"));
    }
}
