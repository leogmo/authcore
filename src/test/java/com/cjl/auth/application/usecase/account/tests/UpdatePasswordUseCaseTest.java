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
import com.cjl.auth.application.usecase.account.UpdatePasswordDTO;
import com.cjl.auth.application.usecase.account.UpdatePasswordUseCase;

public class UpdatePasswordUseCaseTest {
	
	@Mock
	private AccountRepository accountRepository;
	
	
	@Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	public void invalidUserShouldThrowSecurityException() {
		
		when(accountRepository.findByEmail("admin@test.com")).thenReturn(Optional.empty());
		
		UpdatePasswordUseCase useCase = new UpdatePasswordUseCase(accountRepository);
		
		Throwable exception = assertThrows(Exception.class, () -> useCase.update(new UpdatePasswordDTO("admin@test.com", "123456")));
		assertEquals("Email não encontrado na base de dados", exception.getMessage());
	}

}
