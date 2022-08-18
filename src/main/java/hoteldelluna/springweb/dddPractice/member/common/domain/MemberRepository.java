package hoteldelluna.springweb.dddPractice.member.common.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends Repository<Member , MemberId> {
    Optional<Member> findById(MemberId memberId);

    @Query("select m from Member m where m.id = :id")
    Optional<Member> findByIdForUpdate(@Param("id") MemberId memberId);

    void save(Member member);
}
