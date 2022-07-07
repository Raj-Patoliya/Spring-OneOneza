package net.javaguides.springboot.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.javaguides.springboot.Model.Submission;
import net.javaguides.springboot.Repository.SubmissionRepository;

@Service
@Transactional
public class SubmissionServiceImpl implements SubmissionService{

	@Autowired
	private SubmissionRepository submissionRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void saveSumission(Submission submission) {
		this.submissionRepository.save(submission);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Submission> getSubmissionByAssId(long assId) {
		Query query = entityManager.createQuery("select s from Submission s where s.assId = "+assId);
		List<Submission> submissions = query.getResultList();
		return submissions;
	}

	@Override
	public Submission getSubmissionBySubmissionId(long submissionId) {	
		Optional<Submission> optional = submissionRepository.findById(submissionId);
		Submission submission = null;
		if(optional.isPresent())
		{
			submission = optional.get();
		}
		return submission;
	}

	@Override
	public void deleteSubmissionById(long submissionId) {
		// TODO Auto-generated method stub
		this.submissionRepository.deleteById(submissionId);
	}
}
