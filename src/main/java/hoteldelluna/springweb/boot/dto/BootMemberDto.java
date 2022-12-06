package hoteldelluna.springweb.boot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BootMemberDto {
    private String name;
    private String email;
    private String organization;

    //빈 생성자 없으면 매핑할때 오류나옴..
    public BootMemberDto() {}

    public BootMemberDto(String name, String email, String organization) {
        this.name = name;
        this.email = email;
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }
}
