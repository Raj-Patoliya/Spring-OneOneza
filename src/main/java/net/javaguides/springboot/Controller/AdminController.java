package net.javaguides.springboot.Controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.Model.Department;
import net.javaguides.springboot.Model.Login;
import net.javaguides.springboot.Model.Notice;
import net.javaguides.springboot.Model.Student;
import net.javaguides.springboot.Model.Subject;
import net.javaguides.springboot.Model.Teacher;
import net.javaguides.springboot.Service.DepartmentService;
import net.javaguides.springboot.Service.NoticeService;
import net.javaguides.springboot.Service.StudentService;
import net.javaguides.springboot.Service.SubjectService;
import net.javaguides.springboot.Service.TeacherService;

@Controller
public class AdminController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("admin")
	public String AdminLogin() {
		return "Admin/AdminLoginPage";
	}
	@GetMapping("dashboard")
	public String AdminIndex() {
		return "Admin/index";
	}
	@PostMapping("adminAuth")
	public String adminAuth(@ModelAttribute("login") Login login)
	{
		if(login.getUserId().equals("Admin") && login.getPassword().equals("123"))
		{
			return "redirect:dashboard";
		}
		else
		{
			return "redirect:/admin";
		}		
	}
// *****************************Department Operations*********************************************	
	@GetMapping("/departmentList")
	public String departmentList(Model model)
	{
		model.addAttribute("departmentList",departmentService.getAlDepartments());
		return "Admin/departmentList";
	}
	
	@GetMapping("/departmentAdd")
	public String departmentAdd(Model model)
	{
		return "Admin/departmentAdd";
	}
	
	@PostMapping("departmentSave")
	public String departmentSave(@ModelAttribute("department") Department department)
	{
		departmentService.departmentSave(department);
		return "redirect:departmentList";
	}
	
//**********************************Student Operations********************************************	
	
	@GetMapping("/studentDeptList")
	public String studentList(Model model)
	{
		model.addAttribute("departmentList", departmentService.getAlDepartments());
		return "Admin/departmentSelectForStudent";
	}
	@PostMapping("studentList")
	public String studentList(@ModelAttribute("department") Department department,Model model)
	{	
		model.addAttribute("studentList", studentService.getStudentDeptWise(department.getDeptCode()));
		return "Admin/studentList";
	}	
	
	@GetMapping("/studentAdd")
	public String studentAdd(Model model)
	{
		model.addAttribute("departmentList", departmentService.getAlDepartments());
		return "Admin/studentAdd";
	}
	@PostMapping("studentSave")
	public String studentSave(@ModelAttribute("student") Student student,Model model)
	{
		
		System.out.println("Password 123 --> "+student.getPassword());
		System.out.println("student object:--"+student.toString());
		student.setStatus("Active");
		studentService.studentSave(student);
		model.addAttribute("departmentList", departmentService.getAlDepartments());
		return "Admin/departmentSelectForStudent";
	}
	
	@RequestMapping("studentStatus")
	public String studentStatus(Model model,@RequestParam("sId") long sId,@RequestParam("status") String status,@RequestParam("deptCode") String deptCode)
	{
		if(status.equals("Active"))
		{
			studentService.studentStatus(sId, "Deactive");
			model.addAttribute("studentList",studentService.getStudentDeptWise(deptCode));
			return "Admin/studentList";
		}
		else
		{
			studentService.studentStatus(sId, "Active");
			model.addAttribute("studentList",studentService.getStudentDeptWise(deptCode));
			return "Admin/studentList";
		}
	}


//*********************************Teacher's Operations **************************************

	@GetMapping("/teacherDeptList")
	public String teacherDeptList(Model model)
	{
		model.addAttribute("departmentList", departmentService.getAlDepartments());
		return "Admin/departmentSelectForTeacher";
	}		
	@RequestMapping("/teacherList")
	public String teacherList(@ModelAttribute("department") Department department,Model model)
	{	
		model.addAttribute("teacherList",teacherService.findTeacherByDeptCode(department.getDeptCode()));
		return "Admin/teacherList";
	}
	@GetMapping("/teacherAdd")
	public String teacherAdd(Model model)
	{
		Teacher teacher = new Teacher();
		model.addAttribute("teacherList", teacherService.getAllTeacher());
		teacher.setPassword("password");
		teacher.setStatus("Active");
		model.addAttribute("teacher", teacher);
		model.addAttribute("departmentList", departmentService.getAlDepartments());
		return "Admin/teacherAdd";
	}
	@PostMapping("teacherSave")
	public String teacherSave(@ModelAttribute("teacher") Teacher teacher,Model model)
	{
		teacher.setPassword("password");
		teacher.setStatus("Active");
		teacherService.teacherSave(teacher);
		model.addAttribute("departmentList", departmentService.getAlDepartments());
		return "Admin/departmentSelectForTeacher";
	}
	
	@PostMapping("teacherStatus")
	public String Status(Model model,@RequestParam("tId") long tId,@RequestParam("status") String status,@RequestParam("deptCode") String deptCode)
	{
		if(status.equals("Active"))
		{
			teacherService.teacherStatus(tId, "Deactive");
			model.addAttribute("teacherList",teacherService.findTeacherByDeptCode(deptCode));
			return "Admin/teacherList";
		}
		else
		{
			teacherService.teacherStatus(tId, "Active");
			model.addAttribute("teacherList",teacherService.findTeacherByDeptCode(deptCode));
			return "Admin/teacherList";
		}
	}
//*************************************Subject Operations************************************
	@GetMapping("/subjectAdd")
	public String subjectAdd(Model model)
	{		
		model.addAttribute("departmentList", departmentService.getAlDepartments());
		return "Admin/subjectAdd";
	}
	@GetMapping("/subjectListSelect")
	public String subjectListSelect(Model model)
	{		
		model.addAttribute("departmentList", departmentService.getAlDepartments());
		return "Admin/subjectListSelect";
	}
	
	@PostMapping("subjectAddNextPage")
	public String subjectAddNextPage(@ModelAttribute("department") Department department,Model model)
	{	
		
		model.addAttribute("teacherList", teacherService.findTeacherByDeptCode(department.getDeptCode()));
		model.addAttribute("departmentList", department);
		return "Admin/subjectAddNextPage";
	}	
	
	@PostMapping("subjectList")
	public String subjectList(@ModelAttribute("subject") Subject subject,Model model)
	{	
		model.addAttribute("subjectList", subjectService.getAllSubjectByDeptCodeAndSem(subject.getDeptCode(), subject.getSemester(),subject.getYear()));
		return "Admin/subjectList";
	}
	
	@PostMapping("/subjectSave")
	private String subjectSave(@ModelAttribute("subject") Subject subject) {
		

		subjectService.saveSubject(subject);
		return "redirect:subjectAdd";
	}
	
	@GetMapping("/meet")
	public String meet(@RequestParam(value = "roomId", required = false) String roomId, Model model)
	{
		model.addAttribute("roomId",roomId);
		return "Teacher/Meet";
	}
//************************************Notice Operation *********************************************************

	@GetMapping("noticeAdd")
	public String noticeAdd()
	{
		return "Admin/noticeAdd";
	}
	@PostMapping("noticeSave")
	public String noticeSave(@ModelAttribute("notice") Notice notice)
	{
	    Date date = new Date();  
	    notice.setDate(date.toString());
		this.noticeService.noticeSave(notice);
		return "redirect:noticeList";
	}
	@GetMapping("noticeList")
	public String noticeList(Model model)
	{
		model.addAttribute("noticeList", noticeService.getAllNotice());
		return "Admin/noticeList";
	}
	
	@GetMapping("deleteNotice/{id}")
	public String deleteNotice(@PathVariable("id") long id) 
	{
		this.noticeService.deleteNoticeByNoticeId(id);
		return "redirect:/noticeList";
	}
}
