package com.cts.appone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appone")
public class AppOneController {

	@GetMapping("/admin")
	public ResponseEntity<?> getResponseForAdminUser() {
		return new ResponseEntity<String>("Hello!This is from AppOne. Only Admin user can access this", HttpStatus.OK);
	}

	@GetMapping("/user")
	public ResponseEntity<?> getResponseForNormalUser() {
		return new ResponseEntity<String>("Hello!This is from AppOne. Any user can access this", HttpStatus.OK);
	}

}
