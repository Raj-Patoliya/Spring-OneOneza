package net.javaguides.springboot.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.javaguides.springboot.Model.Teacher;
import net.javaguides.springboot.Repository.TeacherRepository;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;
		
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Teacher> getAllTeacher() {
		return teacherRepository.findAll();
	}
	@Override
	public void teacherSave(Teacher teacher) {
		this.teacherRepository.save(teacher);
	}
	
	@SuppressWarnings("unchecked")
	public List<Teacher> findTeacherByDeptCode(String deptCode)
	{
		Query query = entityManager.createQuery("select t from Teacher t where t.deptCode = '"+deptCode+"'");
		List<Teacher> teacherList =  query.getResultList();
		return teacherList;
	}
	
	public Teacher teacherAuth(String userId, String password)
	{
		Teacher teacher =null;
		try {
			Query query = entityManager.createQuery("select t from Teacher t where t.userId = '"+userId+"' and t.password = '"+password+"'");
			teacher = (Teacher) query.getSingleResult();

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
	public void teacherStatus(Long tId,String status) {
		Query query = entityManager.createQuery("update Teacher u set u.status ='"+status+"' where u.tId = "+tId);
		query.executeUpdate();
	}
}
