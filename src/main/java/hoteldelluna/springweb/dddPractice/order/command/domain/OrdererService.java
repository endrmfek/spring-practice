package hoteldelluna.springweb.dddPractice.order.command.domain;

import hoteldelluna.springweb.dddPractice.member.command.domain.MemberId;

public interface OrdererService {
    Orderer createOrderer(MemberId ordererMemberId);
}
