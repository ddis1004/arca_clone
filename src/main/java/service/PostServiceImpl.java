package service;

import dao.*;

import java.util.List;
import java.util.Map;

public class PostServiceImpl implements PostService {

    PostDao postDao;
    ChannelDao channelDao;

    @Override
    public Channel getChannel(String slug) {
        return channelDao.getChannel(slug);
    }

    @Override
    public Post getPost(int id) {
        return postDao.searchById(id);
    }

    @Override
    public CategoryListModel getCategoryList(String slug, String currentSelected) {

        List<String> nameList = channelDao.getCategoryNames(slug);
        nameList.add(0, "전체");
        CategoryListModel  categoryList = new CategoryListModel(nameList);

        categoryList.setSelected(0);
        if(currentSelected != null){
            for(int i = 0; i < nameList.size(); i++){
                if(nameList.get(i).equals(currentSelected)){
                    categoryList.setSelected(i);
                    break;
                }
            }
        }
        return categoryList;
    }

    @Override
    public List<SimplifiedPostInfo> getPostListData(Map<String, String> listparam) {
        return null;
    }
}
