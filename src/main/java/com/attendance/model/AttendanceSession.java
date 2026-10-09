package com.attendance.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "attendance_sessions", uniqueConstraints = @UniqueConstraint(columnNames = "token"))
public class AttendanceSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, unique = true, length = 36)
    public String token;

    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    public Subject subject;

    @ManyToOne(optional = false)
    @JoinColumn(name = "faculty_id", nullable = false)
    public Faculty faculty;

    @Column(nullable = false)
    public Instant createdAt;

    @Column(nullable = false)
    public Instant expiresAt;

    @Column(name = "session_date")
    public LocalDate sessionDate;

    @Column(nullable = false)
    public boolean active = true;

    public AttendanceSession() {}
}
