package com.cjl.auth.infraestructure.security;

import com.cjl.auth.domain.User;

public interface JwtTokenUtil {
	String generateToken(User user);
}
