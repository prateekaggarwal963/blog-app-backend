package com.blogappbyprateek.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogappbyprateek.payloads.ApiResponse;
import com.blogappbyprateek.payloads.PostDto;
import com.blogappbyprateek.payloads.PostResponse;
import com.blogappbyprateek.services.FileService;
import com.blogappbyprateek.services.PostService;

import jakarta.servlet.http.HttpServletResponse;
@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;
	
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable("userId") Integer userID,
			@PathVariable("categoryId") Integer catId)
	{
		PostDto createPost= this.postService.createPost(postDto, userID, catId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	//getByUser
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
	{
		List<PostDto> posts = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//getByCategory
		@GetMapping("/category/{categoryId}/posts")
		public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
		{
			List<PostDto> posts = this.postService.getPostsByUser(categoryId);
			return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		}
	

		//get all posts
			@GetMapping("/posts")
			public ResponseEntity<PostResponse> getAllPost(@RequestParam(value="pageNumber",defaultValue = "0",required = false) Integer pageNumber,
					@RequestParam(value="pageSize",defaultValue = "5",required = false) Integer pageSize,
					@RequestParam(value="sortBy",defaultValue = "postId",required = false) String sortBy,
					@RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir)
			{
				PostResponse allPost= this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
				return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
				
				//URL:-  http://localhost:9090/api/posts?pageNumber=0&pageSize=1
			}
			
		//get post details by post id
			@GetMapping("/posts/{postId}")
			public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
			{
				PostDto postDto = this.postService.getPostById(postId);
				return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
			}
		
		//delete post by id
			@DeleteMapping("/posts/{postId}")
			public ApiResponse deletePost(@PathVariable Integer postId)
			{
				this.postService.deletePost(postId);
				return new ApiResponse("Post is Successfully deleted", true);
			}
			
		//update post by id
			@PutMapping("/posts/{postId}")
			public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId )
			{
				PostDto updatePost = this.postService.updatePost(postDto, postId);
				return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
			}
			
		//search post by title
			@GetMapping("/posts/search/{keywords}")
			public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords)
			{
				List<PostDto> postDto = this.postService.searchPosts(keywords);
				return new ResponseEntity<List<PostDto>>(postDto, HttpStatus.OK);
			}
			
		//post image upload
			@PostMapping("post/image/upload/{postId}")
			public ResponseEntity<PostDto> uploadPostImage(@PathVariable Integer postId, @RequestParam("image") MultipartFile image) throws IOException
			{
				PostDto postDto = this.postService.getPostById(postId);
				String fileName= this.fileService.uploadImage(path, image);
				postDto.setImageName(fileName);
				PostDto updatePost = this.postService.updatePost(postDto, postId);
				
				return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
			}
			
		//serving image
			@GetMapping(value = "post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
			public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException
			{
				InputStream resource = this.fileService.getResource(path, imageName);
				response.setContentType(MediaType.IMAGE_JPEG_VALUE);
				org.springframework.util.StreamUtils.copy(resource, response.getOutputStream());
			}
			
}
