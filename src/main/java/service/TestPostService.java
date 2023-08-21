package service;

import dao.CategoryList;
import dao.Channel;
import dao.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static test.ChannelDaoTest.makeTestChannel;
import static test.PostDaoTest.makeTestPost;

public class TestPostService implements PostService{
    @Override
    public Channel getChannel(String slug) {
        return makeTestChannel(1, slug + " channel");
    }

    @Override
    public Post getPost(int id) {
        return makeTestPost(id, "nickname");
    }

    @Override
    public CategoryList getCategoryList(String slug, String currentSelected) {
        List<String> nameList = new ArrayList<String>();
        nameList.add("공지");
        nameList.add("잡담");
        nameList.add("정보");
        nameList.add("가나");
        nameList.add("다라");
        nameList.add("마바");
        CategoryList categoryList = new CategoryList(nameList);
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
