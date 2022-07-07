package net.javaguides.springboot.Service;

import java.util.List;

import net.javaguides.springboot.Model.Assignment;

public interface AssignmentService {

	Assignment getAssignmentById(long assId);
	List<Assignment> getAllAssignmentByTeacherId(String teacherId);
	List<Assignment> getAllAssignmentByYearAndSemester(String year,String semester);
	void saveAssignment(Assignment assignment);
	
	void deleteAssignment(long assId);
}
