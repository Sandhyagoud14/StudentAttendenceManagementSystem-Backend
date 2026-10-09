package com.attendance.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name="class_schedules", uniqueConstraints=@UniqueConstraint(columnNames={"subject_id","day_of_week","start_time"}))
public class ClassSchedule {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;
    @ManyToOne(optional=false) @JoinColumn(name="subject_id",nullable=false)
    public Subject subject;
    @Column(name="day_of_week",nullable=false,length=12)
    public String dayOfWeek;
    @Column(name="start_time",nullable=false)
    public LocalTime startTime;
    @Column(name="end_time",nullable=false)
    public LocalTime endTime;
    public String room;
    @Column(nullable=false)
    public boolean active=true;

    public ClassSchedule() {}
}
