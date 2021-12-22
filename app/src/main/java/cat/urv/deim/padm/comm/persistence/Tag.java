package cat.urv.deim.padm.comm.persistence;

import java.io.Serializable;

public class Tag implements Serializable {

    String description;
    String name;

    public Tag(String description, String name) {
        this.description = description;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
}
