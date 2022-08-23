package hoteldelluna.springweb.dddPractice.member.command.domain;

import hoteldelluna.springweb.dddPractice.common.event.Events;
import hoteldelluna.springweb.dddPractice.common.jpa.EmailSetConverter;
import hoteldelluna.springweb.dddPractice.common.model.Email;
import hoteldelluna.springweb.dddPractice.common.model.EmailSet;
import lombok.Getter;

import javax.persistence.*;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name="d_member")
@Getter
public class Member {

    @EmbeddedId
    private MemberId id;

    private String name;

    @Embedded
    private Password password;

    private boolean blocked; //?

    @Column(name = "emails")
    @Convert(converter = EmailSetConverter.class)
    private EmailSet emails;

    protected Member() {}

    public Member(MemberId id, String name) {
        this.id = id;
        this.name = name;
    }


    //
    public void initializePassword() { //비밀번호 초기화
        String newPassword = generateRandomPassword();
        this.password = new Password(newPassword);
        Events.raise(new PasswordChangedEvent(id.getId() , newPassword));
    }

    public String generateRandomPassword() {
        Random random = new Random();
        int number = random.nextInt();
        return Integer.toHexString(number);
    }

    public void changeEmails(Set<Email> emails) {
        this.emails = new EmailSet(emails);
    }

    public void block() {
        this.blocked = true;
        Events.raise(new MemberBlockedEvent(id.getId()));
    }

    public void unblock() {
        this.blocked = false;
        Events.raise(new MemberUnblockedEvent(id.getId()));
    }

    public void changePassword(String oldPw, String newPw) {
        if(!password.match(oldPw)) {
            throw new IdPasswordNotMatchingException();
        }
        this.password = new Password(newPw);
        Events.raise(new PasswordChangedEvent(id.getId(), newPw));
    }

    public boolean isBlocked() {
        return blocked;
    }

}
