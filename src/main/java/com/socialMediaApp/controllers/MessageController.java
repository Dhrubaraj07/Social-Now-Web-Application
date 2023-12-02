package com.socialMediaApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socialMediaApp.models.Message;
import com.socialMediaApp.models.User;
import com.socialMediaApp.service.MessageService;
import com.socialMediaApp.service.UserService;

@RestController
public class MessageController {
	@Autowired
	MessageService messageService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/api/messages/chat/{chatId}")
	public Message createMessage(@RequestHeader("Authorization") String jwt,
			@RequestBody Message req,
			@PathVariable Integer chatId) throws Exception {
		User reqUser=userService.findUserByJwt(jwt);
		Message message=messageService.createMessage(reqUser, chatId, req);
		return message;
	}
	
	@GetMapping("/api/messages/chat/{chatId}")
	public List<Message> createMessage(@RequestHeader("Authorization")String jwt,
			@PathVariable Integer chatId) throws Exception {
		
		User reqUser=userService.findUserByJwt(jwt);
		List<Message> messages=messageService.findChatsMessages(chatId);
		return messages;
	}
}
