package hoteldelluna.springweb.boot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BootChangeProductNameDto {
    private Long number;
    private String name;

    public BootChangeProductNameDto() {
    }

    public BootChangeProductNameDto(Long number, String name) {
        this.number = number;
        this.name = name;
    }
}
