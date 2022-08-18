package hoteldelluna.springweb.dddPractice.eventstore.api;

public class PayloadConvertException extends RuntimeException{
    public PayloadConvertException(Exception e) {
        super(e);
    }
}
