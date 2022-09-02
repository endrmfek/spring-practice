package hoteldelluna.springweb.jpaShop.Repository;

import hoteldelluna.springweb.jpaShop.entity.ShopMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopMemberRepository extends JpaRepository<ShopMember , Long> {
    ShopMember findByEmail(String email);

}
