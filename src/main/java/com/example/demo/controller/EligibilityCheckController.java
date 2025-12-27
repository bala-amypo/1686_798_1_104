// package com.example.demo.controller;
// import com.example.demo.model.EligibilityCheckRecord;
// import com.example.demo.service.EligibilityCheckService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/eligibility")
// public class EligibilityCheckController {

//     private final EligibilityCheckService service;

//     public EligibilityCheckController(EligibilityCheckService service) {
//         this.service = service;
//     }

//     // POST /api/eligibility/validate/{employeeId}/{deviceItemId}
//     @PostMapping("/validate/{employeeId}/{deviceItemId}")
//     public EligibilityCheckRecord validateEligibility(
//             @PathVariable Long employeeId,
//             @PathVariable Long deviceItemId
//     ) {
//         return service.validateEligibility(employeeId, deviceItemId);
//     }

//     // GET /api/eligibility/employee/{employeeId}
//     @GetMapping("/employee/{employeeId}")
//     public List<EligibilityCheckRecord> getChecksByEmployee(
//             @PathVariable Long employeeId
//     ) {
//         return service.getChecksByEmployee(employeeId);
//     }
// }

// package com.example.demo.controller;

// import com.example.demo.model.EligibilityCheckRecord;
// import com.example.demo.service.EligibilityCheckService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/eligibility")
// public class EligibilityController {

//     @Autowired
//     private EligibilityCheckService eligibilityService;

//     @PostMapping("/validate")
//     public ResponseEntity<EligibilityCheckRecord> validateEligibility(@RequestParam Long employeeId, @RequestParam Long deviceItemId) {
//         return ResponseEntity.ok(eligibilityService.validateEligibility(employeeId, deviceItemId));
//     }

//     @GetMapping("/employee/{employeeId}")
//     public ResponseEntity<List<EligibilityCheckRecord>> getChecksByEmployee(@PathVariable Long employeeId) {
//         return ResponseEntity.ok(eligibilityService.getChecksByEmployee(employeeId));
//     }
// }


package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.EligibilityCheckRecord;
import com.example.demo.service.EligibilityCheckService;

@RestController
@RequestMapping("/api/eligibility")
public class EligibilityCheckController {

    private final EligibilityCheckService service;

    public EligibilityCheckController(EligibilityCheckService service) {
        this.service = service;
    }

    @PostMapping("/validate/{employeeId}/{deviceItemId}")
    public EligibilityCheckRecord validate(
            @PathVariable Long employeeId,
            @PathVariable Long deviceItemId) {

        return service.validateEligibility(employeeId, deviceItemId);
    }

    @GetMapping("/employee/{employeeId}")
    public List<EligibilityCheckRecord> byEmployee(@PathVariable Long employeeId) {
        return service.getChecksByEmployee(employeeId);
    }

    @GetMapping("/{id}")
    public EligibilityCheckRecord getById(@PathVariable Long id) {
        return service.getById(id);
    }
}

