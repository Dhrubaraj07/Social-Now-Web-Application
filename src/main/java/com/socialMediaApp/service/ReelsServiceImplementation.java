package com.socialMediaApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMediaApp.models.Reels;
import com.socialMediaApp.models.User;
import com.socialMediaApp.repository.ReelsRepository;
@Service
public class ReelsServiceImplementation implements ReelsService{
	@Autowired
	private ReelsRepository reelsRepository;
	@Autowired
	private UserService userService;
	
	@Override
	public Reels createReel(Reels reel, User user) {
		Reels createReels=new Reels();
		createReels.setTitle(reel.getTitle());
		createReels.setVideo(reel.getVideo());
		createReels.setUser(user);
		
		return reelsRepository.save(createReels);
	}

	@Override
	public List<Reels> findAllReels() {
		List<Reels> reels=reelsRepository.findAll();
		return reels;
	}

	@Override
	public List<Reels> findUsersReel(Integer userId) throws Exception {
		userService.findUserById(userId);
		List<Reels> reels=reelsRepository.findByUserId(userId);
		return reels;
	}

}
