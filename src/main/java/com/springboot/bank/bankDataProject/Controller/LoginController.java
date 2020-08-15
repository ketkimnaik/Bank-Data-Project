package com.springboot.bank.bankDataProject.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
import org.springframework.web.bind.annotation.RequestParam;
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
	
	String n = "";
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/")
	public ModelAndView root(Model model) {
		
		if(n.isEmpty()) {
        	model.addAttribute("name_not_exist", true);
        } else {
        	n = "";
        	model.addAttribute("name_not_exist", true);
//        	model.addAttribute("name_exist", true);
//        	model.addAttribute("name", n);
        	
        }
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
//        	return new ModelAndView("welcome");
        }
        
        if(exist_code == null) {
        	theModel.addAttribute("table_not_exist", true);
//        	return new ModelAndView("welcome");
        }
        
        if(n.isEmpty()) {
        	theModel.addAttribute("name_not_exist", true);
        } else {
        	theModel.addAttribute("name_exist", true);
        	theModel.addAttribute("name", n);
        }
        
        
        return new ModelAndView("welcome");	
	}
	
	
	
	//facebook Controller
	
	@Autowired
	OAuth2AuthorizedClientService authclientService;

    @RequestMapping("/oauth2LoginSuccess")
	public ModelAndView getOauth2LoginInfo(Model model,@AuthenticationPrincipal OAuth2AuthenticationToken authenticationToken) {
		// fetching the client details and user details 
		System.out.println(authenticationToken.getAuthorizedClientRegistrationId()); // client name like facebook, google etc.
		System.out.println(authenticationToken.getName()); // facebook/google userId
		
		//		1.Fetching User Info
		OAuth2User user = authenticationToken.getPrincipal(); // When you login with OAuth it gives you OAuth2User else UserDetails
		System.out.println("userId: "+user.getName()); // returns the userId of facebook something like 12312312313212
		// getAttributes map Contains User details like name, email etc// print the whole map for more details
		System.out.println("Email:"+ user.getAttributes().get("email"));
        
		n = user.getAttribute("name");
		System.out.println("name is:" + n);
		//2. Just in case if you want to obtain User's auth token value, refresh token, expiry date etc you can use below snippet
	       OAuth2AuthorizedClient client = authclientService
	         .loadAuthorizedClient(authenticationToken.getAuthorizedClientRegistrationId(), 
				  authenticationToken.getName());
              System.out.println("Token Value"+ client.getAccessToken().getTokenValue()); 
		
	   //3. Now you have full control on users data.You can eitehr see if user is not present in Database then store it and 
	   // send welcome email for the first time 
              
        if(n == null) {
        	model.addAttribute("name_not_exist", true);
        }
        
        if(n != null) {
        	model.addAttribute("name_exist", true);
        	model.addAttribute("name", n);
        }
        
        model.addAttribute("swiftCode", new SwiftForm());
	    return new ModelAndView("welcome");
	}


    
}
