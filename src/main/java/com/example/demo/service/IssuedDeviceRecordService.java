// package com.example.demo.service;

// import com.example.demo.model.IssuedDeviceRecord;
// import java.util.List;

// public interface IssuedDeviceRecordService {

//     IssuedDeviceRecord issueDevice(IssuedDeviceRecord record);

//     IssuedDeviceRecord returnDevice(Long recordId);

//     List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId);
// }

package com.example.demo.service;

import com.example.demo.model.IssuedDeviceRecord;

public interface IssuedDeviceRecordService {
    IssuedDeviceRecord returnDevice(Long id);
}
