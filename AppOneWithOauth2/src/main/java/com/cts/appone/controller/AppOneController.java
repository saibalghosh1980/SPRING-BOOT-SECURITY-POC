package com.cts.appone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.appone.service.SampleService;

@RestController
@RequestMapping("/appone")
public class AppOneController {
	
	@Autowired
	SampleService sampleService;

	@GetMapping("/admin")
	public ResponseEntity<?> getResponseForAdminUser() {
		sampleService.adminService();
		return new ResponseEntity<String>("Hello!This is from AppOne. Only Admin user can access this", HttpStatus.OK);
	}

	@GetMapping("/user")
	public ResponseEntity<?> getResponseForNormalUser() {
		sampleService.adminService();
		return new ResponseEntity<String>("Hello!This is from AppOne. Any user can access this", HttpStatus.OK);
	}

}
