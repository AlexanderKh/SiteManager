package alex.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Page {
    @Id
    @GeneratedValue
    private int id;
    private String content;
    private String title;

    public Page(){}

    public Page(String title){
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Page page = (Page) o;

        if (content != null ? !content.equals(page.content) : page.content != null) return false;
        return !(title != null ? !title.equals(page.title) : page.title != null);

    }

    @Override
    public int hashCode() {
        int result = content != null ? content.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString(){
        return title;
    }
}
