package hoteldelluna.springweb.dddPractice.member.query;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="d_member")
@Getter
public class MemberData {

    @Id
    @Column(name="member_id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="blocked")
    private boolean blocked;

    protected MemberData() {}

    public MemberData(String id, String name, boolean blocked) {
        this.id = id;
        this.name = name;
        this.blocked = blocked;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isBlocked() {
        return blocked;
    }

}
