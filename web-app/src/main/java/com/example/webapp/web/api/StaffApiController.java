package com.example.webapp.web.api;


import com.example.webapp.services.StaffService;
import com.example.webapp.web.model.Staff;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/staff")
public class StaffApiController {


    private final StaffService staffService;

    public StaffApiController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public List<Staff> getAll() {
        return staffService.getStaffs();
    }

    @GetMapping("/{id}")
    public Staff getStaff(@PathVariable(name = "id") UUID id) {
        return staffService.getStaffById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Staff addStaff(@RequestBody Staff staff) {
        return this.staffService.addStaff(staff);
    }

    @PutMapping("/{id}")
    public Staff updateStaff(@PathVariable(name = "id") UUID id, @RequestBody Staff staff) {
        return this.staffService.updateStaff(staff);
    }

    @DeleteMapping("/{id}")
    public void deleteStaff(@PathVariable(name = "id") UUID id) {
        this.staffService.deleteStaff(id);
    }
}
