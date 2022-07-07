package net.javaguides.springboot.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.Model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{

}
