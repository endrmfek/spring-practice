package hoteldelluna.springweb.jpaPractice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class JpaDelivery {

    @Id @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(mappedBy = "jpaDelivery" , fetch = FetchType.LAZY)
    private JpaOrder jpaOrder;

    @Embedded
    private JpaAddress jpaAddress;

    @Enumerated(EnumType.STRING)
    private JpaDeliveryStatus status;
}
