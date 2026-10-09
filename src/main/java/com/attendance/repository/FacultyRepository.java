package com.attendance.repository;
import com.attendance.model.Faculty; import org.springframework.data.jpa.repository.JpaRepository;
public interface FacultyRepository extends JpaRepository<Faculty,Long>{ }
