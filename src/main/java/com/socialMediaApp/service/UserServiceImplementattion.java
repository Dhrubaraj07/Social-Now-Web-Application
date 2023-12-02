package com.socialMediaApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMediaApp.config.JWTProvider;
import com.socialMediaApp.models.User;
import com.socialMediaApp.repository.UserRepository;

@Service
public class UserServiceImplementattion implements UserService{
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User registerUser(User user) {
		User newUser=new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());
		User savedUser=userRepository.save(newUser);
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws Exception {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) return user.get();
		
		throw new Exception("User not found with userId:"+userId);
		
	}

	@Override
	public User findUserByEmail(String email) {
		User user=userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer userId1, Integer UserId2) throws Exception {
		User user1=findUserById(userId1);
		User user2=findUserById(UserId2);
		
		user1.getFollowers().add(user2.getId());
		user2.getFollowings().add(user1.getId());
		userRepository.save(user1);
		userRepository.save(user2);
		return user1;
	}

	@Override
	public User updateUser(User user,Integer userId) throws Exception {
		Optional<User> user1=userRepository.findById(userId);
		if(user1.isEmpty()) throw new Exception("User not exist with userId:"+userId);
		User oldUser=user1.get();
		
		if(user.getFirstName()!=null) 
			oldUser.setFirstName(user.getFirstName());
		
		if(user.getLastName()!=null)
			oldUser.setLastName(user.getLastName());
		
		if(user.getEmail()!=null)
			oldUser.setEmail(user.getEmail());
		
		if(user.getGender()!=null)
			oldUser.setGender(user.getGender());
		
		User updatedUser=userRepository.save(oldUser);
		return updatedUser;
	}

	@Override
	public List<User> search(String query) {
		
		return userRepository.searchUser(query);
	}

	@Override
	public User findUserByJwt(String jwt) {
		String email=JWTProvider.getEmailFromJwtToken(jwt);
		User user=userRepository.findByEmail(email);
		return user;
	}

}
