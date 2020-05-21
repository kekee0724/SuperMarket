package com.smbms.pojo;

import java.util.Calendar;
import java.util.Date;



public class User {
	private Integer id;
	private String userCode;
	private String userName;
	private String userPassword;
	private int gender;
	private Date birthday;
	private String phone;
	private Role role;
	private String address;
	private int createdBy;
	private Date creationDate;
	private int age;
	private int userRole;
	
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
	public int getAge() {
		Date birthday=this.getBirthday();
		Calendar c1=Calendar.getInstance();
		c1.setTime(new Date());
		Calendar c2=Calendar.getInstance();
		c2.setTime(birthday);
		int age=c1.get(Calendar.YEAR)-c2.get(Calendar.YEAR);
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userCode=" + userCode + ", userName="
				+ userName + ", userPassword=" + userPassword + ", gender="
				+ gender + ", birthday=" + birthday + ", phone=" + phone
				+ ", role=" + role + ", address=" + address + ", createdBy="
				+ createdBy + ", creationDate=" + creationDate + ", age=" + age
				+ "]";
	}
	
}
