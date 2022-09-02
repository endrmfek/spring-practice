package hoteldelluna.springweb.jpaShop.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ShopMemberFormDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 8, message = "비밀번호는 최소 8자리입니다.")
    private String password;

    @NotBlank(message = "주소는 필수 입력 값입니다.")
    private String address;
}
