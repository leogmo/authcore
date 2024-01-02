package com.cjl.auth.application.usecase.account;

import java.util.Optional;

import com.cjl.auth.application.usecase.AccountRepository;
import com.cjl.auth.domain.User;
import com.cjl.auth.infraestructure.security.JwtTokenUtil;

public class LoginUseCase {

	private JwtTokenUtil jwtTokenUtil;

	private final AccountRepository accountRepository;

	public LoginUseCase(JwtTokenUtil jwtTokenUtil, AccountRepository accountRepository) {
		super();
		this.jwtTokenUtil = jwtTokenUtil;
		this.accountRepository = accountRepository;
	}

	public LoginResponseDTO login(LoginDTO loginDTO) {
		Optional<User> user = accountRepository.findByEmail(loginDTO.getEmail());

		if (user.isPresent()) {
			if (user.get().matchesPassword(loginDTO.getPassword())) {
				return new LoginResponseDTO(jwtTokenUtil.generateToken(user.get()));
			} else {
				throw new SecurityException("Senha inválida");
			}
		} else {
			throw new SecurityException("Usuário não encontrado");
		}
	}

}
