package com.socialMediaApp.service;

import com.socialMediaApp.models.Comment;

public interface CommentService {
	public Comment createComments(Comment comment,Integer postId,Integer userId) throws Exception;
	public Comment findCommentById(Integer commentId) throws Exception;
	public Comment likeComment(Integer CommentId,Integer userId) throws Exception;
}
