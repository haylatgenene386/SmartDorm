
package com.smartdorm.model;

import com.smartdorm.enums.SectionType;

public class Dormitory {

    private String dormitoryName;
    private DormitorySection maleSection;
    private DormitorySection femaleSection;

    // Constructor
    public Dormitory(String dormitoryName) {

        this.dormitoryName = dormitoryName;

        this.maleSection =
                new DormitorySection(SectionType.MALE);

        this.femaleSection =
                new DormitorySection(SectionType.FEMALE);
    }

    // Getters
    public String getDormitoryName() {
        return dormitoryName;
    }

    public DormitorySection getMaleSection() {
        return maleSection;
    }

    public DormitorySection getFemaleSection() {
        return femaleSection;
    }

    // Get section based on gender
    public DormitorySection getSection(SectionType sectionType) {

        if (sectionType == null) {
            return null;
        }

        if (sectionType == SectionType.MALE) {
            return maleSection;
        }

        return femaleSection;
    }

    // Calculate total dormitory capacity
    public int getTotalCapacity() {

        return maleSection.getTotalCapacity()
                + femaleSection.getTotalCapacity();
    }

    // Calculate total occupied beds
    public int getOccupiedBeds() {

        return maleSection.getOccupiedBeds()
                + femaleSection.getOccupiedBeds();
    }

    // Calculate available beds
    public int getAvailableBeds() {

        return getTotalCapacity() - getOccupiedBeds();
    }

    // Display complete dormitory information
    public void displayDormitoryInformation() {

        System.out.println("\n================================");
        System.out.println("   " + dormitoryName);
        System.out.println("   DORMITORY MANAGEMENT SYSTEM");
        System.out.println("================================");

        System.out.println("Total Capacity: " + getTotalCapacity());
        System.out.println("Occupied Beds: " + getOccupiedBeds());
        System.out.println("Available Beds: " + getAvailableBeds());

        maleSection.displaySectionInformation();

        femaleSection.displaySectionInformation();
    }
}
