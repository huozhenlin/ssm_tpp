package com.cloudsoft.controller;

import java.util.List;
import java.util.Random;

import com.cloudsoft.dao.IUserDao;
import com.cloudsoft.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import com.cloudsoft.dto.MsgResult;
import com.cloudsoft.entity.Movie;
import com.cloudsoft.entity.Schedule;
import com.cloudsoft.entity.User;
import com.cloudsoft.entity.UserOrder;
import sun.nio.cs.US_ASCII;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api")
public class APIController {

	//自动注入业务层userservice类
	@Autowired
	UserServices userService;
	private static ApplicationContext ac;
	static {
		ac=new ClassPathXmlApplicationContext("spring-mybatis.xml");
	}
	@Autowired
	private UserService userServiceImpl;
	
	@Autowired
	private MovieService movieServiceImpl;
	
	@Autowired
	private ScheduleService scheduleServiceImpl;
	
	@Autowired
	UserOrderService userOrderServiceImpl;
	
	/**
	 * 注册
	 * 接口url：/api/user/register
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/user/register")
	public MsgResult register(@RequestParam("username")String username,
							  @RequestParam("password")String password){
		
		User regUser = new User();
		regUser.setUsername(username);
		regUser.setPassword(password);
		int result = userServiceImpl.save(regUser);
		if(result > 0){
			//成功
			
			MsgResult mr = new MsgResult();
			mr.setSuccess(true);
			mr.setMsg("注册成功");
			return mr;
		}else{
			//失败
			MsgResult mr = new MsgResult();
			mr.setSuccess(false);
			mr.setMsg("注册失败");
			return mr;
		}
	}
	
	/**
	 * 登陆
	 * 接口url:/api/user/login
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/user/login")
	public MsgResult login(@RequestParam("username")String username,
			  		       @RequestParam("password")String password){
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		User resultUser = userServiceImpl.findByUsernameAndPassword(user);
		if(resultUser != null){
			MsgResult mr = new MsgResult();
			resultUser.setPassword("");
			mr.setData(resultUser);
			mr.setSuccess(true);
			mr.setMsg("登陆成功");
			return mr;
		}else{
			MsgResult mr = new MsgResult();
			mr.setSuccess(false);
			mr.setMsg("用户名或密码错误");
			return mr;
		}
	}
	
	/**
	 * 获取电影列表
	 * 接口：/api/movie/list
	 * @return
	 */
	@RequestMapping("/movie/list")
	public MsgResult getAllMovies() {
		List<Movie> movies = movieServiceImpl.findAllMovies();
		if(!movies.isEmpty()){
			MsgResult mr = new MsgResult();
			mr.setSuccess(true);
			mr.setData(movies);
			return mr;
		}else{
			MsgResult mr = new MsgResult();
			mr.setSuccess(false);
			mr.setMsg("暂无电影信息");
			return mr;
		}
	}
	
	
	/**
	 * 根据电影id获取排期列表
	 * 接口url:/api/movie/{id}/schedule/list
	 * @param id
	 * @return
	 */
	@RequestMapping("/movie/{id}/schedule/list")
	public MsgResult getMovieSchedule(@PathVariable("id")int id) {
		List<Schedule> schedules = scheduleServiceImpl.findByMovieId(id);
		if(!schedules.isEmpty()){
			MsgResult mr = new MsgResult();
			mr.setSuccess(true);
			mr.setData(schedules);
			return mr;
		}else{
			MsgResult mr = new MsgResult();
			mr.setSuccess(false);
			mr.setMsg("暂无电影排期信息");
			return mr;
		}
	}
	
	/**
	 * 根据用户id获取订单列表
	 * 接口url:/api/user/{id}/schedule/list
	 * @param id
	 * @return
	 */
	@RequestMapping("/user/{id}/schedule/list")
	public MsgResult getUserOrders(@PathVariable("id")int id) {
		
		List<UserOrder> orders = userOrderServiceImpl.findByUserId(id);
		if(!orders.isEmpty()){
			MsgResult mr = new MsgResult();
			mr.setSuccess(true);
			mr.setData(orders);
			return mr;
		}else{
			MsgResult mr = new MsgResult();
			mr.setSuccess(false);
			mr.setMsg("暂无用户订单信息");
			return mr;
		}
	}
	
	
	/**
	 * 购买电影票
	 * 接口:/api/user/{uid}/movie/{mid}/schedule/{sid}/buy
	 * @param uid
	 * @param mid
	 * @return
	 */
	@RequestMapping("/user/{uid}/movie/{mid}/schedule/{sid}/buy")
	public MsgResult buyTicket(@PathVariable("uid")int uid,
							   @PathVariable("mid")int mid,
							   @PathVariable("sid")int sid){
		
		User user = userServiceImpl.findById(uid);
		if(user == null){
			MsgResult mr = new MsgResult();
			mr.setSuccess(false);
			mr.setMsg("无效的用户id");
			return mr;
		}
		
		Movie movie = movieServiceImpl.findById(mid);
		if(movie == null){
			MsgResult mr = new MsgResult();
			mr.setSuccess(false);
			mr.setMsg("无效的电影id");
			return mr;
		}
		
		Schedule schedule = scheduleServiceImpl.findById(sid);
		if(schedule == null){
			MsgResult mr = new MsgResult();
			mr.setSuccess(false);
			mr.setMsg("无效的电影排期id");
			return mr;
		}
		
		//随机生成座位信息
		Random rd = new Random();
		int row = rd.nextInt(20)+1;
		int col = rd.nextInt(20)+1;
		String seat = row+"排"+col+"座";
		
		//生成订单信息
		UserOrder order = new UserOrder();
		order.setUserid(uid);
		order.setMovieid(mid);
		order.setScheduleid(sid);
		order.setSeat(seat);
		order.setPrice(movie.getPrice());
		
		int result = userOrderServiceImpl.save(order);
		if(result > 0){
			MsgResult mr = new MsgResult();
			mr.setSuccess(true);
			mr.setData(order);
			return mr;
		}else{
			MsgResult mr = new MsgResult();
			mr.setSuccess(false);
			mr.setMsg("购买失败，订单插入数据库失败");
			return mr;
		}
	}

	/**
	 *
	 * @param request 判断用户名是否被注册
	 * @return json信息
	 */
	@RequestMapping(value = "/exist", method = RequestMethod.GET)
	@ResponseBody
	public String signin(HttpServletRequest request){
		IUserDao mapper= (IUserDao) ac.getBean("IUserDao");
		String username=request.getParameter("username");
		System.out.println(username);
		boolean loginType =userService.isExist(username);

		String message = "";
		if(loginType) {

			User user=mapper.selectByName(username);
			String pic_url=user.getPic();
			message = "code:true:"+pic_url;


		}else{
			message="code:false";

		}
		return message;
	}


	/**
	 * 检查验证码是否正确
	 * @return
	 */
	@RequestMapping(value = "/checkCode", method = RequestMethod.GET)
	@ResponseBody
	public String checkUserOrCode(HttpSession session, HttpServletRequest request){
		String message="";//空信息
		String code=request.getParameter("code");
		if(code.equalsIgnoreCase(session.getAttribute("code").toString())){

			message="access";
		}else {
			message="fail";
		}
		return message;
	}

	/**
	 * 检查用户名合密码是否正确
	 */
	@RequestMapping(value = "/checkUserAndPwd", method = RequestMethod.GET)
	@ResponseBody
	public String checkUserAndPwd(HttpServletRequest request){
		String message="";//空信息
		String username=request.getParameter("username");
		String password=request.getParameter("pwd");
		if(userService.login(username,password)){
			message="access";
		}else {
			message="fail";
		}
		return message;
	}


	@RequestMapping(value = "/find", method = RequestMethod.GET)
	@ResponseBody
	public String sendMail(HttpServletRequest request){
		String password=request.getParameter("email");
		System.out.println(password);
		boolean ok =userService.findUser(password);
		String message = "";
		if(ok){
			message="status:ok";
		}else {
			message="staus:fail";
		}
		return message;
	}

	@RequestMapping(value = "checkmail",method = RequestMethod.GET)
	@ResponseBody
	public String checkMail(HttpServletRequest request){
		String mail=request.getParameter("email");
		System.out.println("执行检查邮箱是否被注册"+mail);
		boolean ok=userService.checkMail(mail);
		String message="";
		if(ok){
			message="status:ok";

		}else {
			message="status:fail";
		}
		return message;
	}

	//检查权限
	@RequestMapping("/role/check")
	@ResponseBody
	public int checkRole(HttpSession session){
		User user=new User();
		user= (User) session.getAttribute("loginUser");
		String username=user.getUsername();
		user= userService.checkRole(username);
		System.out.println("执行检查权限操作"+user.getRole());
		return user.getRole();
	}
	
	
	
}
