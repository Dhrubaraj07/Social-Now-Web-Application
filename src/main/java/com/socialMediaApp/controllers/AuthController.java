package com.socialMediaApp.controllers;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialMediaApp.config.JWTProvider;
import com.socialMediaApp.models.User;
import com.socialMediaApp.repository.UserRepository;
import com.socialMediaApp.request.LoginRequest;
import com.socialMediaApp.response.AuthResponse;
import com.socialMediaApp.service.CustomeUserDetailsService;
import com.socialMediaApp.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	CustomeUserDetailsService customeUserDetails;
	
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {
		
		User isExist=userRepository.findByEmail(user.getEmail());
		if(isExist!=null) throw new Exception("User already exist with this email");
		
		User newUser=new User();
		
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser=userRepository.save(newUser);
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		String token=JWTProvider.generateToken(authentication);
		AuthResponse res=new AuthResponse(token,"Register Success");
		return res;
	}
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		Authentication authentication=
				authenticate(loginRequest.getEmail(),loginRequest.getPassword());
		String token=JWTProvider.generateToken(authentication);
		AuthResponse res=new AuthResponse(token,"Sign In Success");
		return res;
	}

	private Authentication authenticate(String email, String password) {
		UserDetails userDetails=customeUserDetails.loadUserByUsername(email);
		if(userDetails==null) throw new BadCredentialsException("Invalid username");
		if(!passwordEncoder.matches(password, userDetails.getPassword()))
			throw new BadCredentialsException("Invalid Password");
		
		return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
	}
}
