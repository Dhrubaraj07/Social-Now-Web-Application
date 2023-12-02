package com.socialMediaApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMediaApp.models.Chat;
import com.socialMediaApp.models.User;
import com.socialMediaApp.repository.ChatRepository;

@Service
public class ChatServiceImplementation implements ChatService{
	@Autowired
	ChatRepository chatRepository;
	
	@Override
	public Chat createChat(User reqUser, User user) {
		Chat isExist=chatRepository.findChatByUsersId(user, reqUser);
		if(isExist!=null) return isExist;
		Chat chat=new Chat();
		chat.getUsers().add(user);
		chat.getUsers().add(reqUser);
		chat.setTimestamp(LocalDateTime.now());
		return chatRepository.save(chat);
	}

	@Override
	public Chat findChatById(Integer chatId) throws Exception {
		Optional<Chat> opt=chatRepository.findById(chatId);
		if(opt.isEmpty()) throw new Exception("Chat not found with chatId:"+chatId);
		return opt.get();
	}

	@Override
	public List<Chat> findUsersChat(Integer userId) {
		return chatRepository.findByUsersId(userId);
	}

}
