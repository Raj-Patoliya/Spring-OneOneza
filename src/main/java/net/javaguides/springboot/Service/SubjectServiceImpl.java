package net.javaguides.springboot.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.Model.Subject;
import net.javaguides.springboot.Repository.SubjectRepository;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void saveSubject(Subject subject) {
		this.subjectRepository.save(subject);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getAllSubjectByDeptCodeAndSem(String deptCode, String semester, String year) {
		Query query = entityManager.createQuery("Select s from Subject s where s.year = '"+year+"' and s.semester = '"+semester+"'and s.deptCode ='"+deptCode+"'");
		List<Subject> subject = query.getResultList();
		return subject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getAllSubjectByTeacherId(String teacherId) {
		Query query = entityManager.createQuery("Select s from Subject s where s.teacherId = '"+teacherId+"'");
		List<Subject> subject = query.getResultList();
		return subject;
	}

	@Override
	public Subject getSubjectBySubjectCode(String subjectCode) {
		Query query = entityManager.createQuery("select s from Subject s where s.subjectCode = '"+subjectCode+"'");
		Subject subject = (Subject) query.getSingleResult();
		return subject;
	}
}
