package alex.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Page {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private User author;
    private String content;
    private String title;
    @OneToMany(mappedBy = "page")
    private List<Permission> permissions;

    public Page(){}

    public Page(String title, User author){
        this.title = title;
        this.author = author;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
