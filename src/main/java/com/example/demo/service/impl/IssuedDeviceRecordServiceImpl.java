// package com.example.demo.service.impl;

// import com.example.demo.exception.BadRequestException;
// import com.example.demo.model.IssuedDeviceRecord;
// import com.example.demo.repository.DeviceCatalogItemRepository;
// import com.example.demo.repository.EmployeeProfileRepository;
// import com.example.demo.repository.IssuedDeviceRecordRepository;
// import com.example.demo.service.IssuedDeviceRecordService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.LocalDate;
// import java.util.List;

// @Service
// public class IssuedDeviceRecordServiceImpl implements IssuedDeviceRecordService {

//     private final IssuedDeviceRecordRepository recordRepo;

//     // ✅ Constructor USED BY SPRING BOOT
//     @Autowired
//     public IssuedDeviceRecordServiceImpl(IssuedDeviceRecordRepository recordRepo) {
//         this.recordRepo = recordRepo;
//     }

//     // ✅ Constructor USED ONLY BY TESTS
//     public IssuedDeviceRecordServiceImpl(
//             IssuedDeviceRecordRepository recordRepo,
//             EmployeeProfileRepository employeeRepo,
//             DeviceCatalogItemRepository deviceRepo
//     ) {
//         this.recordRepo = recordRepo;
//     }

//     @Override
//     public IssuedDeviceRecord issueDevice(IssuedDeviceRecord record) {
//         record.setIssuedDate(LocalDate.now());
//         record.setReturnedDate(null);
//         record.setStatus("ISSUED");
//         return recordRepo.save(record);
//     }

//     @Override
//     public IssuedDeviceRecord returnDevice(Long recordId) {

//         IssuedDeviceRecord record = recordRepo.findById(recordId)
//                 .orElseThrow(() -> new BadRequestException("Issued record not found"));

//         if (record.getReturnedDate() != null
//                 || "RETURNED".equals(record.getStatus())) {
//             throw new BadRequestException("Device already returned");
//         }

//         record.setReturnedDate(LocalDate.now());
//         record.setStatus("RETURNED");
//         return recordRepo.save(record);
//     }

//     @Override
//     public List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId) {
//         return recordRepo.findByEmployeeId(employeeId);
//     }
// }


// package com.example.demo.service.impl;

// import com.example.demo.exception.BadRequestException;
// import com.example.demo.model.IssuedDeviceRecord;
// import com.example.demo.repository.DeviceCatalogItemRepository;
// import com.example.demo.repository.EmployeeProfileRepository;
// import com.example.demo.repository.IssuedDeviceRecordRepository;
// import com.example.demo.service.IssuedDeviceRecordService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class IssuedDeviceRecordServiceImpl implements IssuedDeviceRecordService {

//     private final IssuedDeviceRecordRepository issuedRepo;
//     private final EmployeeProfileRepository employeeProfileRepository;
//     private final DeviceCatalogItemRepository deviceCatalogItemRepository;

//     // ✅ Constructor expected by TESTS
//     @Autowired
//     public IssuedDeviceRecordServiceImpl(
//             IssuedDeviceRecordRepository issuedRepo,
//             EmployeeProfileRepository employeeProfileRepository,
//             DeviceCatalogItemRepository deviceCatalogItemRepository) {

//         this.issuedRepo = issuedRepo;
//         this.employeeProfileRepository = employeeProfileRepository;
//         this.deviceCatalogItemRepository = deviceCatalogItemRepository;
//     }

//     @Override
//     public IssuedDeviceRecord returnDevice(Long id) {
//         IssuedDeviceRecord record = issuedRepo.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Record not found"));

//         if ("RETURNED".equals(record.getStatus())) {
//             throw new BadRequestException("Device already returned");
//         }

//         record.setStatus("RETURNED");
//         return issuedRepo.save(record);
//     }
// }

package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.service.IssuedDeviceRecordService;

@Service
public class IssuedDeviceRecordServiceImpl implements IssuedDeviceRecordService {

    private final IssuedDeviceRecordRepository issuedRepo;
    private final EmployeeProfileRepository employeeRepo;
    private final DeviceCatalogItemRepository deviceRepo;

    public IssuedDeviceRecordServiceImpl(
            IssuedDeviceRecordRepository issuedRepo,
            EmployeeProfileRepository employeeRepo,
            DeviceCatalogItemRepository deviceRepo) {

        this.issuedRepo = issuedRepo;
        this.employeeRepo = employeeRepo;
        this.deviceRepo = deviceRepo;
    }

    @Override
    public IssuedDeviceRecord issueDevice(IssuedDeviceRecord record) {

        EmployeeProfile employee = employeeRepo.findById(record.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        if (!employee.getActive()) {
            throw new BadRequestException("not active");
        }

        DeviceCatalogItem device = deviceRepo.findById(record.getDeviceItemId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Device not found"));

        if (!device.getActive()) {
            throw new BadRequestException("inactive");
        }

        long activeIssued =
                issuedRepo.countByEmployeeIdAndStatus(record.getEmployeeId(), "ISSUED");

        if (activeIssued > 0) {
            throw new BadRequestException("active issuance");
        }

        record.setIssuedDate(LocalDate.now());
        record.setReturnedDate(null);
        record.setStatus("ISSUED");

        return issuedRepo.save(record);
    }

    @Override
    public IssuedDeviceRecord returnDevice(Long recordId) {

        IssuedDeviceRecord record = issuedRepo.findById(recordId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Issued record not found"));

        if ("RETURNED".equals(record.getStatus())) {
            throw new BadRequestException("already returned");
        }

        record.setStatus("RETURNED");
        record.setReturnedDate(LocalDate.now());

        return issuedRepo.save(record);
    }

    @Override
    public List<IssuedDeviceRecord> getByEmployeeId(Long employeeId) {
        return issuedRepo.findByEmployeeId(employeeId);
    }

    @Override
    public List<IssuedDeviceRecord> getActiveByEmployeeId(Long employeeId) {
        return issuedRepo.findByEmployeeIdAndStatus(employeeId, "ISSUED");
    }

    @Override
    public IssuedDeviceRecord getById(Long id) {
        return issuedRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Issued record not found"));
    }

    @Override
    public long countActiveDevicesForEmployee(Long employeeId) {
        return issuedRepo.countByEmployeeIdAndStatus(employeeId, "ISSUED");
    }
}
