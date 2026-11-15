
import com.smartdorm.model.Dormitory;
import com.smartdorm.service.DormitoryConfigurationService;
import com.smartdorm.ui.DormitoryReportMenu;

public class DormitoryReportMenuTest {

    public static void main(String[] args) {

        DormitoryConfigurationService configurationService =
                new DormitoryConfigurationService();

        Dormitory dormitory =
                configurationService.configureDormitory();

        DormitoryReportMenu reportMenu =
                new DormitoryReportMenu(dormitory);

        reportMenu.displayMenu();
    }
}
