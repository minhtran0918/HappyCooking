package calebzone.hcmute.edu.vn.happycooking.database.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeModel implements Serializable {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CATEGORY_ID = "cat_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_INTRO = "intro";
    public static final String COLUMN_INGREDIENT = "ingredient";
    public static final String COLUMN_INSTRUCTION = "instruction";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_LINK = "link";
    public static final String COLUMN_FAVORITE = "favorite";

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("intro")
    @Expose
    private String intro;
    @SerializedName("ingredient")
    @Expose
    private Object ingredient;
    @SerializedName("instruction")
    @Expose
    private String instruction;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("link")
    @Expose
    private Object link;
    @SerializedName("favorite")
    @Expose
    private String favorite;

    public RecipeModel(String id, String cat_id, String name, String intro, String ingredient, String instruction, String image, String link, String favorite) {
        this.id = id;
        catId = cat_id;
        this.name = name;
        this.intro = intro;
        this.ingredient = ingredient;
        this.instruction = instruction;
        this.image = image;
        this.link = link;
        this.favorite = favorite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
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

    public Object getIngredient() {
        if (ingredient == null) {
            ingredient = "";
        }
        return ingredient;
    }

    public void setIngredient(Object ingredient) {
        this.ingredient = ingredient;
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

    public Object getLink() {
        return link;
    }

    public void setLink(Object link) {
        this.link = link;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
}