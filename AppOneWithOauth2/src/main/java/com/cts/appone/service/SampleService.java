package com.cts.appone.service;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;




@Service
public class SampleService {
	private static Logger log = Logger.getLogger(SampleService.class);
	@PreAuthorize("hasRole('admin')")
	public void adminService(){
	   log.info("Inside the adminService  method");	
	}

}
