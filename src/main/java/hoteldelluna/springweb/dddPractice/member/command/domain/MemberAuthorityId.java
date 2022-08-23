package hoteldelluna.springweb.dddPractice.member.command.domain;

import hoteldelluna.springweb.dddPractice.order.command.domain.Orderer;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
public class MemberAuthorityId implements Serializable {

    private MemberId id;

    private String authority;

    protected MemberAuthorityId() {}

    public MemberAuthorityId(MemberId id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public static MemberAuthorityId of(MemberId id , String authority) {return new MemberAuthorityId(id, authority);}
    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        MemberAuthorityId memberAuthorityId = (MemberAuthorityId) o;
        return Objects.equals(id, memberAuthorityId.id) && Objects.equals(authority, memberAuthorityId.authority);
    }
}
