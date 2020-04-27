package br.com.controlebezerras;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
public class HttpsConfiguration extends WebSecurityConfigurerAdapter {

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// classe que redireciona do http para o https
//		http.requiresChannel().anyRequest().requiresSecure();
//		http.csrf().disable()
//        .anonymous().authorities("ROLE_ANONYMOUS")
//        .and()
//        .authorizeRequests()
//        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//        .antMatchers(HttpMethod.GET,"/login").permitAll();
//	}

}
