package service;

import dao.CategoryListModel;
import dao.Channel;
import dao.Post;

import java.util.List;
import java.util.Map;

public interface PostService {

    public Channel getChannel(String slug);
    public Post getPost(int id);
    public CategoryListModel getCategoryList(String slug, String currentSelected);
    public List<SimplifiedPostInfo> getPostListData(Map<String, String> listparam);
}
