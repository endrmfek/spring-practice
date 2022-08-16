package hoteldelluna.springweb.hoteldelluna.controller;

import hoteldelluna.springweb.dddPractice.order.command.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

}
