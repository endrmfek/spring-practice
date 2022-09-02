package hoteldelluna.springweb.dddPractice.member.command.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "d_member_authorities")
public class MemberAuthorities {

    @EmbeddedId
    private MemberAuthorityId memberAuthorityId;

}
