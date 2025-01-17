package com.snva.crmproject.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snva.crmproject.entity.userDetails.User;
import com.snva.crmproject.service.AuthenticationService;

import lombok.Data;

@Data
class ResponseObject {
	private boolean isCreated;
	private String messsage;
	public ResponseObject(boolean isCreated, String messsage) {
		super();
		this.isCreated = isCreated;
		this.messsage = messsage;
	}
	public boolean isCreated() {
		return isCreated;
	}
	public void setCreated(boolean isCreated) {
		this.isCreated = isCreated;
	}
	public String getMesssage() {
		return messsage;
	}
	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}
	
}
@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService; 

	@Autowired
	   private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseObject> register(@RequestBody User user){
		System.out.println(user.getUsername());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if(authenticationService.createUser(user)==null) {
			return new ResponseEntity<ResponseObject>(new ResponseObject(false, "username exists"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResponseObject>(new ResponseObject(true, "Successfully Created User"), HttpStatus.OK);
	}
	@PreAuthorize("hasAuthority('SU')")
	@PostMapping("/createRec_admin")
	ResponseEntity<ResponseObject> createRec_admin(@RequestBody User user){
		System.out.println(user.getUsername());
		user.setRole("REC_ADMIN");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if(authenticationService.createUser(user)==null) {
			return new ResponseEntity<ResponseObject>(new ResponseObject(false, "username exists"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResponseObject>(new ResponseObject(true, "Successfully Created User"), HttpStatus.OK);
	}
	@PreAuthorize("hasAuthority('SU')")
	@PostMapping("/createTech_admin")
	ResponseEntity<ResponseObject> createTech_admin(@RequestBody User user){
		System.out.println(user.getUsername());
		user.setRole("TECH_ADMIN");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if(authenticationService.createUser(user)==null) {
			return new ResponseEntity<ResponseObject>(new ResponseObject(false, "username exists"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResponseObject>(new ResponseObject(true, "Successfully Created User"), HttpStatus.OK);
	}
	@PreAuthorize("hasAuthority('SU')")
	@PostMapping("/createBD_admin")
	ResponseEntity<ResponseObject> createBD_admin(@RequestBody User user){
		System.out.println(user.getUsername());
		user.setRole("BD_ADMIN");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if(authenticationService.createUser(user)==null) {
			return new ResponseEntity<ResponseObject>(new ResponseObject(false, "username exists"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResponseObject>(new ResponseObject(true, "Successfully Created User"), HttpStatus.OK);
	}
	@PreAuthorize("hasAuthority('REC_ADMIN')")
	@PostMapping("/createRecUser")
	ResponseEntity<ResponseObject> createRecUser (@RequestBody User user){
		System.out.println(user.getUsername());
		user.setRole("REC_USER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if(authenticationService.createUser(user)==null) {
			return new ResponseEntity<ResponseObject>(new ResponseObject(false, "username exists"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResponseObject>(new ResponseObject(true, "Successfully Created User"), HttpStatus.OK);
	}
	@PreAuthorize("hasAuthority('TECH_ADMIN')")
	@PostMapping("/createTechUser")
	ResponseEntity<ResponseObject> createTechUser (@RequestBody User user){
		System.out.println(user.getUsername());
		user.setRole("TECH_USER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if(authenticationService.createUser(user)==null) {
			return new ResponseEntity<ResponseObject>(new ResponseObject(false, "username exists"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResponseObject>(new ResponseObject(true, "Successfully Created User"), HttpStatus.OK);
	}
	@PreAuthorize("hasAuthority('BD_ADMIN')")
	@PostMapping("/createBDUser")
	ResponseEntity<ResponseObject> createBDUser (@RequestBody User user){
		System.out.println(user.getUsername());
		user.setRole("BD_USER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if(authenticationService.createUser(user)==null) {
			return new ResponseEntity<ResponseObject>(new ResponseObject(false, "username exists"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ResponseObject>(new ResponseObject(true, "Successfully Created User"), HttpStatus.OK);
	}
	
	@RequestMapping("/loginEndpoint")
	  public User login(Principal user) {
//		System.out.println(user.toString());
		System.out.println(user.getName().isEmpty());
		return authenticationService.getUserDetailsbyUserName(user.getName());
	  }
	@RequestMapping("/")
	  public User user(Principal user) {
		System.out.println(user.getName().isEmpty());
		return authenticationService.getUserDetailsbyUserName(user.getName());
	  }

}
