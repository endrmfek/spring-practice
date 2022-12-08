package hoteldelluna.springweb.jpaShop.config;

import hoteldelluna.springweb.jpaShop.service.ShopMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    ShopMemberService memberService;

    /*
    * http 요청에 대한 보안
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin()
                .loginPage("/shop/members/login")
                .defaultSuccessUrl("/shop/")
                .usernameParameter("email")
                .failureUrl("/shop/members/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/shop/members/logout"))
                .logoutSuccessUrl("/shop/");

        http.authorizeRequests()
                .mvcMatchers("/", "/ddd/**", "/shop" , "/shop/members/**", "/shop/item/**", "/images/**", "/boot/**").permitAll()
                .mvcMatchers("/shop/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();

//        http.exceptionHandling()
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**" , "/css/**", "/js/**", "/img/**");
    }

    /*
    * 인증은 AuthenticationManager 가 처리.
    *
    * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)
                .passwordEncoder(passwordEncoder());
    }
    /*
    * 데이터베이스에 비밀번호 암호화.
    * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
