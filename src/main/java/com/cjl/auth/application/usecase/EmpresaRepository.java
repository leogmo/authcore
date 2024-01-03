package com.cjl.auth.application.usecase;

import com.cjl.auth.domain.Empresa;

public interface EmpresaRepository {

	Empresa save(Empresa empresa) throws Exception;
}
