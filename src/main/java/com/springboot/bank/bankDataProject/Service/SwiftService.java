package com.springboot.bank.bankDataProject.Service;

import com.springboot.bank.bankDataProject.POJO.SwiftDetails;

public interface SwiftService {

	public SwiftDetails findBySwift(String code);
	
}
