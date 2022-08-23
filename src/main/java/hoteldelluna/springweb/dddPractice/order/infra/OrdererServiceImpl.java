package hoteldelluna.springweb.dddPractice.order.infra;

import hoteldelluna.springweb.dddPractice.member.command.domain.MemberId;
import hoteldelluna.springweb.dddPractice.member.query.MemberData;
import hoteldelluna.springweb.dddPractice.member.query.MemberQueryService;
import hoteldelluna.springweb.dddPractice.order.command.domain.Orderer;
import hoteldelluna.springweb.dddPractice.order.command.domain.OrdererService;
import org.springframework.stereotype.Service;

@Service
public class OrdererServiceImpl implements OrdererService {
    private MemberQueryService memberQueryService;

    public OrdererServiceImpl(MemberQueryService memberQueryService) {
        this.memberQueryService = memberQueryService;
    }

    @Override
    public Orderer createOrderer(MemberId ordererMemberId) {
        MemberData memberData = memberQueryService.getMemberData(ordererMemberId.getId());
        return new Orderer(MemberId.of(memberData.getId()) , memberData.getName());
    }
}
