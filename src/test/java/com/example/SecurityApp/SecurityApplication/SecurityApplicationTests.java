package com.example.SecurityApp.SecurityApplication;

import com.example.SecurityApp.SecurityApplication.Entities.User;
import com.example.SecurityApp.SecurityApplication.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {

	@Autowired
	private JwtService jwtService;
	@Test
	void contextLoads() {
		User user = new User(4L,"Shubham@gmail.com","vishwa97","haka");
		String token = jwtService.generateToken(user);
		System.out.println(token);
		Long id = jwtService.getUserIdFromToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0IiwiZW1haWwiOiJTaHViaGFtQGdtYWlsLmNvbSIsInJvbGVzIjpbIkFkbWluIiwiVVNFUiJdLCJpYXQiOjE3ODcyNDczNTYsImV4cCI6MTc4NzI0NzQxNn0.HK65RQrAk4Qs_XJ4y0tBMOPqYEFd-dCPvFTg058JvOg");

		System.out.println(id);
	}

}
