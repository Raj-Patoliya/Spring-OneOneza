package net.javaguides.springboot.Service;

import java.util.List;

import net.javaguides.springboot.Model.Subject;

public interface SubjectService {
	void saveSubject(Subject subject);
	List<Subject> getAllSubjectByDeptCodeAndSem(String deptCode,String semester,String year);
	List<Subject> getAllSubjectByTeacherId(String teacherId);
	Subject getSubjectBySubjectCode(String subjectCode);
}
