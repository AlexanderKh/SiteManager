package alex.entity;

import javax.jws.soap.SOAPBinding;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Permission {
    @Id
    @ManyToOne
    private Page page;
    @Id
    @ManyToOne
    private User user;
    private PermissionType type;

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
}
