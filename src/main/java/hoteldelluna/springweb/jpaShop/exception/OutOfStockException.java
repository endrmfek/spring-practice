package hoteldelluna.springweb.jpaShop.exception;

/*
* 재고 없을 때 발생하는 예외
* */
public class OutOfStockException extends RuntimeException{
    public OutOfStockException(String message) {
        super(message);
    }
}
