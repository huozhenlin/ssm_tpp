package com.cloudsoft.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cloudsoft.entity.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String url = request.getRequestURL().toString();
		if(url.indexOf("/admin/login")>=0){
			return true;
		}
		if(url.indexOf("/admin/addUser")>=0){
			return true;
		}
		
		// 判断用户是否登陆，未登录就拦截跳转至登陆页面
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		if (user != null) {
			return true;
		} else {
			response.sendRedirect("/admin/login");
		}
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 获取session中保存的登陆的用户对象
		User user = (User) request.getSession().getAttribute("loginUser");
		if (user != null) {
			// 为每个ModelAndView对象都添加已经登陆的用户对象
			modelAndView.getModelMap().addAttribute("USER", user);
		}

		super.postHandle(request, response, handler, modelAndView);
	}

}
