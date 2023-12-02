package com.socialMediaApp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMediaApp.models.Story;
import com.socialMediaApp.models.User;
import com.socialMediaApp.repository.StoryRepository;

@Service
public class StoryServiceImplementation implements StoryService{
	@Autowired
	private StoryRepository storyRepository;
	@Autowired
	private UserService userService;
	
	@Override
	public Story createStory(Story story, User user) {
		Story createStory=new Story();
		createStory.setCaptions(story.getCaptions());
		createStory.setImage(story.getImage());
		createStory.setUser(user);
		createStory.setTimestamp(LocalDateTime.now());
		storyRepository.save(createStory);
		return createStory;
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws Exception {
		userService.findUserById(userId);
		return storyRepository.findByUserId(userId);
	}

}
