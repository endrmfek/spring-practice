package hoteldelluna.springweb.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boot")
public class HelloController {

    @RequestMapping("/")
    public String hello() {
        return "Hello world";
    }
}
