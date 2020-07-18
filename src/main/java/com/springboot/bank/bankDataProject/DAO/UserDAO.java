package com.springboot.bank.bankDataProject.DAO;

import com.springboot.bank.bankDataProject.POJO.User;

public interface UserDAO {

//	public User findByUsername(String username);
	
	public void save(User user);
	
	public User findByemail(String email);
	
}
