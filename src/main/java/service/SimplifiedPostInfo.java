package service;

import java.time.LocalDateTime;

public class SimplifiedPostInfo {
    int id;
    String title;
    String nickname;
    LocalDateTime timeCreated;
    int viewCount;
    int rating;
    int commentCount;

    public SimplifiedPostInfo() {
    }

    public SimplifiedPostInfo(int id, String title, String nickname, LocalDateTime timeCreated, int viewCount, int rating, int commentCount) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.timeCreated = timeCreated;
        this.viewCount = viewCount;
        this.rating = rating;
        this.commentCount = commentCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
