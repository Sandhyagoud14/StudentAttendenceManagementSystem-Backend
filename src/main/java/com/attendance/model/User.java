package com.attendance.model;
import jakarta.persistence.*; import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity @Table(name="users") public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(unique=true,nullable=false) public String username;
 @JsonIgnore @Column(nullable=false) public String password;
 @Column(nullable=false) public String role;
 public User(){} public User(String u,String p,String r){username=u;password=p;role=r;}
}
