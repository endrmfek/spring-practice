package hoteldelluna.springweb.jpaShop.controller;

import hoteldelluna.springweb.jpaShop.dto.ShopMemberFormDto;
import hoteldelluna.springweb.jpaShop.entity.ShopMember;
import hoteldelluna.springweb.jpaShop.service.ShopMemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ShopMemberControllerTest {
    @Autowired
    private ShopMemberService memberService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ShopMember createMember(String email, String password) {
        ShopMemberFormDto memberFormDto = new ShopMemberFormDto();
        memberFormDto.setEmail(email);
        memberFormDto.setName("강민수");
        memberFormDto.setAddress("서울시 마포구 합정동");
        memberFormDto.setPassword(password);
        ShopMember member = ShopMember.createMember(memberFormDto, passwordEncoder);
        return memberService.saveMember(member);
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginSuccessTest() throws Exception {
        String email = "test@email.com";
        String password = "1234";
        this.createMember(email, password);
        mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin()
                .userParameter("email")
                .loginProcessingUrl("/shop/members/login")
                .user(email).password(password)
        ).andExpect(SecurityMockMvcResultMatchers.authenticated());
    }
}