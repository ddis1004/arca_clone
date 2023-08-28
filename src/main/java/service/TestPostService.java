package service;

import dao.CategoryListModel;
import dao.Channel;
import dao.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import static test.PostDaoTest.makeTestPost;

public class TestPostService implements PostService{
    @Override
    public Channel getChannel(String slug) {
        Channel channel = new Channel();
        channel.setName(slug);
        channel.setSlug(slug+"_slug");
        channel.setMinRecommend(10);
        channel.setIconImage("icon_file_name");
        channel.setDefaultImage("default_image_name");
        channel.setIntroduction("An introduction to channel"+ slug);
        return channel;
    }

    @Override
    public Post getPost(int id) {
        return makeTestPost(id, "nickname");
    }

    @Override
    public CategoryListModel getCategoryList(String slug, String currentSelected) {
        List<String> nameList = new ArrayList<String>();
        nameList.add("공지");
        nameList.add("잡담");
        nameList.add("정보");
        nameList.add("가나");
        nameList.add("다라");
        nameList.add("마바");
        CategoryListModel categoryList = new CategoryListModel(nameList);
        categoryList.setSelected(3);
        return categoryList;
    }

    @Override
    public List<SimplifiedPostInfo> getPostListData(Map<String, String> listparam) {
        List<SimplifiedPostInfo> postlist = new ArrayList<SimplifiedPostInfo>();
        for(int i = 0; i < 20; i++){
            postlist.add( new SimplifiedPostInfo(i, "TITLE_"+ i, "NICKNAME_" + i, LocalDateTime.now(), 0,0, i));
        }
        return postlist;
    }
}
