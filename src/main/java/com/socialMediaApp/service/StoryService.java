package com.socialMediaApp.service;

import java.util.List;

import com.socialMediaApp.models.Story;
import com.socialMediaApp.models.User;

public interface StoryService {
	
	public Story createStory(Story story,User user);
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;
}
