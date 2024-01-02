package com.cjl.auth.application.usecase.account;

import java.util.Optional;

import com.cjl.auth.application.usecase.AccountRepository;
import com.cjl.auth.application.usecase.EmailSender;
import com.cjl.auth.domain.User;
import com.cjl.auth.infraestructure.security.JwtTokenUtil;

public class PasswordRecoveryUseCase {

	private JwtTokenUtil jwtTokenUtil;
	
    private AccountRepository accountRepository;
    private EmailSender emailSender;

    public PasswordRecoveryUseCase(JwtTokenUtil jwtTokenUtil, AccountRepository accountRepository,
			EmailSender emailSender) {
		super();
		this.jwtTokenUtil = jwtTokenUtil;
		this.accountRepository = accountRepository;
		this.emailSender = emailSender;
	}

	public void recover(PassRecoverDTO passRecoverDTO) throws Exception {
        Optional<User> user = accountRepository.findByEmail(passRecoverDTO.getEmail());
        if (!user.isPresent()){
            throw new Exception("Email não encontrado na base de dados");
        }
        String token = jwtTokenUtil.generateToken(user.get());

        emailSender.sendRecoveryEmail(token, user.get(), passRecoverDTO.getUrl());
    }
}
