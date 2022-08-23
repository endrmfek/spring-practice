package hoteldelluna.springweb.dddPractice.member.command.application;

import hoteldelluna.springweb.dddPractice.member.command.domain.Member;
import hoteldelluna.springweb.dddPractice.member.command.domain.MemberId;
import hoteldelluna.springweb.dddPractice.member.command.domain.MemberRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlockMemberService {
    private MemberRepository memberRepository;

    public BlockMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void block(String memberId) {
        Member member = memberRepository.findById(new MemberId(memberId)).orElseThrow(() -> new NoMemberException());
        member.block();
    }
}
