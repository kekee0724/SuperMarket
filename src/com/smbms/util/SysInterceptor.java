package com.smbms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SysInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		Object obj=session.getAttribute("userOnLogin");
		if(obj==null){
			String path=request.getContextPath();
			session.setAttribute("error", "未登陆，请先登陆");
			response.sendRedirect(path+"/login");
			return false;
		}
		return true;
	}
}
