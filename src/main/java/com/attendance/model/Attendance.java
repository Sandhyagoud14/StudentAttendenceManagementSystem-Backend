package com.attendance.model;
import jakarta.persistence.*; import java.time.LocalDate;
@Entity @Table(uniqueConstraints=@UniqueConstraint(columnNames={"student_id","subject_id","attendance_date"})) public class Attendance {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @ManyToOne(optional=false) @JoinColumn(name="student_id",nullable=false) public Student student;
 @ManyToOne(optional=false) @JoinColumn(name="subject_id",nullable=false) public Subject subject;
 @Column(name="attendance_date",nullable=false) public LocalDate attendanceDate;
 @Column(nullable=false) public String status;
 @Column(name="session_id") public Long sessionId;
 public String markedBy;
 public Attendance(){}
}
