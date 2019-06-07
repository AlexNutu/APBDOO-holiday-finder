package com.holiday.finder.config;

import com.holiday.finder.service.RememberMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource securityDataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(securityDataSource)
                .passwordEncoder(passwordEncoder());

//        auth.inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder().encode("admin")).roles(
//                "CUSTOMER","HELLO")
//                .and().withUser("userHello").password(passwordEncoder().encode("1234")).roles("HELLO");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()/*.anyRequest().authenticated()*/
                .antMatchers("/signup/**").permitAll()
                .antMatchers("/*").authenticated().and().authorizeRequests()
                .antMatchers("/console/**").permitAll()
                .antMatchers("/hello/**").hasAuthority("USER")
                .antMatchers("/place/**").hasAuthority("USER")
                .and().exceptionHandling()
                .accessDeniedPage("/accessDenied")
                .and()
                .formLogin()
                .loginPage("/showLogInForm")
                .failureUrl("/showErrorLogIn")
                .loginProcessingUrl("/authUser")
                .permitAll().
                and().logout().permitAll();

        http.sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/login?expired");

        // For accessing h2-console tables
        http.csrf().disable();
        http.headers().frameOptions().disable();

    }

    @Bean
    public RememberMeServices rememberMeServices() {
        RememberMeService remmemberMeUserDetailsService = new RememberMeService();
        InMemoryTokenRepositoryImpl rememberMeTokenRepository =
                new InMemoryTokenRepositoryImpl();
        PersistentTokenBasedRememberMeServices services =
                new PersistentTokenBasedRememberMeServices("secret", remmemberMeUserDetailsService, rememberMeTokenRepository);
        services.setAlwaysRemember(true);
        return services;
    }

}
