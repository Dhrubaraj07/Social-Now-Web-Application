package com.socialMediaApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socialMediaApp.models.Reels;
import com.socialMediaApp.models.User;
import com.socialMediaApp.service.ReelsService;
import com.socialMediaApp.service.UserService;

import jakarta.persistence.metamodel.ListAttribute;

@RestController
public class ReelsController {
	@Autowired
	ReelsService reelsService;
	@Autowired
	UserService userService;
	
	@PostMapping("/api/reels")
	public Reels createReels(@RequestBody Reels reel,@RequestHeader("Authorization") String jwt) {
		User reqUser=userService.findUserByJwt(jwt);
		return reelsService.createReel(reel, reqUser);
	}
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReels() {
		return reelsService.findAllReels();
	}
	
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels> findUsersReel(@PathVariable Integer userId) throws Exception {
		return reelsService.findUsersReel(userId);
	}
}
