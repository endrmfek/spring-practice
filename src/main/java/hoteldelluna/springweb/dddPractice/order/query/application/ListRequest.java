package hoteldelluna.springweb.dddPractice.order.query.application;

import lombok.Getter;

@Getter
public class ListRequest {
    private int page;
    private int size;

    public ListRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
