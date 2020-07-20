package com.springboot.bank.bankDataProject.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SwiftForm {
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String swiftcode;
	
	public SwiftForm() {}

	public String getSwiftcode() {
		return swiftcode;
	}

	public void setSwiftcode(String swiftcode) {
		this.swiftcode = swiftcode;
	}
	
	

}
