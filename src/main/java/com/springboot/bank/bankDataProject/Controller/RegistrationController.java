package com.springboot.bank.bankDataProject.Controller;

import java.util.logging.Logger;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.bank.bankDataProject.POJO.User;
import com.springboot.bank.bankDataProject.Service.UserService;
import com.springboot.bank.bankDataProject.form.RegisterUserform;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
    @Autowired
    private UserService userService;
	
    private Logger logger = Logger.getLogger(getClass().getName());
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showRegistrationForm")
	public ModelAndView showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("crmUser", new RegisterUserform());
		ModelAndView modelAndView = new ModelAndView("registration-form");
		return modelAndView;
	}

	@PostMapping("/processRegistrationForm")
	public ModelAndView processRegistrationForm(
				@Valid @ModelAttribute("crmUser") RegisterUserform theCrmUser, 
				BindingResult theBindingResult, 
				Model theModel, RedirectAttributes redirect) {
		
		 
//		String userName = theCrmUser.getUserName();
		String email = theCrmUser.getEmail();
//		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return new ModelAndView("registration-form");
	     }

		// check the database if user already exists
//        User existing = userService.findByUsername(userName);
          User existing_email = userService.findByemail(email);
        
//        if (existing != null){
//        	
//        	theModel.addAttribute("crmUser", new RegisterUserform());
//        	
//        	theModel.addAttribute("registrationError", "User name already exists.");
//        	
//			logger.warning("User name already exists.");
//        	return "registration-form";
//        }
        
        if(existing_email != null) {
        	
        	
        	theModel.addAttribute("crmUser", new RegisterUserform());
        	
        	theModel.addAttribute("registrationError", "Email already exists.");
        	
			logger.warning("Email already exists.");
        	return new ModelAndView("registration-form");
        	
        }
        
        // create user account        						
        userService.save(theCrmUser);
        
        redirect.addFlashAttribute("newUserSaveStatus", true);
        
//        logger.info("Successfully created user: " + userName);
        
        return new ModelAndView("redirect:/showMyLoginPage");	
	}
}
