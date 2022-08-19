package hoteldelluna.springweb.dddPractice.member.command.domain;

import hoteldelluna.springweb.dddPractice.common.event.Event;

public class MemberBlockedEvent extends Event {
    private String memberId;

    public MemberBlockedEvent(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }
}
