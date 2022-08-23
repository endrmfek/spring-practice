package hoteldelluna.springweb.dddPractice.order.infra;

import hoteldelluna.springweb.dddPractice.order.command.application.RefundService;
import hoteldelluna.springweb.dddPractice.order.command.domain.OrderCanceledEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class OrderCanceledEventHandler {
    private RefundService refundService; //이거 구현체 어딨어?

    public OrderCanceledEventHandler(RefundService refundService) {
        this.refundService = refundService;
    }

    @Async
    @TransactionalEventListener(
            classes = OrderCanceledEvent.class,
            phase = TransactionPhase.AFTER_COMMIT
    )
    public void handle(OrderCanceledEvent event) {
        refundService.refund(event.getOrderNumber());
    }

}
