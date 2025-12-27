// package com.example.demo.service;

// import com.example.demo.model.IssuedDeviceRecord;
// import java.util.List;

// public interface IssuedDeviceRecordService {

//     IssuedDeviceRecord issueDevice(IssuedDeviceRecord record);

//     IssuedDeviceRecord returnDevice(Long recordId);

//     List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId);
// }

// package com.example.demo.service;

// import com.example.demo.model.IssuedDeviceRecord;

// public interface IssuedDeviceRecordService {
//     IssuedDeviceRecord returnDevice(Long id);
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

