package com.cjl.auth.application.usecase.account;

import java.util.Optional;

import com.cjl.auth.application.usecase.AccountRepository;
import com.cjl.auth.domain.User;

public class UpdatePasswordUseCase {
    private final AccountRepository accountRepository;

    
    public UpdatePasswordUseCase(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	public void update(UpdatePasswordDTO updatePasswordDTO) throws Exception {
    	
        Optional<User> user = accountRepository.findByEmail(updatePasswordDTO.getEmail());
        if (!user.isPresent()) {
            throw new Exception("Email não encontrado na base de dados");
        }

        user.get().updatePassword(updatePasswordDTO.getPassword());
        accountRepository.save(user.get());
    }
}
