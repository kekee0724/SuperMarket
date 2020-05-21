package com.smbms.controller.provider;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smbms.pojo.Provider;
import com.smbms.service.provider.ProviderService;

@Controller
public class ProviderController {
	@Resource
	ProviderService ps;
	/*
	 * 全部供应商界面
	 */
	/************************************************中文转码*********************************************************************/
	@RequestMapping("/sys/provider")
	public String provider(Model model,HttpServletRequest request) throws UnsupportedEncodingException{
		String proCode=request.getParameter("queryProCode");
		String proName=request.getParameter("queryProName");
		if(proCode!=null){
			//proCode=new String(proCode.getBytes("iso-8859-1"), "utf-8");
		}
		if(proName!=null){
			//proName=new String(proName.getBytes("iso-8859-1"), "utf-8");
		}
		List<Provider> proList=ps.findByCodeOrName(proCode, proName);
		model.addAttribute("proList",proList);
		model.addAttribute("queryProCode", proCode);
		model.addAttribute("queryProName", proName);
		return "pro/providerlist";
	}
	/*
	 * 供应商详细信息
	 */
	@RequestMapping("/sys/proview")
	public String proview(HttpSession session,@RequestParam String proid){
		int id=Integer.parseInt(proid);
		Provider p=ps.findProvider(id);
		session.setAttribute("provider", p);
		return "pro/providerview";
	}
	/*
	 * 供应商修改界面
	 */
	@RequestMapping("/sys/providermodify")
	public String promodify(HttpSession session,@RequestParam String proid){
		int id=Integer.parseInt(proid);
		Provider p=ps.findProvider(id);
		session.setAttribute("provider", p);
		return "pro/providermodify";
	}
	/*
	 * 保存供应商修改信息
	 */
	@RequestMapping("/sys/providermodifysave")
	public String providermodifysave(HttpServletRequest request){
		int id=Integer.valueOf(request.getParameter("proid"));
		String proCode=request.getParameter("proCode");
		String proName=request.getParameter("proName");
		String proContact=request.getParameter("proContact");
		String proPhone=request.getParameter("proPhone");
		String proAddress=request.getParameter("proAddress");
		String proFax=request.getParameter("proFax");
		String proDesc=request.getParameter("proDesc");
		Provider p=new Provider();
		p.setId(id);
		p.setProCode(proCode);
		p.setProName(proName);
		p.setProContact(proContact);
		p.setProPhone(proPhone);
		p.setProAddress(proAddress);
		p.setProFox(proFax);
		p.setProDesc(proDesc);
		if(ps.proModify(p)){
			return "redirect:/sys/provider";
		}
		return "redirect:/sys/providermodify";
	}
	/*
	 * 删除供应商
	 */
	@RequestMapping("/sys/delprovider")
	@ResponseBody
	public HashMap<String, String> delProvider(HttpServletRequest request){
		int id=Integer.valueOf(request.getParameter("proid"));
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("delResult", ps.proDelete(id));
		return map;
	}
	/*
	 * 添加供应商界面
	 */
	@RequestMapping("/sys/provideradd")
	public String addprovider(){
		return "/pro/provideradd";
	}
	/*
	 * 保存供应商修改信息
	 */
	@RequestMapping("/sys/provideraddsave")
	public String providerAdd(HttpServletRequest request){
		String proCode=request.getParameter("proCode");
		String proName=request.getParameter("proName");
		String proContact=request.getParameter("proContact");
		String proPhone=request.getParameter("proPhone");
		String proAddress=request.getParameter("proAddress");
		String proFax=request.getParameter("proFax");
		String proDesc=request.getParameter("proDesc");
		Provider p=new Provider();
		p.setProCode(proCode);
		p.setProName(proName);
		p.setProContact(proContact);
		p.setProPhone(proPhone);
		p.setProAddress(proAddress);
		p.setProFox(proFax);
		p.setProDesc(proDesc);
		p.setCreationDate(new Date());
		if(ps.proAdd(p)){
			return "redirect:/sys/provider";
		}else{
			return "redirect:/sys/provideradd";
		}
	}
}
