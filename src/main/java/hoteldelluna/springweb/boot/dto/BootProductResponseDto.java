package hoteldelluna.springweb.boot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BootProductResponseDto {
    private Long number;
    private String name;
    private int price;
    private int stock;

    public BootProductResponseDto() {}

    public BootProductResponseDto(long number, String name, int price, int stock) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    //필요에 따라 builder 메서드와 hashcode / equals 메서드 추가할 수 있음.

    public static class BootProductResponseDtoBuilder {
        private Long number;
        private String name;
        private int price;
        private int stock;

        public BootProductResponseDtoBuilder() {
        }

        public BootProductResponseDtoBuilder number(Long number) {
            this.number = number;
            return this;
        }

        public BootProductResponseDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BootProductResponseDtoBuilder price(int price) {
            this.price = price;
            return this;
        }

        public BootProductResponseDtoBuilder stock(int stock) {
            this.stock = stock;
            return this;
        }

        public BootProductResponseDto build() {
            return new BootProductResponseDto(number, name, price, stock);
        }

        @Override
        public String toString() {
            return "BootProductResponseDtoBuilder{" +
                    "number=" + number +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    ", stock=" + stock +
                    '}';
        }
    }
}
