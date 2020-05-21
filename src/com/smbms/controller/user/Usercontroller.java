package com.smbms.controller.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smbms.pojo.Page;
import com.smbms.pojo.Role;
import com.smbms.pojo.User;
import com.smbms.service.user.UserService;

@Controller
public class Usercontroller {
	@Resource
	UserService us;
	/*
	 * 全部用户界面
	 */
	@RequestMapping("/sys/user")
	public String user(Model model,HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException{
		//判断角色权限

		User user=(User)session.getAttribute("userOnLogin");
		if(user.getUserRole()!=1){
			return "redirect:/401.jsp";
		}
		
	    String queryname=request.getParameter("queryname");
		String queryUserRole=request.getParameter("queryUserRole");
		Integer userRole=null;
		if(queryname!=null&&queryname!=""){
			//queryname=new String(queryname.getBytes("iso-8859-1"), "utf-8");
			request.setAttribute("queryUserName", queryname);
		}
		
		if(queryUserRole!=null&&queryUserRole!=""){
			userRole=Integer.parseInt(queryUserRole);
			if(userRole==0){
				userRole=null;
			}
			request.setAttribute("queryUserRole", userRole);
		}	
				
		
		
		Page page=new Page();
		String Page=request.getParameter("currentPage");
		int currentPage=0;
		if(Page!=null){
			currentPage=Integer.parseInt(Page);
		}
		else{
			currentPage=1;
		}
		page.setCurrentpage(currentPage);
		request.setAttribute("currentPage",currentPage);
		List<User> list=us.findUserByCount(queryname, userRole, page);
		request.setAttribute("totalPage",page.getTotalPage());
		request.setAttribute("totalCount", page.getTotalCount());
		List<Role> roleList=us.findAllRole();
		model.addAttribute("roleList", roleList);
		model.addAttribute("userList", list);
		return "/user/userlist";
	}
	
	
	/*
	 * 修改用户密码界面
	 */
	@RequestMapping("/sys/pwdmodify")
	public String pwdmodify(){
		return "/user/pwdmodify";
	}
	/*
	 * 核对旧密码是否正确
	 */
	@RequestMapping("sys/checkpwd")
	@ResponseBody
	public HashMap<String,String> checkpwd(HttpSession session,@RequestParam String oldpassword){
		Object obj=session.getAttribute("userOnLogin");
		HashMap<String, String> map=new HashMap<String, String>();
		if(oldpassword!=""&&oldpassword!=null){
			if(obj!=null){
				User u=(User) obj;
				if(u.getUserPassword().equals(oldpassword)){
					map.put("result", "true");
				}else{
					map.put("result", "false");
				}
			}
			else{
				map.put("result", "sessionerror");
			}
		}else{
			map.put("result", "error");
			}
		return map;
	}
	/*
	 * 保存用户新密码
	 */
	@RequestMapping("/sys/savepwdmodify")
	public String savePwdModify(HttpServletRequest request,HttpSession session){
		User u=(User) session.getAttribute("userOnLogin");
		String newpassword=request.getParameter("newpassword");
		if(us.pwdModify(u.getId(), newpassword)){
			session.setAttribute("successSavePwd", "密码修改成功，请重新登录");
			return "redirect:/login";
		}
		return "redirect:/sys/pwdmodify";
	}
	/*
	 * 用户详细信息界面
	 */
	@RequestMapping("/sys/viewUser")
	public String proview(HttpSession session,@RequestParam String uid){
		int id=Integer.parseInt(uid);
		User u=us.findUserById(id);
		session.setAttribute("user", u);
		return "user/userview";
	}
	/*
	 * 修改用户信息界面
	 */
	@RequestMapping("/sys/modifyUser")
	public String modifyUser(@RequestParam String uid,HttpSession session) throws ParseException{
		int id=Integer.parseInt(uid);
		User u=us.findUserById(id);
		session.setAttribute("user", u);
		return "user/usermodify";
	}
	/*
	 * 保存用户修改信息
	 */
	@RequestMapping("/sys/modifyusersave")
	public String modifyUserSave(HttpServletRequest request) throws ParseException{
		int uid=Integer.parseInt(request.getParameter("uid"));
		String userName=request.getParameter("userName");
		int gender=Integer.parseInt(request.getParameter("gender"));
		SimpleDateFormat sdf =new SimpleDateFormat( "yyyy-MM-dd");
		Date birthday=sdf.parse(request.getParameter("birthday"));
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		int userRole=Integer.parseInt(request.getParameter("userRole"));
		User u=new User();
		u.setId(uid);
		u.setAddress(address);
		u.setUserName(userName);
		u.setBirthday(birthday);
		u.setGender(gender);
		u.setUserRole(userRole);
		u.setPhone(phone);
		if(us.userModify(u)){
			return "redirect:/sys/user";
		}
		return "redirect:/sys/modifyUser";
	}
	/*
	 * 获取用户角色列表
	 */
	@RequestMapping("/sys/getrolelist")
	@ResponseBody
	public List<Role> getRoleList(){
		List<Role> list=us.findAllRole();
		return list;
	}
	/*
	 * 核对用户编码是否重复
	 */
	@RequestMapping("/sys/usercode")
	@ResponseBody
	public Map<String, String> userCode(@RequestParam String userCode){
		Map<String, String> map=new HashMap<String, String>();
		map.put("userCode", us.findCode(userCode));
		return map;
	}
	/*
	 * 添加用户界面
	 */
	@RequestMapping("/sys/useradd")
	public String userAdd(){
		return "user/useradd";
	}
	/*
	 * 用户信息保存
	 */
	@RequestMapping("sys/saveuser")
	public String userSave(HttpServletRequest request) throws ParseException{
		String userName=request.getParameter("userName");
		String userPassword=request.getParameter("userPassword");
		String userCode=request.getParameter("userCode");
		int gender=Integer.parseInt(request.getParameter("gender"));
		SimpleDateFormat sdf =new SimpleDateFormat( "yyyy-MM-dd");
		Date birthday=sdf.parse(request.getParameter("birthday"));
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		int userRole=Integer.parseInt(request.getParameter("userRole"));
		User u=new User();
		u.setUserCode(userCode);
		u.setAddress(address);
		u.setUserName(userName);
		u.setBirthday(birthday);
		u.setGender(gender);
		u.setUserRole(userRole);
		u.setUserPassword(userPassword);
		u.setPhone(phone);
		if(us.userAdd(u)){
			return "redirect:/sys/user";
		}
		return "redirect:/sys/useradd";
	}
	/*
	 * 删除用户
	 */
	@RequestMapping("/sys/deleteUser")
	@ResponseBody
	public Map<String, String> userDelete(@RequestParam String uid){
		int id=Integer.parseInt(uid);
		Map<String,String> map=new HashMap<String, String>();
		map.put("delResult", us.userDel(id));
		return map;
	}
	
}
