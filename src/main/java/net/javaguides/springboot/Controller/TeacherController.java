package net.javaguides.springboot.Controller;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.*;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import net.javaguides.springboot.Model.Subject;
import net.javaguides.springboot.Model.Submission;
import net.javaguides.springboot.Model.Teacher;
import net.javaguides.springboot.Service.AssignmentService;
import net.javaguides.springboot.Service.NoticeService;
import net.javaguides.springboot.Service.StudentService;
import net.javaguides.springboot.Service.SubjectService;
import net.javaguides.springboot.Service.SubmissionService;
import net.javaguides.springboot.Service.TeacherService;

@Controller
public class TeacherController {

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
	@GetMapping("TeacherLogin")
	public String TeacherLogin()
	{
		return "Teacher/TeacherLoginPage";
	}
	@GetMapping("/index")
	public String index()
	{
		return "Teacher/index";
	}
	
	@GetMapping("/")
	public String start()
	{
		return "Teacher/index";
	}

	@PostMapping("teacherAuth")
	public String teacherAuth(@ModelAttribute("login") Login login,HttpServletResponse response,HttpServletRequest request)
	{
			String userId = login.getUserId();
			String password = login.getPassword();		
			Teacher teacher =   teacherService.teacherAuth(userId, password);
			if(teacher == null)
			{
				return "Teacher/TeacherLoginPage";
			}
			else
			{
				Cookie username = new Cookie("teacherId",teacher.getUserId());
				Cookie deptCode = new Cookie("deptCode", teacher.getDeptCode());
				response.addCookie(username);
				response.addCookie(deptCode);
				System.out.println(teacher.getDeptCode());
				System.out.println(teacher.getUserId());
				return "redirect:/TeacherHome";

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
					System.out.println(ck[i].getValue());
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
	
	@GetMapping("/logout")
	public String logout(HttpServletResponse response,HttpServletRequest request)
	{
		if(checkLogin(response, request) != false)
		{
			Cookie ck[] = request.getCookies();
			for(int i = 0;i<ck.length;i++) 
			{
				Cookie cookie = ck[i];
				if(ck[i].getName().equals("deptCode") || ck[i].getName().equals("teacherId") ||ck[i].getName().equals("semester")||ck[i].getName().equals("year") )
				{
					
					System.out.println(cookie.getValue());
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
			return "redirect:/index";
		}
		else
		{
			return "redirect:/index";		
		}
	}

	
	@GetMapping("studentListForTeacher")
	public String studentList(Model model,HttpServletResponse response,HttpServletRequest request)
	{
		if( checkLogin(response, request) == false)
		{
			return "redirect:/index";
		}
		else
		{
			String deptCode ="";
			Cookie ck[] = request.getCookies();
				for(int i = 0;i<ck.length;i++) 
				{
					if(ck[i].getName().equals("deptCode"))
					{
						deptCode = ck[i].getValue(); 
					}
				}
				model.addAttribute("studentList",studentService.getStudentDeptWise(deptCode));
				return "Teacher/studentListForTeacher";
		}
	}
	@GetMapping("/TeacherHome")
	public String home(HttpServletResponse response,HttpServletRequest request)
	{
		if( checkLogin(response, request) == false)
		{
			return "redirect:/index";
		}
		else
		{
			return "Teacher/Home";	
		}
	}
	
	@GetMapping("assignmentListForTeacher")
	public String assignmentListForTeacher(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		if(checkLogin(response, request) == false)
		{
			return "redirect:/index";
		}
		else
		{
			String teacherId ="";
			Cookie ck[] = request.getCookies();
			if(ck != null)
			{
				for(int i = 0;i<ck.length;i++) 
				{
					if(ck[i].getName().equals("teacherId"))
					{
						teacherId = ck[i].getValue(); 
					}
				}
				model.addAttribute("subjectList", subjectService.getAllSubjectByTeacherId(teacherId));
				model.addAttribute("assignmentList", assignmentService.getAllAssignmentByTeacherId(teacherId));
				return "Teacher/assignmentListForTeacher";
			}
			else
			{
				return "redirect:/index";	
			}
		}
		
	}
	
	@PostMapping("assignmentAdd")
	public String assignmentAdd(@ModelAttribute("assignment") Assignment assignment,@RequestParam("attechemnt") MultipartFile filess,HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		if(checkLogin(response, request) == false)
		{
			return "redirect:/index";
		}
		else
		{
			LocalDate date = LocalDate.now();
			Subject subject = subjectService.getSubjectBySubjectCode(assignment.getSubjectCode());
			assignment.setTeacherId(subject.getTeacherId());
			assignment.setDeptCode(subject.getDeptCode());
			assignment.setYear(subject.getYear());
			assignment.setSemester(subject.getSemester());
			assignment.setAssignDate(date.toString());
			assignment.setAttechment(filess.getBytes());
			assignment.setContent_type(filess.getContentType());
			assignmentService.saveAssignment(assignment);
			System.out.println(filess.getContentType());
			return "redirect:assignmentListForTeacher";
		}
	}
	
	@GetMapping("noticeShow")
	public String noticeShow(Model model)
	{
		model.addAttribute("noticeList", noticeService.getAllNotice());
		return "Teacher/noticeList";
	}
	
	@GetMapping("deleteAssignment/{id}")
	public String deleteAssignment(@PathVariable("id") long id) 
	{
		this.assignmentService.deleteAssignment(id);
		return "redirect:/assignmentListForTeacher";
	}
	
	
	  @GetMapping("downloadAssignment/{assId}") 
	  public ResponseEntity<ByteArrayResource> downloadAssignment(@PathVariable Long assId) { 
		  Assignment assignment = assignmentService.getAssignmentById(assId); 
		  return ResponseEntity.ok()
				  .contentType(MediaType.parseMediaType(assignment.getContent_type()))
				  .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,"attechment:filename	=\""+assignment.getTitle()+"\"")
				  .body(new ByteArrayResource(assignment.getAttechment()));
	  }
	  
	  @GetMapping("downloadSubmission/{submissionId}") 
	  public ResponseEntity<ByteArrayResource> downloadSubmission(@PathVariable Long submissionId) { 
		  Submission submission = submissionService.getSubmissionBySubmissionId(submissionId);
		  return ResponseEntity.ok()
				  .contentType(MediaType.parseMediaType(submission.getContent_type()))
				  .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,"attechment:filename	=\""+submission.getStudentId()+"\"")
				  .body(new ByteArrayResource(submission.getSubmission()));
	  }
	 @RequestMapping("viewSubmitedAssignment")
	 public String viewSubmitedAssignment(@RequestParam("assId") long assId ,Model model)
	 {
		 model.addAttribute("submissionList", submissionService.getSubmissionByAssId(assId));
		 return "Teacher/viewSubmitedAssignment";
	 }
	 	
}
