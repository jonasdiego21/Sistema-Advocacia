package br.com.jdrmservices.araujocafe.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class ControllerLogin {
	
	@GetMapping
	public ModelAndView login( @AuthenticationPrincipal User user) {
		
		if (user != null) {
			return new ModelAndView("Dashboard");
		}
		
		return new ModelAndView("Login");
	}
	
}
