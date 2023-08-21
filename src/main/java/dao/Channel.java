package dao;

public class Channel {
    int id;
    String name;
    String slug;
    int min_recommend;
    String icon_image;
    String default_image;
    String introduction;

    public Channel(){

    }

    public Channel(int id, String name, String slug, int min_recommend, String icon_image, String default_image, String introduction) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.min_recommend = min_recommend;
        this.icon_image = icon_image;
        this.default_image = default_image;
        this.introduction = introduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getMin_recommend() {
        return min_recommend;
    }

    public void setMin_recommend(int min_recommend) {
        this.min_recommend = min_recommend;
    }

    public String getIcon_image() {
        return icon_image;
    }

    public void setIcon_image(String icon_image) {
        this.icon_image = icon_image;
    }

    public String getDefault_image() {
        return default_image;
    }

    public void setDefault_image(String default_image) {
        this.default_image = default_image;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
