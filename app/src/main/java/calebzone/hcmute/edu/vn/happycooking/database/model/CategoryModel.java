package calebzone.hcmute.edu.vn.happycooking.database.model;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";

    private long id;
    private String name;

    public CategoryModel(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
