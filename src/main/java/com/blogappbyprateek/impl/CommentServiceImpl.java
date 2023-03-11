package com.blogappbyprateek.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogappbyprateek.entities.Comment;
import com.blogappbyprateek.entities.Post;
import com.blogappbyprateek.exceptions.ResourceNotFoundException;
import com.blogappbyprateek.payloads.CommentDto;
import com.blogappbyprateek.repositories.CommentRepo;
import com.blogappbyprateek.repositories.PostRepo;
import com.blogappbyprateek.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	//create comment
	public CommentDto createComment(CommentDto commentDto, Integer postId)
	{
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id", postId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}
	//delete comment
	public void deleteComment(Integer commentId)
	{
		Comment com = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Comment Id", commentId));
		this.commentRepo.delete(com);
	}
	
}
