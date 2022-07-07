package net.javaguides.springboot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.javaguides.springboot.Model.Department;
import net.javaguides.springboot.Repository.DepartmentRepository;
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;
	@Override
	public List<Department> getAlDepartments() {
		return departmentRepository.findAll();
	}
	@Override
	public void departmentSave(Department department) {
		this.departmentRepository.save(department);
	}

}
