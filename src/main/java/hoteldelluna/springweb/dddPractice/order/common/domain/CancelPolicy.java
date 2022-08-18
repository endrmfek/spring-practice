package hoteldelluna.springweb.dddPractice.order.common.domain;

public interface CancelPolicy {
    boolean hasCancellationPermission(Order order, Canceller canceller);
}
