package hoteldelluna.springweb.dddPractice.order.common.domain;

import hoteldelluna.springweb.dddPractice.member.common.domain.MemberId;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class Orderer { // 주문자

    private MemberId memberId;

    @Column(name = "orderer_name")
    private String name;

    protected Orderer() {}

    public Orderer(MemberId memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Orderer orderer = (Orderer) o;
        return Objects.equals(memberId, orderer.memberId) && Objects.equals(name, orderer.name);
    }
}
