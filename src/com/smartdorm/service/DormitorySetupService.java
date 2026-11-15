
package com.smartdorm.service;

import com.smartdorm.model.Block;
import com.smartdorm.model.Room;

public class DormitorySetupService {

    // Create rooms and distribute them across building floors
    public boolean configureRooms(
            Block block,
            int numberOfRooms,
            int firstFloor,
            int lastFloor) {

        if (block == null
                || numberOfRooms <= 0
                || firstFloor < 0
                || lastFloor < firstFloor) {

            System.out.println("Invalid building configuration.");
            return false;
        }

        // Avoid generating duplicate rooms
        if (!block.getRooms().isEmpty()) {
            System.out.println(
                    "Rooms have already been configured for "
                    + block.getBlockName()
            );
            return false;
        }

        int totalFloors = lastFloor - firstFloor + 1;

        // Generate rooms
        for (int i = 1; i <= numberOfRooms; i++) {

            int floorNumber =
                    firstFloor + ((i - 1) % totalFloors);

            String roomNumber =
                    block.getBlockName().replaceAll("[^A-Za-z0-9]", "")
                    + "-" + String.format("%03d", i);

            Room room = new Room(roomNumber, floorNumber);

            if (!block.addRoom(room)) {
                System.out.println(
                        "Could not add room: " + roomNumber
                );
                return false;
            }
        }

        System.out.println(
                "\nRooms configured successfully!"
        );

        System.out.println(
                "Building: " + block.getBlockName()
        );

        System.out.println(
                "Total Rooms: " + block.getNumberOfRooms()
        );

        System.out.println(
                "Total Floors: " + totalFloors
        );

        System.out.println(
                "Physical Bed Capacity: "
                + block.getTotalRoomCapacity()
        );

        return true;
    }
}
