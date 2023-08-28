package dao;

public class Channel {

    String name;
    String slug;
    int minRecommend;
    String iconImage;
    String defaultImage;
    String introduction;

    public Channel(){

    }

    public Channel(String name, String slug, int min_recommend, String icon_image, String default_image, String introduction) {
        this.name = name;
        this.slug = slug;
        this.minRecommend = min_recommend;
        this.iconImage = icon_image;
        this.defaultImage = default_image;
        this.introduction = introduction;
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

    public int getMinRecommend() {
        return minRecommend;
    }

    public void setMinRecommend(int minRecommend) {
        this.minRecommend = minRecommend;
    }

    public String getIconImage() {
        return iconImage;
    }

    public void setIconImage(String iconImage) {
        this.iconImage = iconImage;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
