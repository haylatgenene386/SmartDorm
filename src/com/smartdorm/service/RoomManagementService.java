
package com.smartdorm.service;

import java.util.List;

import com.smartdorm.model.Dormitory;
import com.smartdorm.model.DormitorySection;
import com.smartdorm.model.Block;
import com.smartdorm.model.Room;

import com.smartdorm.enums.SectionType;

public class RoomManagementService {

    // Display all buildings in the dormitory
    public void displayAllBuildings(Dormitory dormitory) {

        if (dormitory == null) {
            System.out.println("Dormitory information unavailable.");
            return;
        }

        System.out.println("\n===== ALL DORMITORY BUILDINGS =====");

        displayBuildings(dormitory.getMaleSection());
        displayBuildings(dormitory.getFemaleSection());
    }

    // Display buildings in a particular section
    public void displayBuildings(DormitorySection section) {

        if (section == null) {
            System.out.println("Section not found.");
            return;
        }

        System.out.println("\n===== "
                + section.getSectionType()
                + " BUILDINGS =====");

        for (Block block : section.getBlocks()) {

            System.out.println("\nBuilding: "
                    + block.getBlockName());

            System.out.println("Status: "
                    + block.getStatus());

            System.out.println("Total Rooms: "
                    + block.getNumberOfRooms());

            System.out.println("Occupied Beds: "
                    + block.getOccupiedBeds());

            System.out.println("Available Beds: "
                    + block.getAvailableBeds());
        }
    }

    // Display all rooms in a selected building
    public void displayRoomsInBuilding(
            Dormitory dormitory,
            SectionType sectionType,
            String buildingName) {

        Block block = findBuilding(
                dormitory,
                sectionType,
                buildingName);

        if (block == null) {
            System.out.println("Building not found.");
            return;
        }

        System.out.println("\n===== ROOMS IN "
                + block.getBlockName() + " =====");

        List<Room> rooms = block.getRooms();

        if (rooms.isEmpty()) {
            System.out.println("No rooms configured.");
            return;
        }

        for (Room room : rooms) {

            System.out.println("\n------------------------");

            room.displayRoomInformation();
        }
    }

    // Search for a building by name
    public Block findBuilding(
            Dormitory dormitory,
            SectionType sectionType,
            String buildingName) {

        if (dormitory == null
                || sectionType == null
                || buildingName == null
                || buildingName.isBlank()) {

            return null;
        }

        DormitorySection section =
                dormitory.getSection(sectionType);

        for (Block block : section.getBlocks()) {

            if (block.getBlockName().equalsIgnoreCase(
                    buildingName.trim())) {

                return block;
            }
        }

        return null;
    }

    // Search for a room by room number
    public Room searchRoom(
            Dormitory dormitory,
            SectionType sectionType,
            String roomNumber) {

        if (dormitory == null
                || sectionType == null
                || roomNumber == null
                || roomNumber.isBlank()) {

            return null;
        }

        DormitorySection section =
                dormitory.getSection(sectionType);

        for (Block block : section.getBlocks()) {

            for (Room room : block.getRooms()) {

                if (room.getRoomNumber().equalsIgnoreCase(
                        roomNumber.trim())) {

                    return room;
                }
            }
        }

        return null;
    }
}
