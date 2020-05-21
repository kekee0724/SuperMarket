package com.smbms.service.user;

import java.util.List;

import com.smbms.pojo.Page;
import com.smbms.pojo.Role;
import com.smbms.pojo.User;

public interface UserService {
	User login(String name,String pass);
	List<User> findAllUser();
	User findUserById(int id);
	List<Role> findAllRole();
	boolean userAdd(User u);
	boolean userModify(User u);
	String userDel(int id);
	String findCode(String userCode);
	List<User> findByNameAndRole(String userName,Integer userRole);
	boolean pwdModify(int id,String newpassword);
	int findUserCount(String userName,Integer userRole);
	List<User> findUserByCount(String userName,Integer userRole,Page page);
}
