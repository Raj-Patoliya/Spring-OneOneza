package net.javaguides.springboot.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="assignment")
public class Assignment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long assId;
	@Column(name="deptCode")
	private String deptCode;
	
	@Column(name="teacherId")	
	private String teacherId;
	
	@Column(name="subjectCode")
	private String subjectCode;
	
	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	@Column(name="year")
	private String year;
	
	@Column(name="semester")
	private String semester;
	
	@Column(name="assignDate")
	private String assignDate;
	
	@Column(name="submissionDate")
	private String submissionDate;
	
	@Column(name="title")
	private String title;
	
	@Lob
	private byte[] attechment;
	
	@Column(name="content_type")
	private String content_type;
	
	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public long getAssId() {
		return assId;
	}

	public void setAssId(long assId) {
		this.assId = assId;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester= semester;
	}

	public String getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(String assignDate) {
		this.assignDate = assignDate;
	}

	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate= submissionDate;
	}

	public byte[] getAttechment() {
		return attechment;
	}

	public void setAttechment(byte[] attechment) {
		this.attechment = attechment;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
