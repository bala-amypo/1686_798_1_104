import com.example.demo.model.UserAccount;
import com.example.demo.model.DeviceCatalogItem;

public interface EligibilityCheckService {

    
    // ✅ ADD THIS
    EligibilityCheckRecord validateEligibility(UserAccount user, DeviceCatalogItem device);

    List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId);
}
