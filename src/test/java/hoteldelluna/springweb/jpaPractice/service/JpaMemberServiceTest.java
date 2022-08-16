package hoteldelluna.springweb.jpaPractice.service;

import hoteldelluna.springweb.jpaPractice.entity.JpaMember;
import hoteldelluna.springweb.jpaPractice.repository.JpaMemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JpaMemberServiceTest {

    @Autowired
    JpaMemberService jpaMemberService;
    @Autowired
    JpaMemberRepository jpaMemberRepository;

    @Test
    public void 회원가입() throws Exception{
        // given
        JpaMember jpaMember = new JpaMember();
        jpaMember.setName("kim");
        // when
        Long saveId = jpaMemberService.join(jpaMember);

        // then
        Assert.assertEquals(jpaMember, jpaMemberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        JpaMember jpaMember1 = new JpaMember();
        jpaMember1.setName("kim");

        JpaMember jpaMember2 = new JpaMember();
        jpaMember2.setName("kim");

        jpaMemberService.join(jpaMember1);
        jpaMemberService.join(jpaMember2);

        Assert.fail("예외가 발생해야 한다.");
    }
}
