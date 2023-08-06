package dao;


import java.util.List;

public interface PostDao {

    public void add(Post post);
    public Post searchById(int id);
    public List<Post> searchByUser(String nickname);
    public void deletePost(int id);
    public void updatePost(Post post);

}
