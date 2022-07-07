package net.javaguides.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.Model.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission,Long> {

}
