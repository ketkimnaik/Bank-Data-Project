package com.springboot.bank.bankDataProject.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.springboot.bank.bankDataProject.POJO.User;
import com.springboot.bank.bankDataProject.form.RegisterUserform;

public interface UserService extends UserDetailsService {

//	public User findByUsername(String username);
	
	public void save(RegisterUserform user);
	
	public User findByemail(String email);
}
