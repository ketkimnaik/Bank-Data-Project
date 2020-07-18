package com.springboot.bank.bankDataProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.bank.bankDataProject.Service.UserService;
import com.springboot.bank.bankDataProject.form.RegisterUserform;

@Controller
public class LoginController {
	
	@GetMapping("/")
	public ModelAndView root() {
		return new ModelAndView("welcome");
	}
	
	@GetMapping("/showMyLoginPage")
	public ModelAndView showForm() {
		
		ModelAndView modelAndView = new ModelAndView("login_form");
		
		return modelAndView;
	}
	
	
	
	
}
