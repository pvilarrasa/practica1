package cat.urv.deim.padm.comm.persistence;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Tag implements Serializable {

    @ColumnInfo
    String description;
    @PrimaryKey
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
