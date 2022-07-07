package net.javaguides.springboot.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.javaguides.springboot.Model.Student;
import net.javaguides.springboot.Model.Teacher;
import net.javaguides.springboot.Repository.StudentRepository;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private StudentRepository studentRepository;
	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	@Override
	public void studentSave(Student student) {
		this.studentRepository.save(student);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Student> getStudentDeptWise(String deptCode) {
		Query query = entityManager.createQuery("select s from Student s where s.deptCode = '"+deptCode+"'");
		List<Student> student = query.getResultList();
		return student;
	}
	
	public Student studentAuth(String userId, String password)
	{
		Student teacher =null;
		try {
			Query query = entityManager.createQuery("select t from Student t where t.userId = '"+userId+"' and t.password = '"+password+"'");
			teacher = (Student) query.getSingleResult();

		} catch (NoResultException e) {
			System.out.println(e);
		}
		if(teacher == null )
		{			
			return null;
		}
		else
		{
			return teacher;
		}
	}
	@Override
	public void studentStatus(Long sId, String status) {
		Query query = entityManager.createQuery("update Student u set u.status ='"+status+"' where u.sId = "+sId);
		query.executeUpdate();
	}
}
