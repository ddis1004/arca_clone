package test;

import configuration.ControllerConfig;
import dao.Post;
import dao.PostDaoJdbc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoTestConfig.class})
public class PostDaoTest {

    @Autowired
    PostDaoJdbc postDao;

    Post post1;
    Post post2;
    Post post3;

    @Before
    public void setup(){
        post1 = makeTestPost(1, "TEST_NICKNAME_1");
        post2 = makeTestPost(2, "TEST_NICKNAME_2");
        post3 = makeTestPost(3, "TEST_NICKNAME_3");
    }

    private Post makeTestPost(int number, String nickname){
        Post post = new Post();
        post.setId(number);
        post.setTimeCreated(LocalDateTime.now());
        post.setUserNickname(nickname);
        post.setTitle("TEST_TITLE_" + number);
        post.setContent("<h1>TEST_CONTENT_" + number + "<h1>");
        post.setUserNumber(UUID.randomUUID());
        post.setRateUp(0);
        post.setRateDown(0);
        post.setViewCount(0);
        post.setBest(false);
        post.setCategoryId(0);
        return post;
    }

    private void resetDB(){
        postDao.deleteAll();
        assertThat(postDao.getCount(), is(0));
    }

    private void insertTestData(){
        postDao.add(post1);
        postDao.add(post2);
        postDao.add(post3);
        assertThat(postDao.getCount(), is(3));
    }

    public boolean isPostEqual(Post post1, Post post2){
        if(post1.getId() == post2.getId() && post1.getCategoryId() == post2.getCategoryId()
                && post1.getRateUp() == post2.getRateUp() && post1.getRateDown() == post2. getRateDown()
                && post1.getViewCount() == post2.getViewCount() && post1.isBest() == post2.isBest()
                && post1.getCategoryId() == post2.getCategoryId()
                && post1.getUserNickname().equals(post2.getUserNickname())
                && post1.getContent().equals(post2.getContent())
                && post1.getTitle().equals(post2.getTitle()))
            return true;
        else
            return false;
    }

    @Test
    public void basicInsertGetTest(){

        resetDB();
        insertTestData();


        assertThat(postDao.getCount(), is(3));

        List<Post> getAllResult = postDao.getAll();
        assertThat(getAllResult.size(), is(3));
        assertTrue(isPostEqual(post1, getAllResult.get(0)));
        assertTrue(isPostEqual(post2, getAllResult.get(1)));
        assertTrue(isPostEqual(post3, getAllResult.get(2)));
    }

    @Test
    public void searchByUserTest(){

        resetDB();
        insertTestData();


        Post post4 = makeTestPost(4, "TEST_NICKNAME_2");
        Post post5 = makeTestPost(5, "TEST_NICKNAME_2");
        Post post6 = makeTestPost(6, "TEST_NICKNAME_3");

        postDao.add(post4);
        postDao.add(post5);
        postDao.add(post6);

        assertThat(postDao.getCount(), is(6));

        List<Post> searchResult;
        searchResult = postDao.searchByUser("TEST_NICKNAME_1");
        assertThat(searchResult.size(), is(1));
        assertTrue(isPostEqual(post1, searchResult.get(0)));

        searchResult = postDao.searchByUser("TEST_NICKNAME_2");
        assertThat(searchResult.size(), is(3));
        assertTrue(isPostEqual(post2, searchResult.get(0)));
        assertTrue(isPostEqual(post4, searchResult.get(1)));
        assertTrue(isPostEqual(post5, searchResult.get(2)));

        searchResult = postDao.searchByUser("TEST_NICKNAME_3");
        assertThat(searchResult.size(), is(2));
        assertTrue(isPostEqual(post3, searchResult.get(0)));
        assertTrue(isPostEqual(post6, searchResult.get(1)));

    }

    @Test
    public void deleteTest(){
        resetDB();
        insertTestData();

        postDao.deletePost(2);

        List<Post> getResult = postDao.getAll();
        assertThat(getResult.size(), is(2));
        assertTrue(isPostEqual(post1, getResult.get(0)));
        assertTrue(isPostEqual(post3, getResult.get(1)));

        postDao.deletePost(3);
        getResult = postDao.getAll();
        assertThat(getResult.size(), is(1));
        assertTrue(isPostEqual(post1, getResult.get(0)));

        postDao.deletePost(1);
        getResult = postDao.getAll();
        assertThat(getResult.size(), is(0));
    }

    @Test
    public void updateTest(){
        resetDB();
        insertTestData();

        Post editPost = makeTestPost(1, post1.getUserNickname());
        editPost.setTitle("CHANGED_TITLE");

        postDao.updatePost(editPost);
        Post searchResult = postDao.searchById(1);

        assertFalse(isPostEqual(post1, searchResult));

        assertThat(post1.getContent(), is(searchResult.getContent()));

        assertThat(editPost.getTitle(), is("CHANGED_TITLE"));

    }
}
