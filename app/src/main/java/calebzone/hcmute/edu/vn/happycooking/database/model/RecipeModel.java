package calebzone.hcmute.edu.vn.happycooking.database.model;

import java.io.Serializable;

public class RecipeModel implements Serializable {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CATEGORY_ID = "cat_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_INTRO = "intro";
    public static final String COLUMN_INSTRUCTION = "instruction";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_LINK = "link";
    public static final String COLUMN_FAVORITE = "favorite";


    private long id;
    private long cat_id;
    private String name;
    private String intro;
    private String instruction;
    private String image;
    private String link;
    private int favorite;

    public RecipeModel(long id, long cat_id, String name, String intro, String instruction, String image, String link, int favorite) {
        this.id = id;
        this.cat_id = cat_id;
        this.name = name;
        this.intro = intro;
        this.instruction = instruction;
        this.image = image;
        this.link = link;
        this.favorite = favorite;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCat_id() {
        return cat_id;
    }

    public void setCat_id(long cat_id) {
        this.cat_id = cat_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}
