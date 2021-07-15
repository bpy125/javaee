package com.example.groupconsumer.Controller;

import com.example.groupconsumer.pojo.GroupActivity;
import com.example.groupconsumer.pojo.Tourist;
import com.example.groupconsumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@Controller
public class TouristController {

	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;

	@Autowired
	DiscoveryClient discoveryClient;

	@GetMapping("/")
	@ResponseBody
	public String test(){

		String url = String.format("http://%s","group");
		return restTemplate.getForObject(url, String.class);
	}
	@PostMapping("/Add_AAPay")
	@ResponseBody
	public String AddAAPay(String id1){
		String url = String.format("http://%s/Add_AAPay","group");
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("id",id1);

		return restTemplate.postForObject(url,params, String.class);
	}
//	@GetMapping("/Add_Pay")
//	@ResponseBody
//	public String AddPay(){
//		String url = String.format("http://%s/Add_Pay","group");
//		return restTemplate.getForObject(url, String.class);
//	}
	@PostMapping("/Add_Tourist")
	@ResponseBody
	public String AddTourist(String id){
		String url = String.format("http://%s/Add_Tourist","group");
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("id",id);
		return restTemplate.postForObject(url,params, String.class);
	}
	@GetMapping("/Add_Activity")
	@ResponseBody
	public String AddActivity(){
		String url = String.format("http://%s/Add_Activity","group");
		return restTemplate.getForObject(url, String.class);
	}
	@GetMapping("/Search_Activity")
	@ResponseBody
	public String SearchActivity(){
		String url = String.format("http://%s/Search_Activity","group");
		return restTemplate.getForObject(url, String.class);
	}
	@GetMapping("/exit")
	@ResponseBody
	public String test1(){
		String url = String.format("http://%s/exit","group");
		return restTemplate.getForObject(url, String.class);
	}
	@GetMapping("/logind")
	@ResponseBody
	public String logind(){
		String url = String.format("http://%s/logind","group");
		return restTemplate.getForObject(url, String.class);
	}
	@GetMapping("/registryd")
	@ResponseBody
	public String registryd(){
		String url = String.format("http://%s/registryd","group");
		return restTemplate.getForObject(url, String.class);
	}
	@PostMapping("/findActivityById")
	@ResponseBody
	public String findActivityById(Integer id){
		String url = String.format("http://%s/findActivityById","group");
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("id",id);
		return restTemplate.postForObject(url,params, String.class);
	}
	@GetMapping("/findAllActivity")
	@ResponseBody
	public String findAllActivity(){
		String url = String.format("http://%s/findAllActivity","group");
		return restTemplate.getForObject(url, String.class);
	}
	@PostMapping("/registry")
	@ResponseBody
	public String registry(User user) {
		String url = String.format("http://%s/registry","group");
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("name",user.getName());
		params.add("password",user.getPassword());
		params.add("age",user.getAge());
		params.add("sex",user.getSex());
		params.add("phoneNumber",user.getPhoneNumber());
		return restTemplate.postForObject(url,params, String.class);
	}

	@PostMapping("/login")
	@ResponseBody
	public String login(User user) {
		String url = String.format("http://%s/login","group");
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("name",user.getName());
		params.add("password",user.getPassword());
		return restTemplate.postForObject(url,params, String.class);
	}

	@PostMapping("/insertActivity")
	@ResponseBody
	public String insertActivity(GroupActivity activity){
		String url = String.format("http://%s/insertActivity","group");
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("name",activity.getName());
		params.add("basicPay",activity.getBasicPay());
		params.add("address",activity.getAddress());
		params.add("content",activity.getContent());
		params.add("dated",activity.getDate());
		params.add("days",activity.getDays());
		params.add("due",activity.getDue());
		params.add("leader",activity.getLeader());

		return restTemplate.postForObject(url,params, String.class);
	}
//	@PostMapping("/insertTourist")
//	@ResponseBody
//	public String insertTourist(Tourist tourist,String days){
//		String url = String.format("http://%s/insertTourist","group");
//		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
//		params.add("activity_id",tourist.getActivity_id());
//		params.add("name",tourist.getName());
//		params.add("age",tourist.getAge());
//		params.add("sex",tourist.getSex());
//		params.add("phoneNumber",tourist.getPhoneNumber());
//		params.add("days",days);
//
//		return restTemplate.postForObject(url,params, String.class);
//	}
//	@PostMapping("/AddPay")
//	@ResponseBody
//	public String AddPay1(Tourist tourist){
//		String url = String.format("http://%s/AddPay","group");
//		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
//		params.add("id",tourist.getId());
//		params.add("activity_id",tourist.getActivity_id());
//		params.add("pay",tourist.getPay());
//		return restTemplate.postForObject(url, params,String.class);
//	}
	@PostMapping("/AddAAPay")
	@ResponseBody
	public String AddAAPay1(String id,String pay){
		String url = String.format("http://%s/AddAAPay","group");
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("id",id);
		params.add("pay",pay);
		return restTemplate.postForObject(url,params,String.class);
	}
//	@GetMapping("/ExportExcel")
//	@ResponseBody
//	public String ExportExcel(){
//		String url = String.format("http://%s/ExportExcel","group");
//		return restTemplate.getForObject(url,String.class);
//	}
	@GetMapping("/ExportExcel")
	@ResponseBody
	public void ExportExcel(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		String url = String.format("http://%s/ExportExcel","group");
		response.setContentType("application/vnd-ms.excel");
		//设置页面不缓存
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		//在线浏览的方式：
		response.setHeader("Content-disposition","inline; filename=tour.xls");
		//下载的方式：
		//response.setHeader("Content-disposition","attachment; filename=test2.xls");
		//response.setCharacterEncoding("UTF-8");
		//4.2取得PrintWriter对象
		PrintWriter out = response.getWriter();
		out.println(restTemplate.getForObject(url,String.class));


		//4.4清除缓冲
		out.flush();
		//4.5关闭PrintWriter输出对象
		out.close();
	}



	@PostMapping("/ActivityReport")
	@ResponseBody
	public void activityReport(String id,HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		String url = String.format("http://%s/ActivityReport","group");
		response.setContentType("application/vnd-ms.excel");
		//设置页面不缓存
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		//在线浏览的方式：
		response.setHeader("Content-disposition","inline; filename=activityreport.xls");
		//下载的方式：
		//response.setHeader("Content-disposition","attachment; filename=test2.xls");
		//response.setCharacterEncoding("UTF-8");
		//4.2取得PrintWriter对象
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("id",id);

		PrintWriter out = response.getWriter();
		out.println(restTemplate.postForObject(url,params,String.class));

		//4.4清除缓冲
		out.flush();
		//4.5关闭PrintWriter输出对象
		out.close();
	}
	@PostMapping("/joinActivity")
	@ResponseBody
	public String joinActivity(String name,String age,String sex, String phoneNumber,String activity_id) {
		String url = String.format("http://%s/joinActivity","group");
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("name",name);
		params.add("age",age);
		params.add("sex",sex);
		params.add("phoneNumber",phoneNumber);

		params.add("activity_id",activity_id);
		return restTemplate.postForObject(url,params,String.class);
	}
	@GetMapping("/find_ActivityM")
	@ResponseBody
	public String find_ActivityM() {
		String url = String.format("http://%s/find_ActivityM","group");
		return restTemplate.getForObject(url,String.class);
	}
	@GetMapping("/find_activityJ")
	@ResponseBody
	public String find_ActivityJ() {
		String url = String.format("http://%s/find_activityJ","group");
		return restTemplate.getForObject(url,String.class);
	}
	@PostMapping("/Add_Pay")
	@ResponseBody
	public String Add_Pay(String id2,String id3) {
		String url = String.format("http://%s/Add_Pay","group");

		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("id2",id2);
		params.add("id3",id3);
		return restTemplate.postForObject(url,params, String.class);
	}
	@PostMapping("/AddPay")
	@ResponseBody
	public String AddPay(Tourist tourist) {
		String url = String.format("http://%s/AddPay","group");

		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("pay",tourist.getPay());
		params.add("id",tourist.getId());
		params.add("activity_id",tourist.getActivity_id());
		return restTemplate.postForObject(url,params, String.class);
	}
}
