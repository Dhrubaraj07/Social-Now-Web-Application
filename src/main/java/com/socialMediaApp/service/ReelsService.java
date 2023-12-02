package com.socialMediaApp.service;

import java.util.List;

import com.socialMediaApp.models.Reels;
import com.socialMediaApp.models.User;

public interface ReelsService {
	
	public Reels createReel(Reels reel,User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUsersReel(Integer userId) throws Exception;
}
