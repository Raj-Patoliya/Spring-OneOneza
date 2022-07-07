package net.javaguides.springboot.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.javaguides.springboot.Model.Assignment;
import net.javaguides.springboot.Repository.AssignmentRepository;

@Service
@Transactional
public class AssignmentServiceImpl implements AssignmentService {

	@Autowired
	private EntityManager entityManager;
	@Autowired
	private AssignmentRepository assignmentRepository;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Assignment> getAllAssignmentByTeacherId(String teacherId) {	
		Query query = entityManager.createQuery("select a from Assignment a where a.teacherId = '"+teacherId+"'");
		List<Assignment> assignment = query.getResultList();
		return assignment;
	}

	@Override
	public void saveAssignment(Assignment assignment) {
		this.assignmentRepository.save(assignment);
	}
	@Override
	public void deleteAssignment(long assId) {
		this.assignmentRepository.deleteById(assId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Assignment> getAllAssignmentByYearAndSemester(String year, String semester) {
		Query query = entityManager.createQuery("select a from Assignment a where a.year = '"+year+"' and a.semester = '"+semester+"'");
		List<Assignment> assignment = query.getResultList();
		return assignment;
	}

	@Override
	public Assignment getAssignmentById(long assId) {
		Optional<Assignment> optional =assignmentRepository.findById(assId);
		Assignment assignment = null;
		if(optional.isPresent())
		{
			assignment = optional.get(); 
		}
		return assignment;
	}

}
