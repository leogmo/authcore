package com.cjl.auth.application.usecase.account;

import com.cjl.auth.application.usecase.AccountRepository;
import com.cjl.auth.application.usecase.EmpresaRepository;
import com.cjl.auth.domain.Empresa;
import com.cjl.auth.domain.User;

public class SignupUseCase {
    private final AccountRepository accountRepository;
    
    private final EmpresaRepository empresaRepository;
    
    public SignupUseCase(AccountRepository accountRepository, EmpresaRepository empresaRepository) {
		super();
		this.accountRepository = accountRepository;
		this.empresaRepository = empresaRepository;
	}

	public User signup(SignupDTO signupDTO) throws Exception {
		Empresa empresa = empresaRepository.save(new Empresa(signupDTO.getEmpresa()));
		
		User user = new User(signupDTO, empresa.getId());
		
        return accountRepository.signup(user);
    }
}
