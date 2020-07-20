package com.springboot.bank.bankDataProject.DAO;

import com.springboot.bank.bankDataProject.POJO.SwiftDetails;

public interface SwiftDAO {

	public SwiftDetails findBySwift(String code);
}
