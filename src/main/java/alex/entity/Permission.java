package alex.entity;

import javax.persistence.*;

@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @ManyToOne
    private Page page;
    @ManyToOne
    private User user;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "PERMISSION_TYPE")
    private PermissionType type;

    public Permission(){}

    public Permission(User user, Page page, PermissionType type){
        this.page = page;
        this.user = user;
        this.type = type;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PermissionType getType() {
        return type;
    }

    public void setType(PermissionType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
