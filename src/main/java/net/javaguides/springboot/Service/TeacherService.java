package net.javaguides.springboot.Service;

import java.util.List;

import net.javaguides.springboot.Model.Teacher;

public interface TeacherService {
	List<Teacher> getAllTeacher();
	void teacherSave(Teacher teacher);
	void teacherStatus(Long sId,String status);
	List<Teacher> findTeacherByDeptCode(String deptId);
	Teacher teacherAuth(String userId, String password);

}
