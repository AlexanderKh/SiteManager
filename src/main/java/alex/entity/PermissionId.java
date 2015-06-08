package alex.entity;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Embeddable
public class PermissionId implements Serializable {
    @GeneratedValue
    private int page;
    @GeneratedValue
    private int user;
}
