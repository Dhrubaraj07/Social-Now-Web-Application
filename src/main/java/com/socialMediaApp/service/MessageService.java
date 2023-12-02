package com.socialMediaApp.service;

import java.util.List;

import com.socialMediaApp.models.Chat;
import com.socialMediaApp.models.Message;
import com.socialMediaApp.models.User;

public interface MessageService {
	
	
	public Message createMessage(User user,Integer chatId,Message message) throws Exception;
	
	public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
