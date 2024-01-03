package com.cjl.auth.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cjl.auth.application.usecase.account.SignupDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode(callSuper = false)
public class User {

    private static final int ENCRYPTION_STRENGTH = 12;
	private Long id;
    private String username;
    private String password;
    private String email;
    private List<Role> roles = new ArrayList<Role>();
    private Long empresaId;
    
    public User(SignupDTO dto, Long empresaId) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(ENCRYPTION_STRENGTH);
    	this.username = dto.getUsername();
		this.password = encoder.encode(dto.getPassword());
    	this.email = dto.getEmail();
    	this.roles.add(Role.ADMIN);
    	this.empresaId = empresaId;
    }
    
    public User(Long id, String username, String password, String email, List<Role> roles, Long empresaId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.empresaId = empresaId;
	}

    
    public boolean matchesPassword(String aPassword){
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(ENCRYPTION_STRENGTH);
        return encoder.matches(aPassword, this.password);
    }
    
    public void updatePassword(String aPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(ENCRYPTION_STRENGTH);
		this.password = encoder.encode(aPassword);
	}
}
