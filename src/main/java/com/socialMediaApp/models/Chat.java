package com.socialMediaApp.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String chat_name;
	@ManyToMany
	private List<User> users=new ArrayList(); 
	private LocalDateTime timestamp;
	@OneToMany(mappedBy = "chat")
	private List<Message> messages=new ArrayList();
}
