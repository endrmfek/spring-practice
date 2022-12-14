package hoteldelluna.springweb.dddPractice.springconfig.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CustomAuthSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails user = (UserDetails) authentication.getPrincipal();

        try{
            Cookie authCookie = new Cookie(WebSecurityConfig.AUTHCOOKIENAME , URLEncoder.encode(encryptId(user), "UTF-8"));
            authCookie.setPath("/ddd");
            response.addCookie(authCookie);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        response.sendRedirect("/ddd/home");
    }

    private String encryptId(UserDetails user) {
        return user.getUsername();
    }

}
