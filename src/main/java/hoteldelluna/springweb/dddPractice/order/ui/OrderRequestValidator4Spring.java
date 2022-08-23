package hoteldelluna.springweb.dddPractice.order.ui;

import hoteldelluna.springweb.dddPractice.order.command.application.OrderProduct;
import hoteldelluna.springweb.dddPractice.order.command.application.OrderRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class OrderRequestValidator4Spring implements Validator {
    /*
    Class.isAssignableFrom은 특정 Class가,
    특정 Class, Interface를 상속하거나 구현 했는지를,
    boolean type으로 return해주는 method이다.
    */
    @Override
    public boolean supports(Class<?> clazz) {
        return OrderRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        OrderRequest orderReq = (OrderRequest) o;
        if(orderReq.getOrderProducts() == null || orderReq.getOrderProducts().isEmpty()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderProducts" , "required");
        } else {
            for(int i=0; i<orderReq.getOrderProducts().size(); i++) {
                OrderProduct orderProduct = orderReq.getOrderProducts().get(i);
                if(orderProduct.getProductId() == null || orderProduct.getProductId().trim().isEmpty()) {
                    errors.rejectValue("orderProducts[" + i + "].productId" , "required");
                }
                if(orderProduct.getQuantity() <= 0) {
                    errors.rejectValue("orderProducts[" + i + "].quantity" , "nonPositive");
                }
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.receiver.name" , "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.receiver.phone" , "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.address.zipCode" , "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.address.address1" , "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.address.address2" , "required");

    }


}
