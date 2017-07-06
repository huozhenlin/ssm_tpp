package com.cloudsoft.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cloudsoft.dao.IUserDao;
import com.cloudsoft.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cloudsoft.dto.OrderDetail;
import com.cloudsoft.entity.Movie;
import com.cloudsoft.entity.Schedule;
import com.cloudsoft.entity.User;
import com.cloudsoft.entity.UserOrder;

@RestController
@RequestMapping("/admin")
public class AdminController {

	//自动注入业务层userservice类
	@Autowired
	UserServices userService;
	private static ApplicationContext ac;

	static {
		ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");
	}
	@Autowired
	UserService userServiceImpl;

	@Autowired
	MovieService movieServiceImpl;
	
	@Autowired
	UserOrderService orderServiceImpl;
	
	@Autowired
	ScheduleService scheduleServiceImpl;

	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index");
	}

	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login2");
	}

	/**
	 * 后台用户登陆
	 * 
	 * @param httpSession
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpSession httpSession,
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		System.out.println("admin获取到post的用户名是"+username);
		System.out.println("admin获取到post的密码是"+password);

		if (username == null || password == null) {
			return new ModelAndView("redirect:/admin/login");
		}

		User user = userServiceImpl.findByNameAndPwd(username, password);
		if (user != null) {
			System.out.println("获取到了用户");
			httpSession.setAttribute("loginUser", user);
			return new ModelAndView("redirect:/admin/");
		}

		return new ModelAndView("redirect:/admin/login");
	}

	/**
	 * 后台用户注销
	 * 
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/loginOut")
	public ModelAndView loginOut(HttpSession httpSession) {
		// 清空所有session值
		httpSession.invalidate();
		return new ModelAndView("redirect:/admin/login");
	}

	//注册用户
	@RequestMapping("/addUser")
	public ModelAndView addUser(User user, HttpSession session, HttpServletRequest request,
								@RequestParam(value = "file", required = false) MultipartFile file) {
		//忽略大小写
		if (!(user.getCode().equalsIgnoreCase(session.getAttribute("code").toString()))) {

			System.out.println(user.getCode());
			return new ModelAndView("redirect:/admin/errror");
		} else {
			try {
				System.out.println("-----------");
				System.out.println(user.getPassword() + " " + user.getUsername() + " " + user.getEmail());
				System.out.println("---------**");
				//获得物理路径webapp所在路径
				String pathRoot = request.getSession().getServletContext().getRealPath("");
				String path = "";
				if (!file.isEmpty()) {
					//生成uuid作为文件名称
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					//获得文件类型（可以判断如果不是图片，禁止上传）
					String contentType = file.getContentType();
					//获得文件后缀名称
					String imageName = contentType.substring(contentType.indexOf("/") + 1);
					path = uuid + "." + imageName;
					file.transferTo(new File("D:\\bfp\\" + path));
				}
				System.out.println(path);
				request.setAttribute("imagesPath", path);
				IUserDao mapper = (IUserDao) ac.getBean("IUserDao");

				mapper.addUsers(user.getUsername(), user.getPassword(), user.getEmail(), path);

				return new ModelAndView("redirect:/admin/login");

			} catch (Exception e) {

				return new ModelAndView("redirect:/admin/error");
			}


		}
	}

	//增加用户
	@RequestMapping("/add")
	public ModelAndView add(User user, HttpSession session, HttpServletRequest request,
								@RequestParam(value = "file", required = false) MultipartFile file) {
			try {
				//获得物理路径webapp所在路径
				String pathRoot = request.getSession().getServletContext().getRealPath("");
				String path = "";
				if (!file.isEmpty()) {
					//生成uuid作为文件名称
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					//获得文件类型（可以判断如果不是图片，禁止上传）
					String contentType = file.getContentType();
					//获得文件后缀名称
					String imageName = contentType.substring(contentType.indexOf("/") + 1);
					path = uuid + "." + imageName;
					file.transferTo(new File("D:\\bfp\\" + path));
				}
				request.setAttribute("imagesPath", path);
				IUserDao mapper = (IUserDao) ac.getBean("IUserDao");

				mapper.addUsers(user.getUsername(), user.getPassword(), user.getEmail(), path);

				return new ModelAndView("redirect:/admin/user");

			} catch (Exception e) {

				return new ModelAndView("redirect:/admin/error");
			}


		}


	/**
	 * 后台用户列表
	 * 
	 * @return
	 */
	@RequestMapping("/user")
	public ModelAndView getAllUsers() {

		ModelAndView mv = new ModelAndView("/manager/user");

		List<User> users = userServiceImpl.findAllUsers();
		if (!users.isEmpty()) {
			mv.addObject("userList", users);
		}
		return mv;
	}

	/**
	 * 根据id删除用户信息
	 *
	 * @param id
	 * @return
	 *
	 */
	@RequestMapping("/user/{id}/delete")
	public ModelAndView deleteUser(@PathVariable("id") int id) {

		userServiceImpl.deleteByPrimaryKey(id);
		return new ModelAndView("redirect:/admin/user");
	}

	/**
	 * 添加用户
	 *
	 */
	@RequestMapping("/user/add/")
	public ModelAndView addUser() {
		ModelAndView mv = new ModelAndView("/manager/userAdd");
		return mv;
	}



	@RequestMapping("/user/{id}/update")
	public ModelAndView toUpdate(@PathVariable("id") int id,
								 HttpSession session) {
		ModelAndView mv = new ModelAndView("/manager/updateUser");
		User user = userServiceImpl.findById(id);
		if(user != null){
			session.setAttribute("user",user);
		}
		return mv;
	}
	@RequestMapping("/{id}/update")
	public ModelAndView update(@PathVariable("id") int id ,User user){
		User u = new User();
		u.setId(user.getId());
		u.setPassword(user.getPassword());
		int rs = userServiceImpl.updateUserById(u);

		if(rs > 0){
			return new ModelAndView("redirect:/admin/user");
		}
		return new ModelAndView("");
	}



	/**
	 * 后台电影列表
	 * @return
	 */
	@RequestMapping("/movie")
	public ModelAndView getAllMovies() {
		ModelAndView mv = new ModelAndView("/manager/movie");
		List<Movie> movies = movieServiceImpl.findAllMovies();
		if(!movies.isEmpty()){
			mv.addObject("movieList", movies);
		}
		return mv;
	}
	
	/**
	 * 根据id删除电影信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/movie/{id}/delete")
	public ModelAndView deleteMovie(@PathVariable("id") int id) {

		movieServiceImpl.deleteByPrimaryKey(id);
		return new ModelAndView("redirect:/admin/movie");
	}
	
	/**
	 * 跳转至添加电影页面
	 * @return
	 */
	@RequestMapping("/movie/toAdd")
	public ModelAndView toAddMovie(){
		return new ModelAndView("/manager/movieAdd");
	}
	
	/**
	 * 添加电影信息
	 * @param movie
	 * @return
	 */
	@RequestMapping("/movie/add")
	public ModelAndView addMovie(Movie movie){
		if(movie == null){
			return new ModelAndView("redirect:/admin/movie/toAdd");
		}
		int result = movieServiceImpl.save(movie);
		if(result >= 0){
			return new ModelAndView("redirect:/admin/movie");
		}else{
			return new ModelAndView("redirect:/admin/movie/toAdd");
		}
		
	}
	
	/**
	 * 后台电影排期列表
	 * @return
	 */
	@RequestMapping("/schedule")
	public ModelAndView getAllSchedules() {
		ModelAndView mv = new ModelAndView("/manager/schedule");
		List<Schedule> schedules = scheduleServiceImpl.findAllSchedules();
		if(!schedules.isEmpty()){
			mv.addObject("scheduleList", schedules);
		}
		return mv;
	}
	
	/**
	 * 根据id删除电影排期
	 * @param id
	 * @return
	 */
	@RequestMapping("/schedule/{id}/delete")
	public ModelAndView deleteSchedule(@PathVariable("id") int id) {

		scheduleServiceImpl.deleteByPrimaryKey(id);
		return new ModelAndView("redirect:/admin/schedule");
	}
	
	
	/**
	 * 跳转至添加电影排期页面
	 * @return
	 */
	@RequestMapping("/movie/{mid}/schedule/toAdd")
	public ModelAndView toAddMovieSchedule(@PathVariable("mid")int mid){
		
		ModelAndView mv = new ModelAndView("/manager/scheduleAdd");
		
		Movie movie = movieServiceImpl.findById(mid);
		if(movie != null){
			mv.addObject("movie", movie);
		}
		
		return mv;
	}
	
	/**
	 * 添加电影排期
	 * @param schedle
	 * @return
	 */
	@RequestMapping("/schedule/add")
	public ModelAndView addMovie(Schedule schedle){
		if(schedle == null){
			return new ModelAndView("redirect:/admin/movie");
		}
		int result = scheduleServiceImpl.save(schedle);
		if(result >= 0){
			return new ModelAndView("redirect:/admin/schedule");
		}else{
			return new ModelAndView("redirect:/admin/movie/"+schedle.getMovieid()+"/schedule/toAdd");
		}
	}
	
	
	
	
	/**
	 * 后台订单列表
	 * @return
	 */
	@RequestMapping("/order")
	public ModelAndView getAllOrders(){
		
		ModelAndView mv = new ModelAndView("/manager/order");
		
		List<UserOrder> orders = orderServiceImpl.findAllOrders();
		if(!orders.isEmpty()){
			
			List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
			for (UserOrder order : orders) {
				//获取跟该订单相关的对象实例
				User user = userServiceImpl.findById(order.getUserid());
				Movie movie = movieServiceImpl.findById(order.getMovieid());
				Schedule schedule = scheduleServiceImpl.findById(order.getScheduleid());
				OrderDetail orderDetail = new OrderDetail();
				//通过相关对象实例设置订单详细信息
				orderDetail.setOrderid(order.getId());
				orderDetail.setUsername(user.getUsername());
				orderDetail.setMoviename(movie.getName());
				orderDetail.setStarttime(schedule.getStarttime());
				orderDetail.setHallname(schedule.getHallname());
				orderDetail.setSeat(order.getSeat());
				orderDetail.setPrice(order.getPrice());
				orderDetails.add(orderDetail);
			}
			
			mv.addObject("orderList", orderDetails);
		}
		
		return mv;
	}
	
	
	/**
	 * 根据id删除订单信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/order/{id}/delete")
	public ModelAndView deleteOrder(@PathVariable("id") int id) {

		orderServiceImpl.deleteByPrimaryKey(id);
		return new ModelAndView("redirect:/admin/order");
	}
	
	

}
