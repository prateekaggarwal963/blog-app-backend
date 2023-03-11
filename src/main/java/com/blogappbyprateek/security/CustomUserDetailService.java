//package com.blogappbyprateek.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.blogappbyprateek.entities.User;
//import com.blogappbyprateek.exceptions.ResourceNotFoundException;
//import com.blogappbyprateek.repositories.UserRepo;
//
////for getting user from database
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//	
////	@Autowired
////	private UserRepo userRepo;
////
////	@Override
////	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////		// TODO Auto-generated method stub
////		User user = this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("user","email"+ username, 0));
////		
////		
////		return user;
////	}
//	
//	
//
//}
