package hoteldelluna.springweb.jpaShop.entity;

import hoteldelluna.springweb.jpaShop.constant.Role;
import hoteldelluna.springweb.jpaShop.dto.ShopMemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="shop_member")
@Getter
@Setter // 쓰지말자.. 진짜
@ToString
public class ShopMember extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static ShopMember createMember(ShopMemberFormDto memberFormDto , PasswordEncoder passwordEncoder) {
        ShopMember member = new ShopMember();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;
    }
}
