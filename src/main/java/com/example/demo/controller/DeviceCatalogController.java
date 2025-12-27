// package com.example.demo.controller;

// import com.example.demo.model.DeviceCatalogItem;
// import com.example.demo.service.DeviceCatalogService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/devices")
// public class DeviceCatalogController {

//     private final DeviceCatalogService service;

//     public DeviceCatalogController(DeviceCatalogService service) {
//         this.service = service;
//     }

//     @PostMapping
//     public DeviceCatalogItem create(@RequestBody DeviceCatalogItem item) {
//         return service.createItem(item);
//     }

//     @PutMapping("/{id}/active")
//     public DeviceCatalogItem updateActive(
//             @PathVariable Long id,
//             @RequestParam boolean active) {
//         return service.updateActiveStatus(id, active);
//     }

//     @GetMapping
//     public List<DeviceCatalogItem> getAll() {
//         return service.getAllItems();
//     }
// }


// package com.example.demo.controller;

// import com.example.demo.model.DeviceCatalogItem;
// import com.example.demo.service.DeviceCatalogService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/devices")
// public class DeviceController {

//     @Autowired
//     private DeviceCatalogService deviceService;

//      @GetMapping("/")
//     // @GetMapping
//     public ResponseEntity<List<DeviceCatalogItem>> getAllDevices() {
//         return ResponseEntity.ok(deviceService.getAllItems());
//     }

//     @PostMapping("/")
//     // @PostMapping
//     public ResponseEntity<DeviceCatalogItem> createDevice(@RequestBody DeviceCatalogItem device) {
//         return ResponseEntity.ok(deviceService.createItem(device));
//     }

//     @PutMapping("/{id}/status")
//     public ResponseEntity<DeviceCatalogItem> updateStatus(@PathVariable Long id, @RequestParam Boolean active) {
//         return ResponseEntity.ok(deviceService.updateActiveStatus(id, active));
//     }
// }

package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.service.DeviceCatalogService;

@RestController
@RequestMapping("/api/devices")
public class DeviceCatalogController {

    private final DeviceCatalogService service;

    public DeviceCatalogController(DeviceCatalogService service) {
        this.service = service;
    }

    @PostMapping
    public DeviceCatalogItem createDevice(
            @RequestBody DeviceCatalogItem item) {
        return service.createItem(item);
    }

    @GetMapping
    public List<DeviceCatalogItem> getAllDevices() {
        return service.getAllItems();
    }

    @GetMapping("/{id}")
    public DeviceCatalogItem getDeviceById(@PathVariable Long id) {
        return service.getItemById(id);
    }

    @PutMapping("/{id}/active")
    public DeviceCatalogItem updateActiveStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {
        return service.updateActiveStatus(id, active);
    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable Long id) {
        service.deleteItem(id);
    }
}

