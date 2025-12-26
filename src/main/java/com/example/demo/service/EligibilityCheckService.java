import com.example.demo.model.UserAccount;
import com.example.demo.model.DeviceCatalogItem;

public interface EligibilityCheckService {

    EligibilityCheckRecord validateEligibility(Long employeeId, Long deviceItemId);

    // ✅ ADD THIS
    EligibilityCheckRecord validateEligibility(UserAccount user, DeviceCatalogItem device);

    List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId);
}
