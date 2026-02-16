package com.example.webapp.services;

import com.example.webapp.data.entiry.Position;
import com.example.webapp.data.entiry.StaffMember;
import com.example.webapp.data.repository.StaffRepository;
import com.example.webapp.web.model.Staff;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getStaffs() {
        return staffRepository.findAll().stream()
                .map(this::getStaffFromEntity).toList();
    }

    public Staff getStaffById(UUID id) {
        return staffRepository.findById(id).map(this::getStaffFromEntity).orElse(null);
    }

    public Staff addStaff(Staff staff) {
        StaffMember staffEntity = staffRepository.save(getStaffMemberFromStaff(staff));
        return getStaffFromEntity(staffEntity);
    }

    public void deleteStaff(UUID id) {
        staffRepository.deleteById(id);
    }

    public Staff updateStaff(Staff staff) {
        StaffMember staffEntity = getStaffMemberFromStaff(staff);
        staffEntity = staffRepository.save(staffEntity);
        return getStaffFromEntity(staffEntity);
    }

    private Staff getStaffFromEntity(StaffMember staff) {
        return new Staff(staff.getEmployeeId(), staff.getFirstName(), staff.getLastName(), staff.getPosition().toString());
    }

    private StaffMember getStaffMemberFromStaff(Staff staff) {
        return new StaffMember(staff.getId(), staff.getFirstName(), staff.getLastName(), Position.valueOf(staff.getPosition()));
    }
}
