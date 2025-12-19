package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.IssuedDeviceRecord;

public interface IssuedDeviceRecordService {

    IssuedDeviceRecord issueDevice(IssuedDeviceRecord record);

    IssuedDeviceRecord returnDevice(Long recordld);

    List<IssuedDeviceRecord> getlssuedDevicesByEmployee(Long employeeld);
}
