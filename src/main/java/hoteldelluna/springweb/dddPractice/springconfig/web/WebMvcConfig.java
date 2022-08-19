package hoteldelluna.springweb.dddPractice.springconfig.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ddd/home").setViewName("ddd/home");
        registry.addViewController("/ddd/login").setViewName("ddd/login");
        registry.addViewController("/ddd/error/forbidden").setViewName("ddd/error/forbidden");
        registry.addViewController("/ddd/error/notFound").setViewName("ddd/error/notFound");
        registry.addViewController("/ddd/my/main").setViewName("ddd/my/myMain");
        registry.addViewController("/ddd/admin/main").setViewName("ddd/admin/adminMain");
        registry.addViewController("/ddd/loggedOut").setViewName("ddd/loggedOut");

    }
}
