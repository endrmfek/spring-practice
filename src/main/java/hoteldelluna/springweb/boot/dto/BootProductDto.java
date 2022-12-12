package hoteldelluna.springweb.boot.dto;

import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.Target;

@Getter
@Setter
public class BootProductDto {

    //왜 dto에 id 없습니까? ->
    private String name;
    private int price;
    private int stock;

    public BootProductDto() {
    }

    public BootProductDto(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
