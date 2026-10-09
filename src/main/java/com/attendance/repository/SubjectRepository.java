package com.attendance.repository;
import com.attendance.model.Subject; import org.springframework.data.jpa.repository.JpaRepository;
public interface SubjectRepository extends JpaRepository<Subject,Long>{ java.util.List<Subject> findByFaculty_Id(Long facultyId); }
