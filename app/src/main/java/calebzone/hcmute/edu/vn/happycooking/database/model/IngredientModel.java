package calebzone.hcmute.edu.vn.happycooking.database.model;

import java.io.Serializable;

public class IngredientModel implements Serializable {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_RECIPE_ID = "rec_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_UNIT = "unit";

    private long id;
    private long rec_id;
    private String name;
    private String quantity;
    private String unit;

    public IngredientModel(long id, long rec_id, String name, String quantity, String unit) {
        this.id = id;
        this.rec_id = rec_id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRec_id() {
        return rec_id;
    }

    public void setRec_id(long rec_id) {
        this.rec_id = rec_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
