package alex.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Permission {
    @EmbeddedId
    private PermissionId permissionId;
    @ManyToOne
    private Page page;
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

    public PermissionId getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(PermissionId permissionId) {
        this.permissionId = permissionId;
    }
}
