package hoteldelluna.springweb.dddPractice.order.command.domain;

public interface CancelPolicy {
    boolean hasCancellationPermission(Order order, Canceller canceller);
}
