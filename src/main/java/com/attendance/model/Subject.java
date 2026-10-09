package com.attendance.model;
import jakarta.persistence.*;
@Entity @Table(uniqueConstraints=@UniqueConstraint(columnNames="subject_code")) public class Subject {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(name="subject_code",nullable=false,unique=true) public String subjectCode;
 @Column(nullable=false) public String subjectName;
 public String department; public Integer year; public String section;
 @ManyToOne @JoinColumn(name="faculty_id") public Faculty faculty;
 public Subject(){}
}
