package service;

import dao.CategoryList;
import dao.Channel;
import dao.Post;

import java.util.List;
import java.util.Map;

public interface PostService {

    public Channel getChannel(String slug);
    public Post getPost(int id);
    public CategoryList getCategoryList(String slug, String currentSelected);
    public List<SimplifiedPostInfo> getPostListData(Map<String, String> listparam);
}
