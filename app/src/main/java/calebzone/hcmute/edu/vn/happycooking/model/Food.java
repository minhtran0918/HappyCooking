package calebzone.hcmute.edu.vn.happycooking.model;

public class Food {
    private long id;
    private String title;
    private String type;
    private String urlImage;

    public Food(long id, String title, String type, String urlImage) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.urlImage = urlImage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return urlImage;
    }

    public void setImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
