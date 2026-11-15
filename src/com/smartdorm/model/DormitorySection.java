
package com.smartdorm.model;

import java.util.ArrayList;
import java.util.List;

import com.smartdorm.enums.SectionType;

public class DormitorySection {

    private SectionType sectionType;
    private List<Block> blocks;

    // Constructor
    public DormitorySection(SectionType sectionType) {

        this.sectionType = sectionType;
        this.blocks = new ArrayList<>();
    }

    // Getters
    public SectionType getSectionType() {
        return sectionType;
    }

    public List<Block> getBlocks() {
        return new ArrayList<>(blocks);
    }

    // Add a block
    public boolean addBlock(Block block) {

        if (block == null) {
            return false;
        }

        // Prevent duplicate block names
        for (Block existing : blocks) {

            if (existing.getBlockName()
                    .equalsIgnoreCase(block.getBlockName())) {
                return false;
            }
        }

        blocks.add(block);
        return true;
    }

    // Calculate total capacity
    public int getTotalCapacity() {

        int total = 0;

        for (Block block : blocks) {
            total += block.getCapacity();
        }

        return total;
    }

    // Calculate occupied beds
    public int getOccupiedBeds() {

        int total = 0;

        for (Block block : blocks) {
            total += block.getOccupiedBeds();
        }

        return total;
    }

    // Calculate available beds
    public int getAvailableBeds() {

        return getTotalCapacity() - getOccupiedBeds();
    }

    // Display section information
    public void displaySectionInformation() {

        System.out.println("\n===== "
                + sectionType + " DORMITORY SECTION =====");

        System.out.println("Number of Blocks: " + blocks.size());
        System.out.println("Total Capacity: " + getTotalCapacity());
        System.out.println("Occupied Beds: " + getOccupiedBeds());
        System.out.println("Available Beds: " + getAvailableBeds());

        System.out.println("\nBlock List:");

        for (Block block : blocks) {
            System.out.println("- " + block.getBlockName()
                    + " (" + block.getStatus() + ")");
        }
    }
}
