package hoteldelluna.springweb.dddPractice.common;

import lombok.Getter;

@Getter
public class ValidationError {
    private String name;
    private String code;

    public ValidationError(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public boolean hasName() {
        return name != null;
    }

    public static ValidationError of(String code) {
        return new ValidationError(null, code);
    }
    public static ValidationError of(String name, String code) {
        return new ValidationError(name, code);
    }
}
