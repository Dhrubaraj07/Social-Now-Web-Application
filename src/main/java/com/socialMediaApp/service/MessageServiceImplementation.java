package com.socialMediaApp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMediaApp.models.Chat;
import com.socialMediaApp.models.Message;
import com.socialMediaApp.models.User;
import com.socialMediaApp.repository.ChatRepository;
import com.socialMediaApp.repository.MessageRepository;

@Service
public class MessageServiceImplementation implements MessageService{
	
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	ChatService chatService;
	@Autowired
	ChatRepository chatRepository;
	
	@Override
	public Message createMessage(User user, Integer chatId, Message req) throws Exception {
		Chat chat=chatService.findChatById(chatId);
		Message message=new Message();
		
		message.setChat(chat);
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setUser(user);
		message.setTimestamp(LocalDateTime.now());
		Message savedMessage=messageRepository.save(message);
		chat.getMessages().add(savedMessage);
		chatRepository.save(chat);
		return savedMessage;
	}

	@Override
	public List<Message> findChatsMessages(Integer chatId) throws Exception {
		Chat chat=chatService.findChatById(chatId);
		return messageRepository.findByChatId(chatId);
	}

}
