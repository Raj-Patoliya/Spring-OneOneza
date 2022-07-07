package net.javaguides.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.Model.Assignment;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long>{

}
