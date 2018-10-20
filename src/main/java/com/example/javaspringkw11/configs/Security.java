package com.example.javaspringkw11.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

@Autowired
@Qualifier("userServiceImpl")
    private UserDetailsService userDetailsService;

    @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }


            @Bean
            public DaoAuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
                provider.setUserDetailsService(userDetailsService);
                provider.setPasswordEncoder(passwordEncoder());
                return provider;
            }

// @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.inMemoryAuthentication().withUser("user").password("{noop}pass").roles("ADMIN");
//     }// то тільки якшо в памяті проекту а не в БД
     @Override
     protected void configure(HttpSecurity http) throws Exception {
         http
                 .authorizeRequests()//авторизовує всі реквести(запити)
                 .antMatchers("/", "/home","/saveUser").permitAll()//на ці запити переходить будь хто
                 .anyRequest().authenticated()//на всі інші тільки аутентифіковані
                 .antMatchers("/admin/**").hasRole("ADMIN")//тільки адмін на такі
                 .and()
                 .formLogin()
                 .loginPage("/login")//сторінка логінації
                 .successForwardUrl("/successURL")//коли залогінюсь буде така урла handle with post mapping in controller
                 .failureUrl("/login?error").permitAll()//якщо ні то помилка
                 .permitAll()
                 .and()
                 .logout()
                 .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).//урла після того як я розлогінився
                 logoutSuccessUrl("/login")
                 .permitAll();
     }
}