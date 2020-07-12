package com.luv2code.spring.ExpenseTracker.config;

import java.util.List;
import java.util.Objects;

import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.luv2code.spring.ExpenseTracker.Model.User;
import com.luv2code.spring.ExpenseTracker.Service.UserService;
public class CustomUserDetailAuthenticationProvider implements AuthenticationProvider {
	private final UserService userService;
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	private static CustomUserDetailAuthenticationProvider singleTonInstance;
	
	public static CustomUserDetailAuthenticationProvider getInstance(UserService userService, BCryptPasswordEncoder passwordEncoder) {
		if(singleTonInstance==null) {
			singleTonInstance = new  CustomUserDetailAuthenticationProvider(userService, passwordEncoder);
		}
		return singleTonInstance;
	}
	
	private CustomUserDetailAuthenticationProvider(UserService userService, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		if(Objects.isNull(authentication.getCredentials())) {
			return null;
		}
		String password = authentication.getCredentials().toString();
		UserDetails user = userService.loadUserByUsername(userName);
		if(user==null) {
			throw new UsernameNotFoundException("User :"+ user.getUsername() +"Doesn't Exist");
		}
		if(passwordEncoder.matches(password, user.getPassword())) {
			if(user.isEnabled()==false) {
				throw new UsernameNotFoundException("User not Verified");
			}
			return new UsernamePasswordAuthenticationToken(
					userName, password, user.getAuthorities());
		} else {
			throw new UsernameNotFoundException("User : "+ user.getUsername()+ "Password is incorrect");
		}
		
		
		
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	

}
