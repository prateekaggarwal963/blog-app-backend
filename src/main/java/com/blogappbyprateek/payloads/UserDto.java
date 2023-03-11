package com.blogappbyprateek.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	@NotEmpty
	@Size(min =4,message="must be 4 character")
	private String name;
	@Email(message="Email address not valid")
	@NotEmpty
	private String email;
	@NotEmpty
	@Size(min=3, max=10, message ="password must be minimum 3 and max 10")
	//@Pattern(regexp = )//add reguler expretion according to you
	private String password;
	@NotNull
	private String about;
}
