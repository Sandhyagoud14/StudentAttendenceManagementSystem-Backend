package com.attendance.repository;

import com.attendance.model.AttendanceSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AttendanceSessionRepository extends JpaRepository<AttendanceSession, Long> {
    Optional<AttendanceSession> findByToken(String token);
    void deleteBySubject_Id(Long subjectId);
    void deleteByFaculty_Id(Long facultyId);
    java.util.List<AttendanceSession> findByFaculty_IdAndActiveTrue(Long facultyId);
}
