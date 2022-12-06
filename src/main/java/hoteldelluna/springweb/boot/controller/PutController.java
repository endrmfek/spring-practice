package hoteldelluna.springweb.boot.controller;

import hoteldelluna.springweb.boot.dto.BootMemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/boot/api/v1/put-api")
public class PutController {

    //헤더가 text/plain으로 들어와.
    @PutMapping(value = "/member1")
    public String postMemberDto1(@RequestBody BootMemberDto memberDto) {
        return memberDto.toString();
    }

    //헤더가 application/json으로 ㄷㄹ어와
    @PutMapping(value = "/member2")
    public BootMemberDto postMemberDto2(@RequestBody BootMemberDto memberDto) {
        return memberDto;
    }

    @PutMapping(value = "/member3")
    public ResponseEntity<BootMemberDto> postMemberDto3(@RequestBody BootMemberDto memberDto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(memberDto);
    }

}
