package com.socialMediaApp.service;

import java.util.List;

import com.socialMediaApp.models.Chat;
import com.socialMediaApp.models.User;

public interface ChatService {
	
	public Chat createChat(User reqUser,User user);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);
}
