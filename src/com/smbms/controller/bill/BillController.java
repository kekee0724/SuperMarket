package com.smbms.controller.bill;

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

import com.smbms.pojo.Bill;
import com.smbms.pojo.Provider;
import com.smbms.service.bill.BillService;
import com.smbms.service.provider.ProviderService;

@Controller
public class BillController {
	@Resource
	BillService bs;
	@Resource
	ProviderService ps;
	@RequestMapping("/sys/bill")
	/*
	 * 全部订单界面
	 */
	/************************************************中文转码*********************************************************************/
	public String bill(Model model,HttpServletRequest request) throws UnsupportedEncodingException{
		String productName=request.getParameter("productName");
		String pid=request.getParameter("providerId");
		String pisPayment=request.getParameter("isPayment");
		Integer providerId=null;
		Integer isPayment=null;
		if(productName!=null){
			
			//productName=new String(productName.getBytes("iso-8859-1"), "utf-8");
			request.setAttribute("productName",productName);
		}
		if(pid!=null&&pid!=""){
			providerId=Integer.parseInt(pid);
			if(providerId==0){
				providerId=null;
			}
			request.setAttribute("providerId", providerId);
		}
		if(pisPayment!=null&&pisPayment!=""){
			isPayment=Integer.parseInt(pisPayment);
			if(isPayment==0){
				isPayment=null;
			}
			request.setAttribute("isPayment", isPayment);
		}
		List<Bill> list=bs.findByNameIdAndIsPayment(productName, providerId, isPayment);
		List<Provider> providerList=ps.findAllProvider();
		model.addAttribute("billList", list);
		model.addAttribute("providerList", providerList);
		return "bill/billlist";
	}
	/*
	 * 查看订单的详细信息
	 */
	@RequestMapping("/sys/billview")
	public String proview(HttpSession session,@RequestParam String billid){
		int id=Integer.parseInt(billid);
		Bill b=bs.findBillById(id);
		session.setAttribute("bill", b);
		return "bill/billview";
	}
	/*
	 * 获取供应商列表
	 */
	@RequestMapping("/sys/billselect")
	@ResponseBody
	public List<Provider> billSelect(){
		List<Provider> list=ps.findAllProvider();
		return list;
	}
	/*
	 * 订单修改界面
	 */
	@RequestMapping("/sys/billmodify")
	public String billmodify(HttpSession session,@RequestParam String billid){
		int id=Integer.parseInt(billid);
		Bill bill=bs.findBillById(id);
		session.setAttribute("bill", bill);
		return "bill/billmodify";
	}
	/*
	 * 保存订单修改信息
	 */
	@RequestMapping("/sys/dobillmodify")
	public String dobillmodify(HttpServletRequest request){
		int id=Integer.parseInt(request.getParameter("id"));
		String productName=request.getParameter("productName");
		String productUnit=request.getParameter("productUnit");
		int productCount=Integer.parseInt(request.getParameter("productCount"));
		double totalPrice=Double.valueOf(request.getParameter("totalPrice"));
		int providerId=Integer.valueOf(request.getParameter("providerId"));
		int isPayment=Integer.parseInt(request.getParameter("isPayment"));
		Bill bill=new Bill();
		bill.setProductName(productName);
		bill.setProductUnit(productUnit);
		bill.setProductCount(productCount);
		bill.setTotalPrice(totalPrice);
		bill.setProviderId(providerId);
		bill.setIsPayment(isPayment);
		bill.setId(id);
		if(bs.billModify(bill)){
			return "redirect:/sys/bill";
		}
		return "redirect:/sys/billmodify";
	}
	/*
	 * 删除订单
	 */
	@RequestMapping("/sys/billdel")
	@ResponseBody
	public HashMap<String, String> billdel(HttpServletRequest request){
		int id=Integer.valueOf(request.getParameter("billid"));
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("delResult", bs.billDel(id));
		return map;
	}
	/*
	 * 添加订单界面
	 */
	@RequestMapping("/sys/billadd")
	public String billAdd(){
		return "bill/billadd";
	}
	/*
	 * 保存添加订单信息
	 */
	@RequestMapping("/sys/dobilladd")
	public String dobilladd(HttpServletRequest request){
		String billCode=request.getParameter("billCode");
		String productName=request.getParameter("productName");
		String productUnit=request.getParameter("productUnit");
		int productCount=Integer.parseInt(request.getParameter("productCount"));
		double totalPrice=Double.valueOf(request.getParameter("totalPrice"));
		int providerId=Integer.valueOf(request.getParameter("providerId"));
		int isPayment=Integer.parseInt(request.getParameter("isPayment"));
		Bill bill=new Bill();
		bill.setBillCode(billCode);
		bill.setProductName(productName);
		bill.setProductUnit(productUnit);
		bill.setProductCount(productCount);
		bill.setCreationDate(new Date());
		bill.setTotalPrice(totalPrice);
		bill.setProviderId(providerId);
		bill.setIsPayment(isPayment);
		if(bs.billAdd(bill)){
			return "redirect:/sys/bill";
		}else{
			return "redirect:/sys/billadd";
		}
	}
}
