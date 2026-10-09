package com.attendance.model;
import jakarta.persistence.*;
@Entity @Table(uniqueConstraints=@UniqueConstraint(columnNames="faculty_id")) public class Faculty {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(name="faculty_id",nullable=false,unique=true) public String facultyId;
 @Column(nullable=false) public String name;
 @Column(nullable=false) public String email;
 public String phone,department;
 @OneToOne @JoinColumn(name="username",referencedColumnName="username") public User user;
 public Faculty(){}
}
