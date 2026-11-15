
import com.smartdorm.model.*;
import com.smartdorm.service.*;
import com.smartdorm.enums.*;

public class RegistrationAllocationTest {

    public static void main(String[] args) {

        // Initialize services
        DormitoryConfigurationService config =
                new DormitoryConfigurationService();

        StudentRegistrationService registration =
                new StudentRegistrationService();

        RoomAllocationService allocation =
                new RoomAllocationService();

        Dormitory dormitory = config.configureDormitory();

        // Register regular students first
        registration.registerStudent(new Student(
                "R001", "Regular Male",
                "male@gmail.com", "0911111111",
                "HU101", "Sociology",
                AcademicYear.FOURTH_YEAR,
                Gender.MALE
        ));

        registration.registerStudent(new Student(
                "R002", "Regular Female",
                "female@gmail.com", "0922222222",
                "HU102", "Law",
                AcademicYear.FIFTH_YEAR,
                Gender.FEMALE
        ));

        // Register special-accommodation students later
        registration.registerStudent(new Student(
                "R003", "Special Male",
                "specialmale@gmail.com", "0933333333",
                "HU103", "Unlisted Department",
                AcademicYear.FRESHMAN,
                Gender.MALE, true
        ));

        registration.registerStudent(new Student(
                "R004", "Special Female",
                "specialfemale@gmail.com", "0944444444",
                "HU104", "Unlisted Department",
                AcademicYear.FRESHMAN,
                Gender.FEMALE, true
        ));

        System.out.println("\n===== REGISTRATION SUMMARY =====");

        System.out.println("Registered Students: "
                + registration.getTotalRegisteredStudents());

        // Allocate registered students
        int allocated =
                allocation.allocateStudentsInPriorityOrder(
                        dormitory,
                        registration.getRegisteredStudents()
                );

        System.out.println("\n===== FINAL RESULTS =====");

        System.out.println("Registered: "
                + registration.getTotalRegisteredStudents());

        System.out.println("Allocated: " + allocated);

        System.out.println("Available Beds: "
                + dormitory.getAvailableBeds());
    }
}
