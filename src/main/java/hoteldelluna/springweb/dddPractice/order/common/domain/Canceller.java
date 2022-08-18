package hoteldelluna.springweb.dddPractice.order.common.domain;

public class Canceller {
    private String memberId;

    public Canceller(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    public static Canceller of(String memberId) {
        return new Canceller(memberId);
    }
}
