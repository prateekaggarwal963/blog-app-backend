package com.blogappbyprateek.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blogappbyprateek.entities.Category;
import com.blogappbyprateek.entities.Post;
import com.blogappbyprateek.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	//we use custom finder method to find in repository
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	//List<Post> findByTitleContain(String title);

	@Query("Select p from Post p where p.title like:key")
	List<Post> searchByTitle(@Param("key") String title); 
	
}
