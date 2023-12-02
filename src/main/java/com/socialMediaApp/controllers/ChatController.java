package com.socialMediaApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socialMediaApp.models.Chat;
import com.socialMediaApp.models.User;
import com.socialMediaApp.request.CreateChatRequest;
import com.socialMediaApp.service.ChatService;
import com.socialMediaApp.service.UserService;

@RestController
public class ChatController {
	@Autowired
	ChatService chatService;
	@Autowired
	UserService userService;
	
	@PostMapping("/api/chats")
	public Chat createChat(@RequestBody CreateChatRequest req,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User reqUser=userService.findUserByJwt(jwt);
		User user=userService.findUserById(req.getUserId());
		Chat chat=chatService.createChat(reqUser, user);
		return chat;
	}
	@GetMapping("/api/chats")
	public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt){
		User reqUser=userService.findUserByJwt(jwt);
		return chatService.findUsersChat(reqUser.getId());
	}
}
