package hoteldelluna.springweb.dddPractice.order.infra.domain;

import hoteldelluna.springweb.dddPractice.order.command.domain.CancelPolicy;
import hoteldelluna.springweb.dddPractice.order.command.domain.Canceller;
import hoteldelluna.springweb.dddPractice.order.command.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class SecurityCancelPolicy implements CancelPolicy { //구현체.

    @Override
    public boolean hasCancellationPermission(Order order, Canceller canceller) { //취소 인가 가지고있냐?
//        return isCancellerOrderer(order, canceller) || isCurrentUserAdminRole(); // 시큐리티 구현할때
        return isCancellerOrderer(order, canceller);
    }

//    private boolean isCurrentUserAdminRole() {
//    }

    private boolean isCancellerOrderer(Order order, Canceller canceller) { //주문한사람이랑 취소한사람이랑 일치하는지 확인.
        return order.getOrderer().getMemberId().getId().equals(canceller.getMemberId());
    }



}
