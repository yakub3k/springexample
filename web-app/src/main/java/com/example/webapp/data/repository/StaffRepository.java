package com.example.webapp.data.repository;

import com.example.webapp.data.entiry.StaffMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<StaffMember, String> {
}
