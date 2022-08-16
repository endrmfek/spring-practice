package hoteldelluna.springweb.jpaPractice.service;

import hoteldelluna.springweb.jpaPractice.entity.JpaMember;
import hoteldelluna.springweb.jpaPractice.repository.JpaMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 읽기 베이스
@RequiredArgsConstructor
public class JpaMemberService {

    private final JpaMemberRepository jpaMemberRepository;

    @Transactional
    public Long join(JpaMember jpaMember) {
        validateDuplicateMember(jpaMember);
        jpaMemberRepository.save(jpaMember);
        return jpaMember.getId();
    }

    private void validateDuplicateMember(JpaMember jpaMember) {
        List<JpaMember> findJpaMembers = jpaMemberRepository.findByName(jpaMember.getName());
        if(!findJpaMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<JpaMember> findMembers() {
        return jpaMemberRepository.findAll();
    }

    public JpaMember findOne(Long memberId) {
        return jpaMemberRepository.findOne(memberId);
    }

}
