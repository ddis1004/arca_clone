package dao;

import java.time.LocalDateTime;
import java.util.UUID;

public class Post {

    int id;
    String userNickname;
    UUID userNumber;
    LocalDateTime timeCreated;
    String title;
    String content;
    int rateUp;
    int rateDown;
    int viewCount;
    boolean best;
    int categoryId;

    public int getId() {
        return id;
    }

    public UUID getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(UUID userNumber) {
        this.userNumber = userNumber;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRateUp() {
        return rateUp;
    }

    public void setRateUp(int rateUp) {
        this.rateUp = rateUp;
    }

    public int getRateDown() {
        return rateDown;
    }

    public void setRateDown(int rateDown) {
        this.rateDown = rateDown;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public boolean isBest() {
        return best;
    }

    public void setBest(boolean best) {
        this.best = best;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
