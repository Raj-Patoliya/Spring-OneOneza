package net.javaguides.springboot.Service;

import java.util.List;

import net.javaguides.springboot.Model.Student;

public interface StudentService {
	List<Student> getAllStudents();
	void studentSave(Student student);
	List<Student> getStudentDeptWise(String deptCode);
	Student studentAuth(String userId,String password);
	void studentStatus(Long sId,String status);
	
}
