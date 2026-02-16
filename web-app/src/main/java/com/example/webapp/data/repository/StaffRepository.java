package com.example.webapp.data.repository;

import com.example.webapp.data.entiry.StaffMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StaffRepository extends JpaRepository<StaffMember, UUID> {
}
