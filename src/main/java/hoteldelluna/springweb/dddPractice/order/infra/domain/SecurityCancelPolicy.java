package hoteldelluna.springweb.dddPractice.order.infra.domain;

import hoteldelluna.springweb.dddPractice.order.command.domain.CancelPolicy;
import hoteldelluna.springweb.dddPractice.order.command.domain.Canceller;
import hoteldelluna.springweb.dddPractice.order.command.domain.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SecurityCancelPolicy implements CancelPolicy { //구현체.

    @Override
    public boolean hasCancellationPermission(Order order, Canceller canceller) { //취소 인가 가지고있냐?
        return isCancellerOrderer(order, canceller) || isCurrentUserAdminRole();
    }

    private boolean isCurrentUserAdminRole() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) return false;
        Authentication authentication = context.getAuthentication();
        if (authentication == null) return false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities == null) return false;
        return authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }

    private boolean isCancellerOrderer(Order order, Canceller canceller) { //주문한사람이랑 취소한사람이랑 일치하는지 확인.
        return order.getOrderer().getMemberId().getId().equals(canceller.getMemberId());
    }



}
