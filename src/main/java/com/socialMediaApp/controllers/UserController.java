package com.socialMediaApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socialMediaApp.models.User;
import com.socialMediaApp.repository.UserRepository;
import com.socialMediaApp.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;
	
	
	
	@GetMapping("/api/users")
	public List<User> getUsers(){
		List<User> users=userRepository.findAll();
		return users;
	}
	@PutMapping("/api/users/")
	public User updateUser(@RequestHeader("Authorization")String jwt,@RequestBody User user) throws Exception {
		User reqUser=userService.findUserByJwt(jwt);
		User updatedUser=userService.updateUser(user, reqUser.getId());
		return updatedUser;
	}
	@GetMapping("/api/users/{userId}")
	public User getUserById(@PathVariable Integer userId) throws Exception {
		User user=userService.findUserById(userId);
		return user;
	}
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization")String jwt,@PathVariable Integer userId2) throws Exception {
		User reqUser=userService.findUserByJwt(jwt);
		User user=userService.followUser(reqUser.getId(), userId2);
		return user;
	}
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		List<User> users=userService.search(query);
		return users;
	}
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization")String jwt) {
		User user=userService.findUserByJwt(jwt);
		user.setPassword(null);
		return user;
	}
	
}
