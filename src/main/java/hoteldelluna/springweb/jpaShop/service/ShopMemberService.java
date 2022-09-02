package hoteldelluna.springweb.jpaShop.service;

import hoteldelluna.springweb.dddPractice.member.command.domain.MemberRepository;
import hoteldelluna.springweb.jpaShop.Repository.ShopMemberRepository;
import hoteldelluna.springweb.jpaShop.dto.ShopMemberFormDto;
import hoteldelluna.springweb.jpaShop.entity.ShopMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ShopMemberService implements UserDetailsService {

    private final ShopMemberRepository shopMemberRepository;

    public ShopMember saveMember(ShopMember member) {
        validateDuplicateMember(member);
        return shopMemberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ShopMember member = shopMemberRepository.findByEmail(email);

        if(member == null) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();

    }

    private void validateDuplicateMember(ShopMember member) {
        ShopMember findMember = shopMemberRepository.findByEmail(member.getEmail());
        if(findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }


}
