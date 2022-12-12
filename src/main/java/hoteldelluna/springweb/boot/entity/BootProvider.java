package hoteldelluna.springweb.boot.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "boot_provider")
public class BootProvider extends BaseEntity{
    //공급체 이름. 상품과 1대 N관계
    public BootProvider() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //mappedby -> 상대 테이블 필드명이야 , 컬럼명 x
    @OneToMany(mappedBy = "provider" , fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<BootProduct> productList = new ArrayList<>();
}
