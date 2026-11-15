
package com.smartdorm.service;

import java.util.List;

import com.smartdorm.model.Dormitory;
import com.smartdorm.model.DormitorySection;
import com.smartdorm.model.Block;
import com.smartdorm.model.Room;

public class DormitoryReportService {

    // Display overall dormitory occupancy report
    public void displayOverallReport(Dormitory dormitory) {

        if (dormitory == null) {
            System.out.println("Dormitory information unavailable.");
            return;
        }

        int totalCapacity = dormitory.getTotalCapacity();
        int occupiedBeds = dormitory.getOccupiedBeds();
        int availableBeds = dormitory.getAvailableBeds();

        System.out.println("\n====================================");
        System.out.println("       DORMITORY OCCUPANCY REPORT");
        System.out.println("====================================");

        System.out.println("Total Bed Capacity: " + totalCapacity);
        System.out.println("Occupied Beds: " + occupiedBeds);
        System.out.println("Available Beds: " + availableBeds);

        double occupancyRate = totalCapacity == 0
                ? 0
                : (occupiedBeds * 100.0) / totalCapacity;

        System.out.printf("Occupancy Rate: %.2f%%%n", occupancyRate);

        System.out.println("====================================");
    }

    // Display occupancy report by section
    public void displaySectionReport(DormitorySection section) {

        if (section == null) {
            System.out.println("Section not found.");
            return;
        }

        int capacity = section.getTotalCapacity();
        int occupied = section.getOccupiedBeds();
        int available = section.getAvailableBeds();

        double occupancyRate = capacity == 0
                ? 0
                : (occupied * 100.0) / capacity;

        System.out.println("\n===== "
                + section.getSectionType()
                + " SECTION OCCUPANCY REPORT =====");

        System.out.println("Total Capacity: " + capacity);
        System.out.println("Occupied Beds: " + occupied);
        System.out.println("Available Beds: " + available);

        System.out.printf("Occupancy Rate: %.2f%%%n", occupancyRate);
    }

    // Display occupancy report for every building
    public void displayBuildingReports(Dormitory dormitory) {

        if (dormitory == null) {
            System.out.println("Dormitory information unavailable.");
            return;
        }

        System.out.println("\n===== BUILDING OCCUPANCY REPORTS =====");

        displayBuildingsReport(dormitory.getMaleSection());
        displayBuildingsReport(dormitory.getFemaleSection());
    }

    // Display individual building reports
    private void displayBuildingsReport(DormitorySection section) {

        if (section == null) {
            return;
        }

        List<Block> blocks = section.getBlocks();

        for (Block block : blocks) {

            int capacity = block.getCapacity();
            int occupied = block.getOccupiedBeds();
            int available = block.getAvailableBeds();

            double occupancyRate = capacity == 0
                    ? 0
                    : (occupied * 100.0) / capacity;

            System.out.println("\nBuilding: " + block.getBlockName());
            System.out.println("Section: " + section.getSectionType());
            System.out.println("Status: " + block.getStatus());
            System.out.println("Total Capacity: " + capacity);
            System.out.println("Occupied Beds: " + occupied);
            System.out.println("Available Beds: " + available);

            System.out.printf("Occupancy Rate: %.2f%%%n", occupancyRate);
        }
    }

    // Display room occupancy summary
    public void displayRoomOccupancySummary(Dormitory dormitory) {

        if (dormitory == null) {
            System.out.println("Dormitory information unavailable.");
            return;
        }

        int totalRooms = 0;
        int fullRooms = 0;
        int partiallyOccupiedRooms = 0;
        int emptyRooms = 0;

        DormitorySection[] sections = {
            dormitory.getMaleSection(),
            dormitory.getFemaleSection()
        };

        for (DormitorySection section : sections) {

            if (section == null) {
                continue;
            }

            for (Block block : section.getBlocks()) {

                for (Room room : block.getRooms()) {

                    totalRooms++;

                    if (room.isFull()) {

                        fullRooms++;

                    } else if (room.getStudents().isEmpty()) {

                        emptyRooms++;

                    } else {

                        partiallyOccupiedRooms++;
                    }
                }
            }
        }

        System.out.println("\n===== ROOM OCCUPANCY SUMMARY =====");

        System.out.println("Total Configured Rooms: " + totalRooms);
        System.out.println("Full Rooms: " + fullRooms);
        System.out.println("Partially Occupied Rooms: "
                + partiallyOccupiedRooms);
        System.out.println("Empty Rooms: " + emptyRooms);
    }
}
