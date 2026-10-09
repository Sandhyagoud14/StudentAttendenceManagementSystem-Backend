package com.attendance.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "attendance_audit")
public class AttendanceAudit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(nullable = false)
    public Long attendanceId;
    @Column(nullable = false)
    public String action;
    public String studentCode;
    public String studentName;
    public String subjectCode;
    public String subjectName;
    public LocalDate attendanceDate;
    public String previousStatus;
    public String newStatus;
    @Column(nullable = false)
    public String changedBy;
    @Column(nullable = false)
    public Instant changedAt;

    public AttendanceAudit() {}
}
