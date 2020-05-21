package com.smbms.controller.main;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smbms.pojo.User;
import com.smbms.service.user.UserService;
@Controller
public class MainController {
		@Resource
		UserService us;
		/*
		 * 登录界面
		 */
		@RequestMapping("/login")
		public String login(HttpSession session){
			session.removeAttribute("userOnLogin");
			return "login";
		}
		/*
		 * 查询登录账号密码是否正确
		 */
		@RequestMapping(value="/dologin" ,method=RequestMethod.POST)
		public String dologin(HttpSession session,@RequestParam String userCode,@RequestParam String password){
			User u=us.login(userCode, password);
			if(u!=null){
				session.setAttribute("userOnLogin", u);
				session.removeAttribute("error");
				return "redirect:/sys/main";
			}else{
				session.setAttribute("error", "用户名或密码错误");
				session.removeAttribute("successSavePwd");
				return "redirect:/login";
			}
		}
		/*
		 * 主界面
		 */
		@RequestMapping("/sys/main")
		public String main(){
			return "frame";
		}
		/*
		 * 退出系统
		 */
		@RequestMapping("/sys/logout")
		public String logout(HttpSession session){
			session.removeAttribute("userOnLogin");
			session.removeAttribute("successSavePwd");
			return "redirect:/login";
		}
		/*
		 * 401界面
		 */
//		@RequestMapping("/*")
//		public String notFound(){
//			return "401";
//		}
}
