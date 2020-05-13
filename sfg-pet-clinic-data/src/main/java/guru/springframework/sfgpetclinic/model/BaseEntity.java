package guru.springframework.sfgpetclinic.model;

import java.io.Serializable;

/**
 * Simple JavaBean domain object with and id property. Used as a baseclass for objects that need this property.
 * @author MHW
 */
public class BaseEntity implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }
}
