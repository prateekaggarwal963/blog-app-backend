package com.blogappbyprateek.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class JwtAuthRequest {
	
	@Getter
	@Setter
	private String username;
	
	@Getter
	@Setter
	private String password;

}
