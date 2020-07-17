package com.springboot.bank.bankDataProject.DAO;

import com.springboot.bank.bankDataProject.POJO.Role;

public interface RoleDAO {

	public Role findRoleByName(String theRoleName);
	
}

