package net.javaguides.springboot.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sId;
	
	@Column(name="fullName",nullable = false)
	private String fullName;
	
	@Column(name="rollNo",nullable = false)
	private String rollNo;


	@Column(name="deptCode",nullable = false)
	private String deptCode;
	@Column(name="enrolmentNo",nullable = false)
	private String enrolmentNo;
	
	@Column(name="userId",nullable = false)
	private String userId;
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@Column(name = "year",nullable = false)
	private String year;
	
	@Column(name = "semester",nullable = false)
	private String semester;
	
	@Column(name = "status",nullable = false)
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getsId() {
		return sId;
	}

	public void setsId(long sId) {
		this.sId = sId;
	}
	public String getDeptCode() {
		return deptCode;
	}
	
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getEnrolmentNo() {
		return enrolmentNo;
	}

	public void setEnrolmentNo(String enrolmentNo) {
		this.enrolmentNo = enrolmentNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		this.semester = semester;
	}	
}
