// package com.example.demo.controller;

// import com.example.demo.model.IssuedDeviceRecord;
// import com.example.demo.service.IssuedDeviceRecordService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/issued-devices")
// public class IssuedDeviceRecordController {

//     private final IssuedDeviceRecordService service;

//     public IssuedDeviceRecordController(IssuedDeviceRecordService service) {
//         this.service = service;
//     }

//     @PostMapping
//     public IssuedDeviceRecord issue(@RequestBody IssuedDeviceRecord record) {
//         return service.issueDevice(record);
//     }

//     @PutMapping("/{id}/return")
//     public IssuedDeviceRecord returnDevice(@PathVariable Long id) {
//         return service.returnDevice(id);
//     }

//     @GetMapping("/employee/{employeeId}")
//     public List<IssuedDeviceRecord> getByEmployee(@PathVariable Long employeeId) {
//         return service.getIssuedDevicesByEmployee(employeeId);
//     }
// }

// package com.example.demo.controller;

// import com.example.demo.model.IssuedDeviceRecord;
// import com.example.demo.service.IssuedDeviceRecordService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/issued-devices")
// public class IssuedDeviceController {

//     @Autowired
//     private IssuedDeviceRecordService issuedService;

//     @PutMapping("/{id}/return")
//     public ResponseEntity<IssuedDeviceRecord> returnDevice(@PathVariable Long id) {
//         return ResponseEntity.ok(issuedService.returnDevice(id));
//     }
// }

package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.service.IssuedDeviceRecordService;

@RestController
@RequestMapping("/api/issued-devices")
public class IssuedDeviceRecordController {

    private final IssuedDeviceRecordService service;

    public IssuedDeviceRecordController(IssuedDeviceRecordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<IssuedDeviceRecord> issueDevice(
            @RequestBody IssuedDeviceRecord record) {
        return ResponseEntity.ok(service.issueDevice(record));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<IssuedDeviceRecord> returnDevice(@PathVariable Long id) {
        return ResponseEntity.ok(service.returnDevice(id));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<IssuedDeviceRecord>> getByEmployee(
            @PathVariable Long employeeId) {
        return ResponseEntity.ok(service.getByEmployeeId(employeeId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssuedDeviceRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
}

