package com.blogappbyprateek.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class JwtAuthResponse {

	@Getter
	@Setter
	private String token;
}
