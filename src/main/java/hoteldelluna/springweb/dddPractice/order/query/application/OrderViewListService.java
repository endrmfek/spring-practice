package hoteldelluna.springweb.dddPractice.order.query.application;

import hoteldelluna.springweb.dddPractice.order.query.dao.OrderSummaryDao;
import hoteldelluna.springweb.dddPractice.order.query.dto.OrderSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

public class OrderViewListService {
    private OrderSummaryDao orderSummaryDao;

    public OrderViewListService(OrderSummaryDao orderSummaryDao) {
        this.orderSummaryDao = orderSummaryDao;
    }

    @Transactional
    public Page<OrderSummary> getList(ListRequest listRequest) {
        Pageable pageable = PageRequest.of(
                listRequest.getPage(),
                listRequest.getSize(),
                Sort.by(Sort.Direction.DESC , "number")
        );
        return orderSummaryDao.findAll(pageable);
    }

}
