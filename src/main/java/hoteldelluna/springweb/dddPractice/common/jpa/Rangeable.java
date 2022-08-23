package hoteldelluna.springweb.dddPractice.common.jpa;

import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
public class Rangeable {
    private int start;
    private int limit;
    private Sort sort;

    public Rangeable(int start, int limit, Sort sort) {
        this.start = start;
        this.limit = limit;
        this.sort = sort;
    }

    public static Rangeable of(int start, int limit) {
        return new Rangeable(start, limit, Sort.unsorted());
    }
}
