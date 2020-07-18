package com.springboot.bank.bankDataProject.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bank.bankDataProject.DAO.RoleDAO;
import com.springboot.bank.bankDataProject.DAO.UserDAO;
import com.springboot.bank.bankDataProject.POJO.Role;
import com.springboot.bank.bankDataProject.POJO.User;
import com.springboot.bank.bankDataProject.form.RegisterUserform;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

//	@Override
//	@Transactional
//	public User findByUsername(String username) {
//		
//		return userDAO.findByUsername(username);
//	}

	@Override
	@Transactional
	public void save(RegisterUserform reguser) {
		
		User user = new User();
		 // assign user details to the user object
//		user.setUserName(reguser.getUserName());
		user.setPassword(passwordEncoder.encode(reguser.getPassword()));
		user.setFirstName(reguser.getFirstName());
		user.setLastName(reguser.getLastName());
		user.setEmail(reguser.getEmail());
		
		// give user default role of "employee"
		user.setRoles(Arrays.asList(roleDAO.findRoleByName("ROLE_EMPLOYEE")));
		
		userDAO.save(user);
	}
	
	@Override
	@Transactional
	public User findByemail(String email) {
		return userDAO.findByemail(email);
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDAO.findByemail(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
