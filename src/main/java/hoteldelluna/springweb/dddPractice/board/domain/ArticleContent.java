package hoteldelluna.springweb.dddPractice.board.domain;

import lombok.Getter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@Embeddable
@Access(AccessType.FIELD)
public class ArticleContent {
    private String content;
    private String contentType;

    protected ArticleContent() {}

    public ArticleContent(String content, String contentType) {
        this.content = content;
        this.contentType = contentType;
    }
}
