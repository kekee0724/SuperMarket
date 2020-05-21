package com.smbms.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smbms.pojo.Role;
import com.smbms.pojo.User;

public interface UserMapper {
	User login(String name);//登录
	List<User> findAllUser();//查询所有用户，未使用
	User findUserById(int id);//查询用户详细信息
	List<Role> findAllRole();//获取角色列表
	int userAdd(User u);//添加用户
	int userModify(User u);//修改用户信息
	User findCode(String userCode);//查询用户编码是否存在
	int userDel(int id);//删除用户信息
	//int useErr(int id);//是否给予访问权限
	//获取用户列表
	List<User> findByNameAndRole(@Param("userName")String userName,@Param("userRole")Integer userRole);
	
	int pwdModify(@Param("id")int id,@Param("newpassword")String newpassword);
	int findUserCount(@Param("userName")String userName,@Param("userRole")Integer userRole);
	List<User> findUserByCount(@Param("userName")String userName,@Param("userRole")Integer userRole,
			@Param("currentPage")int cirrentPage,@Param("size")int size);
}
