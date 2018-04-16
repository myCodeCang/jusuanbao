/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 认证审核Entity
 * @author luo
 * @version 2017-08-04
 */
public class RenzhengAudit extends DataEntity<RenzhengAudit> {
	
	private static final long serialVersionUID = 1L;
	

	
	private String userName;		// 用户名
	private String trueName;		// 真实姓名
	private String idCard;		// 身份证号
	private String schoolName;		// 学校名
	private String subjectName;		// 专业名称
	private String enrollmentYear;		// 入学年份
	private String schoolYear;		// 学年制
	private String education;		// 学历
	private String frontCardImg;		// 身份证正面照
	private String versoCardImg;		// 身份证反面照
	private String studentImg;		// 学生证照片
	private String status;		// 认证状态
	private String type;		// 认证类型
	
	public RenzhengAudit() {
		super();
	}

	public RenzhengAudit(String id){
		super(id);
	}

	@Length(min=0, max=255, message="用户名长度必须介于 0 和 255 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=255, message="真实姓名长度必须介于 0 和 255 之间")
	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	@Length(min=0, max=255, message="身份证号长度必须介于 0 和 255 之间")
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	@Length(min=0, max=255, message="学校名长度必须介于 0 和 255 之间")
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	@Length(min=0, max=255, message="专业名称长度必须介于 0 和 255 之间")
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	@Length(min=0, max=4, message="入学年份长度必须介于 0 和 4 之间")
	public String getEnrollmentYear() {
		return enrollmentYear;
	}

	public void setEnrollmentYear(String enrollmentYear) {
		this.enrollmentYear = enrollmentYear;
	}
	
	@Length(min=0, max=255, message="学年制长度必须介于 0 和 255 之间")
	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	@Length(min=0, max=255, message="学历长度必须介于 0 和 255 之间")
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	
	@Length(min=0, max=255, message="身份证正面照长度必须介于 0 和 255 之间")
	public String getFrontCardImg() {
		return frontCardImg;
	}

	public void setFrontCardImg(String frontCardImg) {
		this.frontCardImg = frontCardImg;
	}
	
	@Length(min=0, max=255, message="身份证反面照长度必须介于 0 和 255 之间")
	public String getVersoCardImg() {
		return versoCardImg;
	}

	public void setVersoCardImg(String versoCardImg) {
		this.versoCardImg = versoCardImg;
	}
	
	@Length(min=0, max=255, message="学生证照片长度必须介于 0 和 255 之间")
	public String getStudentImg() {
		return studentImg;
	}

	public void setStudentImg(String studentImg) {
		this.studentImg = studentImg;
	}
	
	@Length(min=0, max=1, message="认证状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=1, message="认证类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

	
}