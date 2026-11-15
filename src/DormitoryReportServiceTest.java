
import com.smartdorm.model.Dormitory;
import com.smartdorm.model.Student;

import com.smartdorm.enums.AcademicYear;
import com.smartdorm.enums.Gender;

import com.smartdorm.service.DormitoryConfigurationService;
import com.smartdorm.service.DormitoryReportService;
import com.smartdorm.service.StudentManagementController;

public class DormitoryReportServiceTest {

    public static void main(String[] args) {

        DormitoryConfigurationService configurationService =
                new DormitoryConfigurationService();

        Dormitory dormitory =
                configurationService.configureDormitory();

        DormitoryReportService reportService =
                new DormitoryReportService();

        // Test 1: Overall dormitory report
        System.out.println("\nTEST 1: OVERALL DORMITORY REPORT");

        reportService.displayOverallReport(dormitory);

        // Test 2: Male section report
        System.out.println("\nTEST 2: MALE SECTION REPORT");

        reportService.displaySectionReport(
                dormitory.getMaleSection());

        // Test 3: Female section report
        System.out.println("\nTEST 3: FEMALE SECTION REPORT");

        reportService.displaySectionReport(
                dormitory.getFemaleSection());

        // Test 4: Building occupancy reports
        System.out.println("\nTEST 4: BUILDING REPORTS");

        reportService.displayBuildingReports(dormitory);

        // Test 5: Room occupancy summary before registration
        System.out.println("\nTEST 5: ROOM OCCUPANCY SUMMARY");

        reportService.displayRoomOccupancySummary(dormitory);

        // Test 6: Register and allocate a test student
        System.out.println("\nTEST 6: REPORT AFTER ALLOCATION");

        StudentManagementController controller =
                new StudentManagementController(dormitory);

        Student student = new Student(
                "REPORT001",
                "Test Student",
                "reporttest@gmail.com",
                "0912345678",
                "HU/REPORT/001",
                "IT",
                AcademicYear.THIRD_YEAR,
                Gender.MALE
        );

        // Register student
        boolean registered = controller.registerStudent(student);

        System.out.println("Registration result: " + registered);

        // Verify the original student ID
        System.out.println(
                "Original Student ID: " + student.getStudentId());

        // Verify the registered student count


        // Search for the registered student
        Student found = controller.searchStudent("HU/REPORT/001");

        System.out.println("Student found: " + (found != null));

        // Allocate only if the student was found
        boolean allocated = false;

        if (found != null) {
            allocated = controller.allocateStudent("HU/REPORT/001");
        }

        System.out.println("Allocation result: " + allocated);

        // Display reports after allocation
        reportService.displayOverallReport(dormitory);

        reportService.displayRoomOccupancySummary(dormitory);

        System.out.println("\nAll dormitory report tests completed.");
    }
}
