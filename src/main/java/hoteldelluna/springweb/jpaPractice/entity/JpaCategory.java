package hoteldelluna.springweb.jpaPractice.entity;

import hoteldelluna.springweb.jpaPractice.entity.item.JpaItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class JpaCategory {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "jpa_category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<JpaItem> jpaItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private JpaCategory parent;

    @OneToMany(mappedBy = "parent")
    private List<JpaCategory> child = new ArrayList<>();

    //연관관계 메서드
    public void addChildCategory(JpaCategory child) {
        this.child.add(child);
        child.setParent(this);
    }
}
