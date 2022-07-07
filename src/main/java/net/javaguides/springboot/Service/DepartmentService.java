package net.javaguides.springboot.Service;

import java.util.List;

import net.javaguides.springboot.Model.Department;

public interface DepartmentService {
	List<Department> getAlDepartments();
	void departmentSave(Department department);
}
	