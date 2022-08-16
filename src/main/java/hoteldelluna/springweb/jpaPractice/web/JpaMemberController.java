package hoteldelluna.springweb.jpaPractice.web;

import hoteldelluna.springweb.jpaPractice.entity.JpaAddress;
import hoteldelluna.springweb.jpaPractice.entity.JpaMember;
import hoteldelluna.springweb.jpaPractice.service.JpaMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class JpaMemberController {

    private final JpaMemberService jpaMemberService;

    @GetMapping(value = "/jpa/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm" , new JpaMemberForm()); // 왜 만듬?..
        return "jpa/members/createMemberForm";
    }

    @PostMapping(value = "/jpa/members/new")
    public String create(@Valid JpaMemberForm form , BindingResult result) {
        if(result.hasErrors()) {
            return "jpa/members/createMemberForm";
        }

        JpaAddress jpaAddress = new JpaAddress(form.getCity() , form.getStreet(), form.getZipcode());
        JpaMember jpaMember = new JpaMember();
        jpaMember.setName(form.getName());
        jpaMember.setJpaAddress(jpaAddress);
        jpaMemberService.join(jpaMember);

        return "redirect:/jpa/home";
    }

    //회원 목록 조회
    @GetMapping(value = "/jpa/members")
    public String list(Model model) {
        List<JpaMember> jpaMembers = jpaMemberService.findMembers();
        model.addAttribute("jpaMembers" , jpaMembers);
        return "jpa/members/memberList";
    }
}
