package com.luv2code.spring.ExpenseTracker.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.luv2code.spring.ExpenseTracker.Service.UserService;



@Configuration
@EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();
		
	}
	 @Autowired
	    private UserService userService;
	 

	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(bCryptPasswordEncoder());
	
		
		return authProvider;
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
            .antMatchers("/register").permitAll()
            .antMatchers("/confirm").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
    				.loginPage("/showMyLoginPage")
    				.permitAll()
    				.loginProcessingUrl("/authenticateTheUser")
    				.successHandler(customAuthenticationSuccessHandler)
    			.and()
    			.logout()
    			.logoutUrl("/perform_logout")
    			.invalidateHttpSession(true)
    			.deleteCookies("JSESSIONID")
    			.and()
    			.exceptionHandling().accessDeniedPage("/access-denied");
            
            
            
		
	}
	
        }
        
     
