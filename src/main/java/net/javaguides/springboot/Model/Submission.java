package net.javaguides.springboot.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="submission")
public class Submission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long submissionId;
	
	private long assId;
	
	public long getAssId() {
		return assId;
	}
	public void setAssId(long assId) {
		this.assId = assId;
	}
	private String studentId;
	private String submissionDate;
	@Lob
	private byte[] submission;
	
	private String content_type;
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	public long getSubmissionId() {
		return submissionId;
	}
	public void setSubmissionId(long submissionId) {
		this.submissionId = submissionId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}
	public byte[] getSubmission() {
		return submission;
	}
	public void setSubmission(byte[] submission) {
		this.submission = submission;
	}
		
}
