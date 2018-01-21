package com.cts.appone.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.cts.appone.security.ldap.CustomLdapAuthoritiesPopulator;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("springManagedCustomLdapAuthoritiesPopulator")
	CustomLdapAuthoritiesPopulator ldapAuthoritiesPopulator;
	@Autowired
	protected void configureUser(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.ldapAuthentication().ldapAuthoritiesPopulator(ldapAuthoritiesPopulator)
		.contextSource()
			.url("ldap://ldap.forumsys.com:389/" + "dc=example,dc=com")
				.managerDn("cn=read-only-admin,dc=example,dc=com")
				.managerPassword("password")
			.and()
				.userDnPatterns("uid={0}");
		/*auth.inMemoryAuthentication().withUser("billy").password("bob").roles("USER").and().withUser("admin")
				.password("adminpassword").roles("ADMIN");*/
	}

	// We do not want the default behavior of form authentication
	// before HTTP Basic authentication we get
	// from WebSecurityConfigurerAdapter.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
		http
        .authorizeRequests()
            .antMatchers("/appone/admin/**").hasRole("admin")
            .anyRequest().fullyAuthenticated()
        .and()
            .httpBasic();
	}

}
