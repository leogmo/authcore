package com.cjl.auth.application.usecase.account.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cjl.auth.application.usecase.AccountRepository;
import com.cjl.auth.application.usecase.account.LoginDTO;
import com.cjl.auth.application.usecase.account.LoginResponseDTO;
import com.cjl.auth.application.usecase.account.LoginUseCase;
import com.cjl.auth.domain.Role;
import com.cjl.auth.domain.User;
import com.cjl.auth.infraestructure.security.JwtTokenUtil;

public class LoginUseCaseTest {
	
	@Mock
	private AccountRepository accountRepository;

	@Mock
	private JwtTokenUtil jwtTokenUtil;
	
	@Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	public void shouldReturnToken() {
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsZW9nbW9AZ21haWwuY29tIiwiZXhwIjoxNzA0MDc0Njc3LCJpYXQiOjE3MDQwNTY2Nzd9.tVu9qWqRrZ9R5XRK2AxEoD9OlVfYa91JjNKil0X5g2_WMLrvPTpDAg1BuQalsXQRm86770_8wDYPVYemWsyQdg";
		
		User user = new User(1L, "Leonardo Oliveira", "$2a$16$QUQdD3EezT6of51P7yFJ1ufDGKVK233SkAWB/N1LS2km4TaOSUE6W", "admin@admin.com", Arrays.asList(Role.ADMIN), 1L);
		
		when(accountRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
		
		when(jwtTokenUtil.generateToken(user)).thenReturn(token);
		
		LoginUseCase useCase = new LoginUseCase(jwtTokenUtil, accountRepository);
		
		LoginResponseDTO resp = new LoginResponseDTO(token);
		
		assertEquals(resp, useCase.login(new LoginDTO("admin@admin.com", "109148")));
	}
	
	@Test
	public void wrongPasswordShouldThrowSecurityException() {
		
		User user = new User(1L, "Leonardo Oliveira", "$2a$16$QUQdD3EezT6of51P7yFJ1ufDGKVK233SkAWB/N1LS2km4TaOSUE6W", "admin@admin.com", Arrays.asList(Role.ADMIN), 1L);
		
		when(accountRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
		
		LoginUseCase useCase = new LoginUseCase(jwtTokenUtil, accountRepository);
		
		Throwable exception = assertThrows(SecurityException.class, () -> useCase.login(new LoginDTO("admin@admin.com", "665522")));
		assertEquals("Senha inválida", exception.getMessage());
	}
	
	@Test
	public void invalidUserShouldThrowSecurityException() {
		
		when(accountRepository.findByEmail("admin@test.com")).thenReturn(Optional.empty());
		
		LoginUseCase useCase = new LoginUseCase(jwtTokenUtil, accountRepository);
		
		Throwable exception = assertThrows(SecurityException.class, () -> useCase.login(new LoginDTO("admin@admin.com", "665522")));
		assertEquals("Usuário não encontrado", exception.getMessage());
	}
	
	
}
