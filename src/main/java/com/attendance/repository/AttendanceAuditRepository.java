package com.attendance.repository;

import com.attendance.model.AttendanceAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendanceAuditRepository extends JpaRepository<AttendanceAudit, Long> {
    List<AttendanceAudit> findAllByOrderByChangedAtDesc();
    List<AttendanceAudit> findByStudentCodeOrderByChangedAtDesc(String studentCode);
}
