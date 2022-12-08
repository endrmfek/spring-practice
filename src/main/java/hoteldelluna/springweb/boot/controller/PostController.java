package hoteldelluna.springweb.boot.controller;

import hoteldelluna.springweb.boot.dto.BootMemberDto;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/boot/api/v1/post-api")
public class PostController {

    @RequestMapping(value = "/domain" , method = RequestMethod.GET)
    public String postMapping() {
        return "Hello Post Api";
    }

    @PostMapping(value = "/member1")
    public String postMember(@RequestBody Map<String, Object> param) {
        StringBuilder sb = new StringBuilder();

        param.forEach((key, value) -> sb.append(key).append(" : ").append(value).append("\n"));

        return sb.toString();
    }

    @PostMapping(value = "/member2")
    public BootMemberDto postMemberDto(@RequestBody BootMemberDto memberDto) {
        return memberDto;
    }
}
