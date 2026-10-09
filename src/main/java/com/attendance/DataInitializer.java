package com.attendance;
import com.attendance.model.*; import com.attendance.repository.*; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration; import java.time.LocalDate; import java.util.List;
@Configuration public class DataInitializer {
 @Bean CommandLineRunner seed(UserRepository ur,StudentRepository sr,FacultyRepository fr,SubjectRepository subr,AttendanceRepository ar){return args->{
  // Seed the demo campus only on first startup. Re-seeding missing rows would
  // silently restore records that an administrator intentionally deleted.
  if(ur.count()>0||sr.count()>0||fr.count()>0||subr.count()>0||ar.count()>0)return;
  ur.findByUsername("admin").orElseGet(()->ur.save(new User("admin","admin123","ADMIN")));
  User fu=ur.findByUsername("faculty1").orElseGet(()->ur.save(new User("faculty1","faculty123","FACULTY")));
  User su=ur.findByUsername("student1").orElseGet(()->ur.save(new User("student1","student123","STUDENT")));
  String[] names={"Rahul","Priya","Kiran","Anjali"}; String[] codes={"S001","S002","S003","S004"};
  for(int i=0;i<codes.length;i++){if(sr.findByStudentId(codes[i]).isEmpty()){Student s=new Student();s.studentId=codes[i];s.name=names[i];s.email=names[i].toLowerCase()+"@college.edu";s.phone="987654321"+i;s.department="CSE";s.year=2;s.section="A";if(i==0)s.user=su;sr.save(s);}}
  Faculty f1=fr.findAll().stream().filter(f->"F001".equals(f.facultyId)).findFirst().orElseGet(()->{Faculty f=new Faculty();f.facultyId="F001";f.name="Dr. Kumar";f.email="kumar@college.edu";f.phone="9876500001";f.department="CSE";f.user=fu;return fr.save(f);});
  if(fr.findAll().stream().noneMatch(f->"F002".equals(f.facultyId))){Faculty f=new Faculty();f.facultyId="F002";f.name="Dr. Priya";f.email="priya.faculty@college.edu";f.phone="9876500002";f.department="CSE";fr.save(f);}
  String[] sc={"CS301","CS302","CS303","CS304"};String[] sn={"Data Structures","DBMS","Operating Systems","Computer Networks"};
  List<Subject> ss=subr.findAll();for(int i=0;i<sc.length;i++){final String code=sc[i];if(ss.stream().noneMatch(s->code.equals(s.subjectCode))){Subject s=new Subject();s.subjectCode=code;s.subjectName=sn[i];s.department="CSE";s.year=2;s.section="A";s.faculty=f1;subr.save(s);}}
  if(ar.count()==0&&!sr.findAll().isEmpty()&&!subr.findAll().isEmpty()){List<Student> students=sr.findAll();List<Subject> subjects=subr.findAll();for(int i=0;i<students.size();i++)for(int j=0;j<subjects.size();j++){Attendance a=new Attendance();a.student=students.get(i);a.subject=subjects.get(j);a.attendanceDate=LocalDate.now().minusDays(j+1);a.status=(i+j)%4==0?"ABSENT":"PRESENT";a.markedBy="faculty1";ar.save(a);}}
 };}
}
