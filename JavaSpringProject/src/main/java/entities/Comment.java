package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "Comment")
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "creationdate", nullable = false)
    private Date creationDate;
    @ManyToOne
    @JoinColumn(name = "taskid", referencedColumnName = "id")
    private Task task;

    public Comment(long id, String content, String author, Date date, Task task) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.creationDate = date;
        this.task = task;
    }
}