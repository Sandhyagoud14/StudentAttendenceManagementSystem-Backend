package com.attendance.model;
import jakarta.persistence.*;
@Entity @Table(uniqueConstraints=@UniqueConstraint(columnNames="student_id")) public class Student {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(name="student_id",nullable=false,unique=true) public String studentId;
 @Column(nullable=false) public String name;
 @Column(nullable=false) public String email;
 public String phone,department; public Integer year; public String section;
 @OneToOne @JoinColumn(name="username",referencedColumnName="username") public User user;
 public Student(){}
}
