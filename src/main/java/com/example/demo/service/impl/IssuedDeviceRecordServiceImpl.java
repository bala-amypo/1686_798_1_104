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

package com.example.demo.service;

import java.util.List;
import com.example.demo.model.IssuedDeviceRecord;

public interface IssuedDeviceRecordService {

    IssuedDeviceRecord issueDevice(IssuedDeviceRecord record);

    IssuedDeviceRecord returnDevice(Long recordId);

    List<IssuedDeviceRecord> getByEmployeeId(Long employeeId);

    List<IssuedDeviceRecord> getActiveByEmployeeId(Long employeeId);

    IssuedDeviceRecord getById(Long id);

    long countActiveDevicesForEmployee(Long employeeId);
}
