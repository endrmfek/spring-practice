package hoteldelluna.springweb.jpaShop.controller;

import hoteldelluna.springweb.jpaShop.dto.ShopMemberFormDto;
import hoteldelluna.springweb.jpaShop.entity.ShopMember;
import hoteldelluna.springweb.jpaShop.service.ShopMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/shop/members")
@RequiredArgsConstructor
@Slf4j
public class ShopMemberController {
    private final ShopMemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new ShopMemberFormDto());
        return "shop/member/memberForm";
    }

    @PostMapping(value="/new") //회원 가입
    public String memberForm(@ModelAttribute("memberFormDto") @Valid ShopMemberFormDto memberFormDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            log.info("errors = {}" , bindingResult);
            return "shop/member/memberForm";
        }

        try {
            ShopMember member = ShopMember.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalArgumentException e) { //중복회원 에러.
            model.addAttribute("errorMessage" , e.getMessage());
            e.getStackTrace();
            return "shop/member/memberForm";
        }

        return "redirect:/shop/";
    }

    @GetMapping(value = "/login")
    public String loginMember() {
        return "shop/member/memberLoginForm";
    }

    @GetMapping(value="/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "shop/member/memberLoginForm";
    }

}
