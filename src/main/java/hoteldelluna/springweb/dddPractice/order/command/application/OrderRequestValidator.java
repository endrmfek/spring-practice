package hoteldelluna.springweb.dddPractice.order.command.application;

import hoteldelluna.springweb.dddPractice.common.ValidationError;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderRequestValidator { //직접 검증?..
    public List<ValidationError> validate(OrderRequest orderRequest) { //요청 받고 검증 시작.

        List<ValidationError> errors = new ArrayList<>(); // 오류 담을 그릇

        if (orderRequest == null) {
            errors.add(ValidationError.of("required"));
        } else {
            if (orderRequest.getOrdererMemberId() == null)
                errors.add(ValidationError.of("ordererMemberId", "required"));
            if (orderRequest.getOrderProducts() == null) //널이거나
                errors.add(ValidationError.of("orderProducts", "required"));
            if (orderRequest.getOrderProducts().isEmpty()) //비어있거나?
                errors.add(ValidationError.of("orderProducts", "required"));

            if (orderRequest.getShippingInfo() == null) { //배송정보에 관해
                errors.add(ValidationError.of("shippingInfo", "required"));
            } else {
                if (orderRequest.getShippingInfo().getReceiver() == null) { // 받는사람에 대해
                    errors.add(ValidationError.of("shippingInfo.receiver", "required"));
                } else {
                    if (!StringUtils.hasText(orderRequest.getShippingInfo().getReceiver().getName())) {
                        errors.add(ValidationError.of("shippingInfo.receiver.name", "required"));
                    }
                    if (!StringUtils.hasText(orderRequest.getShippingInfo().getReceiver().getPhone())) {
                        errors.add(ValidationError.of("shippingInfo.receiver.phone", "required"));
                    }
                    if (orderRequest.getShippingInfo().getAddress() == null) { // 주소에 대해
                        errors.add(ValidationError.of("shippingInfo.address", "required"));
                    } else {
                        if (!StringUtils.hasText(orderRequest.getShippingInfo().getAddress().getZipCode())) {
                            errors.add(ValidationError.of("shippingInfo.address.zipCode", "required"));
                        }
                        if (!StringUtils.hasText(orderRequest.getShippingInfo().getAddress().getAddress1())) {
                            errors.add(ValidationError.of("shippingInfo.address.address1", "required"));
                        }
                        if (!StringUtils.hasText(orderRequest.getShippingInfo().getAddress().getAddress2())) {
                            errors.add(ValidationError.of("shippingInfo.address.address2", "required"));
                        }
                    }
                }
            }
        }
        return errors;
    }
}