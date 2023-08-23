package service;

import dao.CategoryList;
import dao.Channel;
import dao.Post;

import java.util.List;
import java.util.Map;

public class PostServiceImpl implements PostService {
    @Override
    public Channel getChannel(String slug) {
        return null;
    }

    @Override
    public Post getPost(int id) {
        return null;
    }

    @Override
    public CategoryList getCategoryList(String slug, String currentSelected) {
        return null;
    }

    @Override
    public List<SimplifiedPostInfo> getPostListData(Map<String, String> listparam) {
        return null;
    }
}
