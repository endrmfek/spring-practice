package hoteldelluna.springweb.dddPractice.order.common.domain;

import hoteldelluna.springweb.dddPractice.member.common.domain.MemberId;

public interface OrdererService {
    Orderer createOrderer(MemberId ordererMemberId);
}
