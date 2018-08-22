package com.michaelcgood.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.michaelcgood.model.User;
import com.michaelcgood.service.UserService;

@Controller
@RequestMapping("/")
public class HelloSessionController {

	@Autowired
    private UserService userService;
//    @RequestMapping(value="/loginProcess", method = RequestMethod.POST)
//    String login(HttpSession session, User user) {
//        
//		//User loginUser = loginBO.findByUserIdAndPassword(user.getUserId(), user.getPassword());
//    	  System.out.println("@@@@@@@@@@@@@@");
//		//if (loginUser != null) {
//			session.setAttribute("userLoginInfo", user);
//		//}
//			  System.out.println("@@@@@@@@@@@@@@");
//        return "loginForm";
//    }
	// 세션사용 화면
	@RequestMapping("page1")
	public String page1() {
		return "page1";
	}
	// 세션 사용 안하는 화면
	@RequestMapping("page2")
	public String page2() {
		return "page2";
	}
	// 로그인 화면
	@RequestMapping("login")
	public String login() {
		System.out.println("uuuuuuuuuuuuuuuuuuu");
		return "login";
	}
	// 로그아웃
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.setAttribute("userLoginInfo", null);
		return "redirect:login";
	}
	// 로그인 처리 
	@RequestMapping(value="loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(User user, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:login");
		
		User loginUser = userService.findById(user.getId());
		
		if (loginUser != null) {
			session.setAttribute("userLoginInfo", user);
		}
		return mav;
	}
}