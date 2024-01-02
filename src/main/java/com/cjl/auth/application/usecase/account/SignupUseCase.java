package com.cjl.auth.application.usecase.account;

import com.cjl.auth.application.usecase.AccountRepository;
import com.cjl.auth.domain.User;

public class SignupUseCase {
    private final AccountRepository accountRepository;
    
    public SignupUseCase(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	public User signup(SignupDTO signupDTO) throws Exception {
        return accountRepository.signup(signupDTO);
    }
}
