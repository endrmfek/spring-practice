package hoteldelluna.springweb.jpaPractice.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class JpaHomeController {

    @RequestMapping("/jpa/home")
    public String jpaHome() {
        log.debug("jpaHome Controller");
        return "jpa/jpaHome";
    }
}
