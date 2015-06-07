package alex.entity;

import javax.persistence.*;

@Entity
public class Page {
    @Id
    private int id;
    @ManyToOne
    private User author;
    private String content;
    @Enumerated(value = EnumType.STRING)
    private Permission permission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
