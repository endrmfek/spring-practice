package hoteldelluna.springweb.jpaPractice.repository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaMemberRepositoryTest {

    @Autowired
    JpaMemberRepository jpaMemberRepository;

//    @Test
//    @Transactional
////    @Rollback(false)
//    public void testMember() {
//        Member member = new Member();
//        member.setUsername("memberA");
//        Long savedId = memberRepository.save(member); // id가 자동생성돼.
//
//        Member findMember = memberRepository.find(savedId);
//
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//
//        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//
//        Assertions.assertThat(findMember).isEqualTo(member); // JPA 엔티티 동일성 보장.
//
//    }
}