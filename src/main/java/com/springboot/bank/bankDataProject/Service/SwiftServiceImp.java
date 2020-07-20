package com.springboot.bank.bankDataProject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bank.bankDataProject.DAO.SwiftDAO;
import com.springboot.bank.bankDataProject.POJO.SwiftDetails;

@Service
public class SwiftServiceImp implements SwiftService {

	@Autowired
	private SwiftDAO swiftDAO;
	
	@Override
	@Transactional
	public SwiftDetails findBySwift(String code) {
		
		return swiftDAO.findBySwift(code);
		
	}

}
