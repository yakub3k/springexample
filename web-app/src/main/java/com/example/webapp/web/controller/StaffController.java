package com.example.webapp.web.controller;

import com.example.webapp.data.repository.StaffRepository;
import com.example.webapp.web.model.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {

    private final StaffRepository staffRepository;

    public StaffController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @GetMapping
    public String getStaffPage(Model model) {
        List<Staff> staffs = staffRepository.findAll()
                .stream()
                .map(s ->
                        new Staff(s.getEmployeeId(), s.getFirstName(), s.getLastName(), s.getPosition().toString()))
                .toList();
        model.addAttribute("staffs", staffs);
        return "staff";
    }
}
