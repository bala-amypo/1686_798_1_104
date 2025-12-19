package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.IssuedDeviceRecord;
import com.example.demo.service.IssuedDeviceRecordService;

@RestController
@RequestMapping("/api/issued-devices")
public class IssuedDeviceRecordController {

    private final IssuedDeviceRecordService service;

    public IssuedDeviceRecordController(IssuedDeviceRecordService service) {
        this.service = service;
    }

    // POST /api/issued-devices
    @PostMapping
    public IssuedDeviceRecord issueDevice(
            @RequestBody IssuedDeviceRecord record) {
        return service.issueDevice(record);
    }

    // PUT /api/issued-devices/{id}/return
    @PutMapping("/{id}/return")
    public IssuedDeviceRecord returnDevice(@PathVariable Long id) {
        return service.returnDevice(id);
    }

    // GET /api/issued-devices/employee/{employeeld}
    @GetMapping("/employee/{employeeld}")
    public List<IssuedDeviceRecord> getIssuedDevices(
            @PathVariable Long employeeld) {
        return service.getlssuedDevicesByEmployee(employeeld);
    }
}
