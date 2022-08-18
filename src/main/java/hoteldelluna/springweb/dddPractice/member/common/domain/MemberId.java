package hoteldelluna.springweb.dddPractice.member.common.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
public class MemberId implements Serializable {
    @Column(name="member_id")
    private String id;

    protected MemberId() {}

    public MemberId(String id) {
        this.id = id;
    }

    public static MemberId of(String id) {
        return new MemberId(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || o.getClass() != getClass()) return false;
        MemberId memberId = (MemberId) o;
        return Objects.equals(id, memberId);
    }
}
