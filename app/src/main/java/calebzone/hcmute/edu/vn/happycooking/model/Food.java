package calebzone.hcmute.edu.vn.happycooking.model;

public class Food {
    private String title;
    private String type;
    private int image;

    public Food(String title, String type, int image) {
        this.title = title;
        this.type = type;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
