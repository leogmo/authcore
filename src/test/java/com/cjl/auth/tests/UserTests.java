package com.cjl.auth.tests;

import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Test;

import com.cjl.auth.application.usecase.account.SignupDTO;
import com.cjl.auth.domain.User;

public class UserTests {
	@Test
	public void whenSignupShouldEncriptPassword() throws NoSuchAlgorithmException, InvalidKeySpecException {
		SignupDTO dto = new SignupDTO();
		dto.setPassword("109148");
		
		User user = new User(dto, 1L);
		
		assertTrue(user.matchesPassword("109148"));
	}
	
	@Test
	public void whenUpdatePasswordShouldMatch() throws NoSuchAlgorithmException, InvalidKeySpecException {
		SignupDTO dto = new SignupDTO();
		dto.setPassword("109148");
		
		User user = new User(dto, 1L);
		
		user.updatePassword("556622");
		
		assertTrue(user.matchesPassword("556622"));
	}

}
