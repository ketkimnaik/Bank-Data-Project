package com.springboot.bank.bankDataProject.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.bank.bankDataProject.POJO.SwiftDetails;
import com.springboot.bank.bankDataProject.POJO.User;
import com.springboot.bank.bankDataProject.Service.SwiftService;
import com.springboot.bank.bankDataProject.Service.UserService;
import com.springboot.bank.bankDataProject.form.RegisterUserform;
import com.springboot.bank.bankDataProject.form.SwiftForm;

@Controller
public class LoginController {
	
	@Autowired
	private SwiftService swiftService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/")
	public ModelAndView root(Model model) {
		
		model.addAttribute("swiftCode", new SwiftForm());
		return new ModelAndView("welcome");
	}
	
	@GetMapping("/showMyLoginPage")
	public ModelAndView showForm() {
		
		ModelAndView modelAndView = new ModelAndView("login_form");
		
		return modelAndView;
	}
	
	@PostMapping("/processSwiftRequest")
	public ModelAndView processSwiftRequest(
				@Valid @ModelAttribute("swiftCode") SwiftForm form, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		 
		String code = form.getSwiftcode();
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 System.out.println("it has error");
			 return new ModelAndView("welcome");
	     }

		// check the database if user already exists
//        User existing = userService.findByUsername(userName);
        SwiftDetails exist_code = swiftService.findBySwift(code);
        
        if(exist_code != null) {
        	theModel.addAttribute("table_exist", true);
        	theModel.addAttribute("SwiftCodeModel", exist_code);
        	return new ModelAndView("welcome");
        }
        
        return new ModelAndView("welcome");	
	}
	
	
	
}
