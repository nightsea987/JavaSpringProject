package dto;

import entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String content;
    private String author;
    private Date creationDate;
    private Long taskId;

    public static CommentDto fromComment(Comment comment) {
        return new CommentDto(comment.getId(), comment.getContent(), comment.getAuthor(), comment.getCreationDate(),
                comment.getTask().getId());
    }

}