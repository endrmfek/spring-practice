package hoteldelluna.springweb.dddPractice.board.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "article")
@SecondaryTable(
        name="article_content",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "id")
)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @AttributeOverrides({
            @AttributeOverride(
                    name = "content",
                    column = @Column(table = "article_content" , name="content")),
            @AttributeOverride(
                    name = "contentType",
                    column = @Column(table="article_content", name="content_type")
            )
    })
    @Embedded
    private ArticleContent content;

    protected Article() {}

    public Article(String title, ArticleContent content) {
        this.title = title;
        this.content = content;
    }
}
