package com.example.group.Controller;

import com.example.group.pojo.GroupActivity;
import com.example.group.pojo.Tourist;
import com.example.group.pojo.User;
import com.example.group.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.acl.Group;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class TouristController {
	@Autowired
	private TouristService touristService;

	private User s;

	@Autowired
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 根据id查询客户详情
	 */
	@PostMapping("/findActivityById")
	public String findActivityById(Model model,Integer id) {

		GroupActivity activity = touristService.findActivityWithTourists(id);
		model.addAttribute("list", activity);
		//返回客户信息展示页面
		return "GroupActivity";
	}
	@GetMapping("/findAllActivity")
	public String findAllActivity(Model model) {
		
		List<GroupActivity> l = touristService.findAllActivity();
		model.addAttribute("list", l);
		//返回客户信息展示页面
		return "showAll";
	}
	@PostMapping("/insertActivity")
	public String insertActivity(Model model, GroupActivity activity,HttpServletRequest request,Tourist tourist) throws ParseException {
		String dated = request.getParameter("dated");
		java.util.Date date1 = sdf.parse(dated);
		java.sql.Date date = new java.sql.Date(date1.getTime());
		activity.setDate(date);
		activity.setNumber(1);
		User u = touristService.findUser(s.getName());
		tourist.setName(u.getName());
		tourist.setAge(u.getAge());
		tourist.setSex(u.getSex());
		tourist.setPhoneNumber(u.getPhoneNumber());
		touristService.insertActivity(activity);
		GroupActivity a = touristService.findActivityByName1(activity.getName());
		tourist.setActivity_id(a.getId());
		double pay = a.getBasicPay()+a.getDue();
		tourist.setPay(pay);
		touristService.insertTourist(tourist);

		//返回客户信息展示页面
		List<GroupActivity> l = touristService.findAllActivity();
		model.addAttribute("list", l);
		//返回客户信息展示页面
		return "showAll";
	}

//	@PostMapping("/insertTourist")
//	public String insertTourist(Model model, Tourist tourist, HttpServletRequest request) {
//
//
//		int activity_id = tourist.getActivity_id();
//		double basePay= touristService.findActivityBasicPay(activity_id);
//		double day = Double.parseDouble(days);
//		double newPay = day*300+basePay;
//		tourist.setPay(newPay);
//		touristService.insertTourist(tourist);
//		//返回客户信息展示页面
//	 	GroupActivity l = touristService.findActivityWithTourists(tourist.getActivity_id());
//		model.addAttribute("activity", l);
//		//返回客户信息展示页面
//		return "GroupActivity";
//	}
	@PostMapping("/AddPay")
	public String AddPay(Model model,Tourist tourist) {
		touristService.updateTouristPay(tourist);

		List<GroupActivity> a = touristService.findActivityByLeader(s.getName());
		model.addAttribute("list1", a);
		return "findActivityM";

	}
	@PostMapping("/AddAAPay")
	public ModelAndView AddAAPay(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		String pay = request.getParameter("pay");
		int activity_id=Integer.parseInt(id);
		double pay1=Double.parseDouble(pay);
		touristService.AddAAPay(activity_id,pay1);
		GroupActivity activity = touristService.findActivityWithTourists(activity_id);
		double p = pay1*activity.getNumber();
		touristService.AddAAPay1(activity_id,p);
		//返回客户信息展示页面
		GroupActivity a = touristService.findActivityWithTourists(activity_id);
		model.addAttribute("list1", a);
		//返回客户信息展示页面
		return new ModelAndView("findActivityM");
	}

	@ResponseBody
	@GetMapping("/ExportExcel")
	public void ExportExcel(HttpServletResponse response,HttpServletRequest request) throws IOException {
		//4.输出处理结果，如向客户输出Excel文件，方法如下：
		//4.1设置响应的数据类型，以输出Excel格式的文件
		response.setCharacterEncoding("UTF-8");

		List<Tourist> list = touristService.findTouristByName(s.getName());
		PrintWriter out = response.getWriter();
		out.println("姓名\t参加活动\t合计费用");
		for(Tourist u :list){
			out.print(u.getName());
			out.print("\t");
			GroupActivity a =touristService.findActivityWithTourists(u.getActivity_id());
			out.print(a.getName());
			out.print("\t");
			out.print(u.getPay());

			out.print("\n");
		}

		//4.4清除缓冲
		out.flush();
		//4.5关闭PrintWriter输出对象
		out.close();
	}
	@ResponseBody
	@PostMapping("/ActivityReport")
	public void activityReport(HttpServletResponse response,HttpServletRequest request) throws IOException {
		//4.输出处理结果，如向客户输出Excel文件，方法如下：
		//4.1设置响应的数据类型，以输出Excel格式的文件
		String id = request.getParameter("id");
		int i = Integer.parseInt(id);
		System.out.println(i);
		GroupActivity l = touristService.findActivityWithTourists(i);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("编号\t名称\t基础费用\t地点\t内容\t日期\t天数\t团费\t团长\t参加人员");
		out.print(l.getId());
		out.print("\t");
		out.print(l.getName());
		out.print("\t");
		out.print(l.getBasicPay());
		out.print("\t");
		out.print(l.getAddress());
		out.print("\t");
		out.print(l.getContent());
		out.print("\t");
		out.print(l.getDate());
		out.print("\t");
		out.print(l.getDays());
		out.print("\t");
		out.print(l.getDue());
		out.print("\t");
		out.print(l.getLeader());
		out.print("\t");
		out.println("编号\t姓名\t年龄\t性别\t花费\t手机号");
		out.print("\t\t\t\t\t\t\t\t\t");
		for(Tourist t:l.getList()) {
			out.print(t.getId());
			out.print("\t");
			out.print(t.getName());
			out.print("\t");
			out.print(t.getAge());
			out.print("\t");
			out.print(t.getSex());
			out.print("\t");
			out.print(t.getPay());
			out.print("\t");
			out.print(t.getPhoneNumber());
			out.print("\n");
		}

		//4.4清除缓冲
		out.flush();
		//4.5关闭PrintWriter输出对象
		out.close();
	}
	@PostMapping("/registry")
	public String registry(User user) {
		touristService.addUser(user);
		return "login";
	}

	@PostMapping("/login")
	public String login(User user) {
		String name = user.getName();
		String password = user.getPassword();
		User u = touristService.findUser(name);
		s = new User();
		s = touristService.findUser(name);
		if(u.getName()!=null && u.getPassword().equals(password)) {
			return "newM";
		}
		else {
			return "login";
		}
	}

	@PostMapping("/joinActivity")
	public String joinActivity(HttpServletRequest request, Model model) {

		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		String phoneNumber = request.getParameter("phoneNumber");
		int ag = Integer.parseInt(age);
		double ph = Double.parseDouble(phoneNumber);
		int i = Integer.parseInt(request.getParameter("activity_id"));
		GroupActivity a = touristService.findActivityById(i);
		int du = a.getDue();
		double baPa = a.getBasicPay();
		int nu = a.getNumber();
		double pay = baPa/(nu+1) +du;
		Tourist tourist = new Tourist();
		tourist.setPay(pay);
		tourist.setActivity_id(i);
		tourist.setName(name);
		tourist.setAge(ag);
		tourist.setSex(sex);
		tourist.setPhoneNumber(ph);
		touristService.insertTourist(tourist);
		touristService.updateTouristPay(tourist);

		touristService.updateActivityNumber(i);
		GroupActivity l = touristService.findActivityWithTourists(tourist.getActivity_id());
		System.out.println(l.toString());
		model.addAttribute("list", l);
		//返回客户信息展示页面
		return "GroupActivity";
	}
	@GetMapping("/find_ActivityM")
	public String find_ActivityM(Model model) {

		List<GroupActivity> a = touristService.findActivityByLeader(s.getName());

		if(a!=null) {
			model.addAttribute("list1", a);

			return "findActivityM";
		}
		else {
			return "index";
		}
	}

	@GetMapping("/")
	public ModelAndView test(Model model){
		return new ModelAndView("login");
	}
	@PostMapping("/Add_AAPay")
	public ModelAndView Add_AAPay(HttpServletRequest request,Model model){
		String id = request.getParameter("id");
		int i = Integer.parseInt(id);
		model.addAttribute("id",i);
		return new ModelAndView("AddAAPay");
	}
	@PostMapping("/Add_Pay")
	public String AddPay(HttpServletRequest request,Model model){
		String id2 = request.getParameter("id2");
		String id3 = request.getParameter("id3");
		int id1 = Integer.parseInt(id2);
		int id4 = Integer.parseInt(id3);
		model.addAttribute("id1",id1);
		model.addAttribute("id4",id4);
		return "AddPay";
	}
	@PostMapping("/Add_Tourist")
	public String AddTourist(HttpServletRequest request,Model model){
		int id = Integer.parseInt(request.getParameter("id"));
		model.addAttribute("id",id);
		return "AddTourist";
	}
	@GetMapping("/Add_Activity")
	public String AddActivity(){
		return "AddActivity";
	}
	@GetMapping("/Search_Activity")
	public String SearchActivity(){
		return "SearchActivity";
	}
	@GetMapping("/exit")
	public String test1(){
		return "login";
	}
	@GetMapping("/logind")
	public String logind() {
		return "login";
	}
	@GetMapping("/registryd")
	public String registryd() {
		return "registry";
	}

	@GetMapping("/find_activityJ")
	public String find_activityJ(Model model) {
		List<Tourist> tourist = touristService.findTouristByName(s.getName());
		List<GroupActivity> activity = new ArrayList<>();
		if(tourist!=null) {
			for (Tourist t : tourist) {
				GroupActivity a = touristService.findActivityById(t.getActivity_id());
				activity.add(a);
			}
			model.addAttribute("list", activity);
			return "GroupActivity";
		}
		else {
			return "index";
		}
	}
}
