package hoteldelluna.springweb.dddPractice.order.command.application;

import lombok.Getter;

@Getter
public class NoOrderProductException extends RuntimeException {
    private String productId;

    public NoOrderProductException(String productId) {
        this.productId = productId;
    }
}
