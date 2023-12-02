package com.socialMediaApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialMediaApp.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
