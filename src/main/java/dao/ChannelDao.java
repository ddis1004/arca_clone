package dao;

import java.util.List;

public interface ChannelDao {
    public void addChannel(Channel channel);
    public Channel getChannel(String slug);
    public List<Category> getCategory(String slug);
    public void addCategory(Category category);
    public void addCategory(List<Category> category);
    public List<String> getCategoryNames(String slug);
    public void updateCategory(Category category);
    public void updateAddCategory(List<Category> category);
    public void updateChannel(Channel channel);
    public boolean channelExist(String slug);
}
