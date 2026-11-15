
package com.smartdorm.service;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.smartdorm.enums.AcademicYear;
import com.smartdorm.enums.BlockStatus;
import com.smartdorm.enums.SectionType;
import com.smartdorm.model.Block;
import com.smartdorm.model.Dormitory;
import com.smartdorm.model.DormitorySection;

public class DormitoryConfigurationService {

    private static final int ESTIMATED_ROOMS_PER_AB_BLOCK = 256;

    private final DormitorySetupService setupService =
            new DormitorySetupService();

    // Main configuration method
    public Dormitory configureDormitory() {

        Dormitory dormitory =
                new Dormitory("Haramaya University SmartDorm");

        configureMaleSection(dormitory.getMaleSection());
        configureFemaleSection(dormitory.getFemaleSection());

        return dormitory;
    }
// Configure Building I: Male special-accommodation priority building
private void configureBuildingI(DormitorySection section) {

    for (Block block : section.getBlocks()) {

        if (block.getBlockName().equalsIgnoreCase("Building I")) {

            setupService.configureRooms(
                    block,
                    24,
                    1,
                    2
            );

            return;
        }
    }
}
    // Configure male buildings
    private void configureMaleSection(DormitorySection section) {

        addMaleBlock(section, "Building III", 550,
                departments("Accounting", "Economics", "COOP",
                        "Agro-Economics", "Bio Tech", "ABVM", "EDPM"),
                AcademicYear.SECOND_YEAR);

        addMaleBlock(section, "Building II", 144,
                departments("PADM", "Sociology"),
                AcademicYear.FOURTH_YEAR);

        addMaleBlock(section, "Building I", 100,
                departments("Sociology", "Statistics", "Tourism"),
                AcademicYear.FOURTH_YEAR);

        addMaleBlock(section, "Wing A", 420,
                departments("Law", "IT", "Management", "Journalism"),
                AcademicYear.FOURTH_YEAR);

        addMaleBlock(section, "Wing B", 430,
                departments("Sociology", "Statistics", "Tourism", "EDPM"),
                AcademicYear.SECOND_YEAR);

        addMaleBlock(section, "SAT 1A", 440,
                departments("Journalism", "History", "GIS",
                        "Marketing", "Management", "Geography", "English"),
                AcademicYear.SECOND_YEAR);

        addInactiveBlock(section, "SAT 1B");

        addMaleBlock(section, "AMEL 2A", 420,
                rules(
                    "SWE", AcademicYear.THIRD_YEAR,
                    "Law", AcademicYear.SECOND_YEAR,
                    "PADM", AcademicYear.THIRD_YEAR,
                    "Psychology", AcademicYear.THIRD_YEAR,
                    "Sociology", AcademicYear.THIRD_YEAR,
                    "Statistics", AcademicYear.THIRD_YEAR,
                    "Management", AcademicYear.THIRD_YEAR
                ));

        addMaleBlock(section, "AMEL 2B", 440,
                rules(
                    "IT", AcademicYear.SECOND_YEAR,
                    "ISY", AcademicYear.SECOND_YEAR,
                    "ISC", AcademicYear.SECOND_YEAR,
                    "CS", AcademicYear.SECOND_YEAR,
                    "Tourism", AcademicYear.THIRD_YEAR,
                    "SWE", AcademicYear.THIRD_YEAR
                ));

        // Combined reported population is 2,250.
        // Individual A/B figures have not yet been provided.
        addMaleBlock(section, "AMEL 1A", 0,
                departments("Natural Science"),
                AcademicYear.FRESHMAN);

        addMaleBlock(section, "AMEL 1B", 0,
                departments("Natural Science"),
                AcademicYear.FRESHMAN);

        addMaleBlock(section, "AMEL 3A", 538,
                departments("Natural Science"),
                AcademicYear.FRESHMAN);

        addMaleBlock(section, "AMEL 3B", 280,
                departments("Social Science"),
                AcademicYear.FRESHMAN);

        addMaleBlock(section, "DMC 1A", 420,
                departments("CS", "Agro-Economics", "COOP",
                        "Accounting", "ABVM", "Economics"),
                AcademicYear.THIRD_YEAR);

        addMaleBlock(section, "DMC 1B", 430,
                departments("GIS", "ISC", "EDPM", "ISY",
                        "Economics", "NRM", "IT", "Journalism"),
                AcademicYear.THIRD_YEAR);

        addMaleBlock(section, "DMC 2A", 390,
                departments("Afaan Oromo", "Accounting",
                        "COOP", "Economics", "EDPM"),
                AcademicYear.SECOND_YEAR);

        addMaleBlock(section, "DMC 2B", 400,
                departments("ISY", "ISC", "History", "GIS",
                        "English", "EDPM", "Economics"),
                AcademicYear.FOURTH_YEAR);

        configureEstimatedABRooms(section);
        configureBuildingI(section);  
    }

 // Configure female buildings

private void configureFemaleSection(DormitorySection section) {

    // Fifth-year Law and SWE students.
    addFemaleBlock(section, "Twins A", 0,
            departments("Law", "SWE"),
            AcademicYear.FIFTH_YEAR);

    addFemaleBlock(section, "Twins B", 0,
            departments("Law", "SWE"),
            AcademicYear.FIFTH_YEAR);

    // Freshman students from all departments.
    addFemaleBlock(section, "SAT 2A", 0,
            departments("All Departments"),
            AcademicYear.FRESHMAN);

    addFemaleBlock(section, "SAT 2B", 0,
            departments("All Departments"),
            AcademicYear.FRESHMAN);

    // Second-, third-, and selected fourth-year students.
    addFemaleMixedBlock(section, "3A", 0);
    addFemaleMixedBlock(section, "3B", 0);

    configureEstimatedABRooms(section);
}

    // Add a male block with one common academic year
    private void addMaleBlock(
            DormitorySection section,
            String name,
            int assignedStudents,
            Set<String> departments,
            AcademicYear year) {

        Block block = new Block(
                name, assignedStudents, BlockStatus.ACTIVE);

        block.configureEligibility(
                SectionType.MALE,
                rules(departments, year));

        section.addBlock(block);
    }

    // Add a male block with department-specific rules
    private void addMaleBlock(
            DormitorySection section,
            String name,
            int assignedStudents,
            Map<String, Set<AcademicYear>> eligibility) {

        Block block = new Block(
                name, assignedStudents, BlockStatus.ACTIVE);

        block.configureEligibility(SectionType.MALE, eligibility);
        section.addBlock(block);
    }

    // Add a female block
   

private void addFemaleBlock(
        DormitorySection section,
        String name,
        int assignedStudents,
        Set<String> departments,
        AcademicYear... years) {

    Block block = new Block(
            name, assignedStudents, BlockStatus.ACTIVE);

    Map<String, Set<AcademicYear>> eligibility =
            new HashMap<>();

    Set<AcademicYear> allowedYears =
            EnumSet.noneOf(AcademicYear.class);

    for (AcademicYear year : years) {
        allowedYears.add(year);
    }

    for (String department : departments) {

        if (department.equalsIgnoreCase("All Departments")) {
            eligibility.put("*", allowedYears);
        } else {
            eligibility.put(department, allowedYears);
        }
    }

    block.configureEligibility(
            SectionType.FEMALE, eligibility);

    section.addBlock(block);
}


private void addFemaleMixedBlock(
        DormitorySection section,
        String name,
        int assignedStudents) {

    Block block = new Block(
            name, assignedStudents, BlockStatus.ACTIVE);

    Map<String, Set<AcademicYear>> eligibility =
            new HashMap<>();

    // All departments: second and third year.
    eligibility.put(
            "*",
            EnumSet.of(
                    AcademicYear.SECOND_YEAR,
                    AcademicYear.THIRD_YEAR
            )
    );

    // Selected departments: fourth year.
    Set<AcademicYear> fourthYear =
            EnumSet.of(AcademicYear.FOURTH_YEAR);

    eligibility.put("SWE", fourthYear);
    eligibility.put("CS", fourthYear);
    eligibility.put("Law", fourthYear);

    block.configureEligibility(
            SectionType.FEMALE, eligibility);

    section.addBlock(block);
}

private void addFemaleBlockWithRules(
        DormitorySection section,
        String name,
        int assignedStudents,
        Map<String, Set<AcademicYear>> rules) {

    Block block = new Block(
            name, assignedStudents, BlockStatus.ACTIVE);

    block.configureEligibility(
            SectionType.FEMALE, rules);

    section.addBlock(block);
}

    // Add inactive building
    private void addInactiveBlock(
            DormitorySection section, String name) {

        Block block = new Block(name, 0, BlockStatus.INACTIVE);

        block.configureEligibility(
                SectionType.MALE,
                new HashMap<>());

        section.addBlock(block);
    }

    // Generate estimated rooms for A/B buildings
    private void configureEstimatedABRooms(
            DormitorySection section) {

        for (Block block : section.getBlocks()) {

            String name = block.getBlockName().toUpperCase();

            if (block.getStatus() != BlockStatus.ACTIVE) {
                continue;
            }

            if (name.endsWith("A") || name.endsWith("B")) {

                setupService.configureRooms(
                        block,
                        ESTIMATED_ROOMS_PER_AB_BLOCK,
                        0,
                        5
                );
            }
        }
    }

    // Create eligibility rules for one academic year
    private Map<String, Set<AcademicYear>> rules(
            Set<String> departments,
            AcademicYear year) {

        Map<String, Set<AcademicYear>> result = new HashMap<>();

        for (String department : departments) {
            result.put(department, EnumSet.of(year));
        }

        return result;
    }

    // Create department-specific eligibility rules
    private Map<String, Set<AcademicYear>> rules(Object... values) {

        Map<String, Set<AcademicYear>> result = new HashMap<>();

        for (int i = 0; i < values.length; i += 2) {

            String department = (String) values[i];
            AcademicYear year = (AcademicYear) values[i + 1];

            result.put(department, EnumSet.of(year));
        }

        return result;
    }

    // Create department names
    private Set<String> departments(String... names) {
        return Set.of(names);
    }
}

