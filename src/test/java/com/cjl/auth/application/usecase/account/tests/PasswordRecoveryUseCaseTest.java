package com.cjl.auth.application.usecase.account.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cjl.auth.application.usecase.AccountRepository;
import com.cjl.auth.application.usecase.EmailSender;
import com.cjl.auth.application.usecase.account.LoginDTO;
import com.cjl.auth.application.usecase.account.LoginUseCase;
import com.cjl.auth.application.usecase.account.PassRecoverDTO;
import com.cjl.auth.application.usecase.account.PasswordRecoveryUseCase;
import com.cjl.auth.infraestructure.security.JwtTokenUtil;

public class PasswordRecoveryUseCaseTest {

	@Mock
	private AccountRepository accountRepository;

	@Mock
	private JwtTokenUtil jwtTokenUtil;
	
	@Mock
	private EmailSender emailSender;
	
	@Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	public void invalidUserShouldThrowSecurityException() {
		
		when(accountRepository.findByEmail("admin@test.com")).thenReturn(Optional.empty());
		
		PasswordRecoveryUseCase useCase = new PasswordRecoveryUseCase(jwtTokenUtil, accountRepository, emailSender);
		
		Throwable exception = assertThrows(Exception.class, () -> useCase.recover(new PassRecoverDTO("admin@test.com", "")));
		assertEquals("Email não encontrado na base de dados", exception.getMessage());
	}
}
