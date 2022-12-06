package hoteldelluna.springweb.boot.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boot/api/v1/delete-api")
public class DeleteController {

    //경로에서 삭제?
    @DeleteMapping(value = "/{variable}")
    public String deleteVariable(@PathVariable String variable) {
        return variable;
    }

    @DeleteMapping(value = "/request1")
    public String deleteParam(@RequestParam String email) {
        return "e-mail : " + email;
    }



}
