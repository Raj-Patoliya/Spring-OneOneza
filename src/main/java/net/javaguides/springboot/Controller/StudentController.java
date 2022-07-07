package net.javaguides.springboot.Controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.javaguides.springboot.Model.Assignment;
import net.javaguides.springboot.Model.Login;
import net.javaguides.springboot.Model.Student;
import net.javaguides.springboot.Model.Submission;
import net.javaguides.springboot.Model.Teacher;
import net.javaguides.springboot.Service.AssignmentService;
import net.javaguides.springboot.Service.NoticeService;
import net.javaguides.springboot.Service.StudentService;
import net.javaguides.springboot.Service.SubjectService;
import net.javaguides.springboot.Service.SubmissionService;
import net.javaguides.springboot.Service.TeacherService;

@Controller
public class StudentController {

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private AssignmentService assignmentService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private SubmissionService submissionService;
	
	@GetMapping("StudentLogin")
	public String StudentLogin()
	{
		return "Student/StudentLoginPage";
	}
	
	@PostMapping("studentAuth")
	public String studentAuth(@ModelAttribute("login") Login login,HttpServletResponse response,HttpServletRequest request)
	{
			String userId = login.getUserId();
			String password = login.getPassword();		
			Student student=   studentService.studentAuth(userId, password);
			if(student== null)
			{
				return "Studenytt/TeacherLoginPage";
			}
			else
			{
				Cookie username = new Cookie("studentId",student.getUserId());
				Cookie year = new Cookie("year",student.getYear());
				Cookie semester = new Cookie("semester",student.getSemester());
				Cookie deptCode = new Cookie("deptCode", student.getDeptCode());
				response.addCookie(username);
				response.addCookie(deptCode);
				response.addCookie(year);
				response.addCookie(semester);
				System.out.println(student.getDeptCode());
				System.out.println(student.getUserId());
				return "redirect:/StudentHome";

			}
	}
	public static boolean checkLogin(HttpServletResponse response,HttpServletRequest request)
	{
		Cookie ck[] = request.getCookies();
		if(ck != null)
		{
			for(int i = 0;i<ck.length;i++) 
			{
				if(ck[i].getName().equals("deptCode") || ck[i].getName().equals("teacherId") )
				{
					return true;
				}
			}
			return false;
		}
		else
		{
			return false;
		}
	}
	
	@GetMapping("/StudentHome")
	public String home(HttpServletResponse response,HttpServletRequest request)
	{
		if( checkLogin(response, request) == false)
		{
			return "redirect:/index";
		}
		else
		{
			return "Student/Home";	
		}
	}
	@GetMapping("assignmentListForStudent")
	public String assignmentListForStudent(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		if(checkLogin(response, request) == false)
		{
			return "redirect:/index";
		}
		else
		{
			String year ="";
			String semester ="";
			Cookie ck[] = request.getCookies();
			if(ck != null)
			{
				for(int i = 0;i<ck.length;i++) 
				{
					if(ck[i].getName().equals("year"))
					{
						year = ck[i].getValue(); 
					}
					else if(ck[i].getName().equals("semester"))
					{
						semester = ck[i].getValue(); 
					}
				}
				model.addAttribute("assignmentList", assignmentService.getAllAssignmentByYearAndSemester(year, semester));
				return "Student/assignmentListForStudent";
			}
			else
			{
				return "redirect:/index";	
			}
		}
		
	}
	
	@GetMapping("/studentNotice")
	public String studentNotice(HttpServletResponse response,HttpServletRequest request,Model model)
	{
		if( checkLogin(response, request) == false)
		{
			return "redirect:/index";
		}
		else
		{
			model.addAttribute("noticeList", noticeService.getAllNotice());
			return "Student/noticeList";	
		}
	}
	
	@PostMapping("/submissionPage")
	public String submitfile(@RequestParam("assId" )long assId,Model model,HttpServletResponse response,HttpServletRequest request)
	{
		Cookie assignmentId = new Cookie("assId", String.valueOf(assId));
		response.addCookie(assignmentId );
		model.addAttribute("assignmentList", assignmentService.getAssignmentById(assId));
		return "Student/submissionPage";	
	}
	
	@PostMapping("submitfile")
	public String submitfile(HttpServletResponse response,HttpServletRequest request,Model model,@RequestParam("submission") MultipartFile filess) throws IOException 
	{
		String assId ="";
		String studentId ="";
		Cookie ck[] = request.getCookies();
		Submission submission = new Submission();
		if(ck != null)
		{
			for(int i = 0;i<ck.length;i++) 
			{
				Cookie cookie = ck[i]; 
				if(ck[i].getName().equals("assId"))
				{
					assId= ck[i].getValue(); 
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				if(ck[i].getName().equals("studentId"))
				{
					studentId= ck[i].getValue(); 
				}
			}
			submission.setAssId(Long.parseLong(assId));
			submission.setStudentId(studentId);
			LocalDate date = LocalDate.now();
			submission.setSubmissionDate(Date.valueOf(date).toString());
			submission.setSubmission(filess.getBytes());
			submissionService.saveSumission(submission);	
			return "Student/assignmentListForStudent";
		}
		else
		{
			return "redirect:/index";	
		}
	}
}
