package net.javaguides.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.Model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
