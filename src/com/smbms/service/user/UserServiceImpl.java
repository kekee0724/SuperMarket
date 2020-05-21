package com.smbms.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smbms.dao.user.UserMapper;
import com.smbms.pojo.Page;
import com.smbms.pojo.Role;
import com.smbms.pojo.User;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	UserMapper um;
	@Override
	public User login(String name, String pass) {
		// TODO Auto-generated method stub
		User u=um.login(name);
		if(u!=null&&u.getUserPassword().equals(pass)){
			return u;
		}
		return null;
	}
	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return um.findAllUser();
	}
	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return um.findUserById(id);
	}
	@Override
	public List<Role> findAllRole() {
		// TODO Auto-generated method stub
		return um.findAllRole();
	}
	@Override
	public boolean userAdd(User u) {
		// TODO Auto-generated method stub
		if(um.userAdd(u)>0){
			return true;
		}
		return false;
	}
	@Override
	public boolean userModify(User u) {
		// TODO Auto-generated method stub
		if(um.userModify(u)>0){
			return true;
		}
		return false;
	}
	@Override
	public String findCode(String userCode) {
		// TODO Auto-generated method stub
		if(um.findCode(userCode)!=null){
			return "exist";
		}
		return "noexist";
	}
	@Override
	public String userDel(int id) {
		// TODO Auto-generated method stub
		if(um.findUserById(id)!=null){
			if(um.userDel(id)>0){
				return "true";
			}
			return "false";
		}
		return "notexist";
	}
	@Override
	public List<User> findByNameAndRole(String userName,Integer userRole) {
		// TODO Auto-generated method stub
		return um.findByNameAndRole(userName,userRole);
	}
	@Override
	public boolean pwdModify(int id, String newpassword) {
		// TODO Auto-generated method stub
		if(um.pwdModify(id, newpassword)>0){
			return true;
		}
		return false;
	}
	@Override
	public int findUserCount(String userName,Integer userRole) {
		// TODO Auto-generated method stub
		return um.findUserCount(userName, userRole);
	}
	@Override
	public List<User> findUserByCount(String userName, Integer userRole,
			Page page) {
		page.setSize(5);
		page.setTotalCount(um.findUserCount(userName,userRole));
		// TODO Auto-generated method stub
		return um.findUserByCount(userName, userRole, (page.getCurrentpage()-1)*page.getSize(), page.getSize());
		
	}

}
