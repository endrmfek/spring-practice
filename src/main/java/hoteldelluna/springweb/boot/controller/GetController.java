package hoteldelluna.springweb.boot.controller;

import hoteldelluna.springweb.boot.dto.BootMemberDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/boot/api/v1/get-api")
public class GetController {

    @RequestMapping(value = "/hello" , method = RequestMethod.GET)
    public String getHello() {
        return "Hello world";
    }

    //매개변수가 없는 get
    @GetMapping("/name")
    public String getName() {
        return "Minsoo";
    }

    //매개변수를 받는 get
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable) {
        return variable;
    }

    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var) {
        return var;
    }

    //파라미터를 받는 get
    @ApiOperation(value = "GET 메서드 예제", notes = "@RequestParam을 활용한 GET Method")
    @GetMapping(value = "/request1")
    public String getParam1(
            @ApiParam(value = "이름" , required = true) @RequestParam String name,
            @ApiParam(value = "이메일" , required = true) @RequestParam String email,
            @ApiParam(value = "회사" , required = true) @RequestParam String organization
    ) {
        return name + " "  + email + " " + organization;
    }

    //어떤 파라미터가 들어올지 모른다?
    @GetMapping(value = "/request2")
    public String getParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();
        param.forEach((key, value) -> sb.append(key + " " + value + "\n"));
        return sb.toString();
    }

    //객체로 받고싶으면
    //들어오는 데이터 키 : 값이 memberDto 멤버명이랑 같아야돼.
    @GetMapping(value = "/request3")
    public BootMemberDto getParam3(BootMemberDto memberDto) {
        return memberDto;
    }
}
