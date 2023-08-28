package test;

import dao.Category;
import dao.Channel;
import dao.ChannelDaoJdbc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoTestConfig.class})
public class ChannelDaoTest {
    Channel channel1;
    Channel channel2;
    Channel channel3;

    Category category1_1;
    Category category1_2;
    Category category2_1;
    Category category2_2;
    Category category3_1;
    Category category3_2;

    @Autowired
    ApplicationContext ctx;

    private class TestChannelDaoJdbc extends ChannelDaoJdbc{

        TestChannelDaoJdbc(DataSource dataSource){
            super(dataSource);
        }

        public void deleteAllChannel(){
            jdbcTemplate.update("delete from channel");
        }
        public void deleteAllCategory(){
            jdbcTemplate.update("delete from category");
        }
        public int getChannelCount(){
            Integer i = jdbcTemplate.queryForObject("select count(*) from channel", Integer.class);

            if(i == null)
                return -1;
            else
                return i;
        }
        public int getCategoryCount(String slug){
            Integer i = jdbcTemplate.queryForObject("select count(*) from category where channel_slug = ?",
                    new Object[]{slug}, Integer.class);
            if(i == null)
                return -1;
            else
                return i;
        }
    }
    TestChannelDaoJdbc testChannelDao;

    @Before
    public void setUp(){

        testChannelDao = new TestChannelDaoJdbc(ctx.getBean(DataSource.class));

        channel1 = makeTestChannel("computer", 1);
        channel2 = makeTestChannel("food", 2);
        channel3 = makeTestChannel("guitar", 3);

        category1_1 = makeTestCategory("computer", "글머리1");
        category1_2 = makeTestCategory("computer", "글머리2");

        category2_1 = makeTestCategory("food", "글머리1");
        category2_2 = makeTestCategory("food", "글머리2");

        category3_1 = makeTestCategory("guitar", "글머리1");
        category3_2 = makeTestCategory("guitar", "글머리2");


    }

    private Channel makeTestChannel(String name, int number){
        Channel channel = new Channel();
        channel.setName(name);
        channel.setSlug(name+"_slug");
        channel.setMinRecommend(number);
        channel.setIconImage("icon_file_name");
        channel.setDefaultImage("default_image_name");
        channel.setIntroduction("An introduction to channel"+ name);
        return channel;
    }

    private Category makeTestCategory(String slug, String id){
        Category category = new Category();
        category.setId(id);
        category.setChannel(slug);
        category.setName(slug + "_" + id);
        category.setNoBest(false);
        category.setNoBestChannel(false);
        category.setNoRateDown(false);
        category.setNoDelete(false);
        category.setMinRecommend(10);
        return category;
    }

    private void assertChannelEqual(Channel channel1, Channel channel2){
        assertTrue(channel1.getName().equals(channel2.getName()) && channel1.getSlug().equals(channel2.getSlug())
                && channel1.getMinRecommend() == channel2.getMinRecommend() && channel1.getIconImage().equals(channel2.getIconImage())
                && channel1.getDefaultImage().equals(channel2.getDefaultImage()) && channel1.getIntroduction().equals(channel2.getIntroduction()));
    }

    private void assertCategoryEqual(Category category1, Category category2){
        assertTrue(category1.getId().equals(category2.getId()) && category1.getChannel().equals(category2.getChannel())
                && category1.getName().equals(category2.getName()) && category1.isNoBest() == category2.isNoBest()
                && category1.isNoBestChannel() == category2.isNoBestChannel() && category1.isNoRateDown() == category2.isNoRateDown()
                && category1.isNoDelete() == category2.isNoDelete() && category1.getMinRecommend() == category2.getMinRecommend());
    }

    private void resetDB(){
        testChannelDao.deleteAllChannel();
        testChannelDao.deleteAllCategory();
        assertThat(testChannelDao.getChannelCount(), is(0));
        assertThat(testChannelDao.getCategoryCount(channel1.getSlug()), is(0));
        assertThat(testChannelDao.getCategoryCount(channel2.getSlug()), is(0));
        assertThat(testChannelDao.getCategoryCount(channel3.getSlug()), is(0));
    }

    @Test
    public void addGetChannel(){
        resetDB();
        testChannelDao.addChannel(channel1);
        assertThat(testChannelDao.getChannelCount(), is(1));
        testChannelDao.addChannel(channel2);
        testChannelDao.addChannel(channel3);
        assertThat(testChannelDao.getChannelCount(), is(3));

        assertChannelEqual(testChannelDao.getChannel(channel1.getSlug()), channel1);
        assertChannelEqual(testChannelDao.getChannel(channel2.getSlug()), channel2);
        assertChannelEqual(testChannelDao.getChannel(channel3.getSlug()), channel3);
    }

    @Test
    public void getCategoryNameList(){
        resetDB();
        testChannelDao.addCategory(category1_1);
        testChannelDao.addCategory(category1_2);
        List<String> getResult = testChannelDao.getCategoryNames(category1_1.getChannel());
        assertThat(getResult.get(0), is(category1_1.getName()));
        assertThat(getResult.get(1), is(category1_2.getName()));
    }

    @Test
    public void addGetCategory(){
        resetDB();
        testChannelDao.addCategory(category1_1);
        testChannelDao.addCategory(category1_2);
        assertThat(testChannelDao.getCategoryCount(category1_1.getChannel()), is(2));
        testChannelDao.addCategory(category2_1);
        assertThat(testChannelDao.getCategoryCount(category2_1.getChannel()), is(1));
        testChannelDao.addCategory(category2_2);
        assertThat(testChannelDao.getCategoryCount(category2_1.getChannel()), is(2));
        testChannelDao.addCategory(category3_1);
        testChannelDao.addCategory(category3_2);
        assertThat(testChannelDao.getCategoryCount(category3_1.getChannel()), is(2));

        List<Category> getResult = testChannelDao.getCategory(category1_1.getChannel());
        assertThat(getResult.size(), is(2));
        assertCategoryEqual(getResult.get(0), category1_1);
        assertCategoryEqual(getResult.get(1), category1_2);

        getResult = testChannelDao.getCategory(category3_1.getChannel());
        assertThat(getResult.size(), is(2));
        assertCategoryEqual(getResult.get(0), category3_1);
        assertCategoryEqual(getResult.get(1), category3_2);

        resetDB();

        ArrayList<Category> categoryList = new ArrayList<Category>();
        categoryList.add(category1_1);
        categoryList.add(category2_1);
        categoryList.add(category3_1);

        testChannelDao.addCategory(categoryList);
        assertThat(testChannelDao.getCategoryCount(category1_1.getChannel()), is(1));
        assertThat(testChannelDao.getCategoryCount(category2_1.getChannel()), is(1));
        assertThat(testChannelDao.getCategoryCount(category3_1.getChannel()), is(1));
    }

    @Test
    @DirtiesContext
    public void updateCategory(){

        resetDB();

        category1_1.setName("CHANGED_NAME_1");
        testChannelDao.addCategory(category1_1);

        testChannelDao.updateCategory(category1_1);
        List<Category> getResult = testChannelDao.getCategory(category1_1.getChannel());

        assertThat(getResult.size(), is(1));
        assertThat(getResult.get(0).getName(), is("CHANGED_NAME_1"));

        testChannelDao.addCategory(category1_2);
        testChannelDao.updateCategory(category1_1);
        testChannelDao.updateCategory(category1_2);

        getResult = testChannelDao.getCategory(category1_1.getChannel());
        assertThat(getResult.size(), is(2));
        assertCategoryEqual(getResult.get(1), category1_2);
    }

    @Test
    public void channelExist(){
        resetDB();
        assertFalse(testChannelDao.channelExist(channel1.getSlug()));
        testChannelDao.addChannel(channel1);
        assertTrue(testChannelDao.channelExist(channel1.getSlug()));

        testChannelDao.addChannel(channel2);
        testChannelDao.addChannel(channel3);
        assertTrue(testChannelDao.channelExist(channel2.getSlug()));
        assertTrue(testChannelDao.channelExist(channel3.getSlug()));

        assertFalse(testChannelDao.channelExist("NO_EXIST_CHANNEL"));
    }

    @Test
    @DirtiesContext
    public void updateChannel(){
        resetDB();

        testChannelDao.addChannel(channel1);
        testChannelDao.addChannel(channel2);
        channel1.setIntroduction("CHANGED_INTRODUCTION");

        testChannelDao.updateChannel(channel1);

        Channel getResult = testChannelDao.getChannel(channel1.getSlug());
        assertThat(getResult.getIntroduction(), is("CHANGED_INTRODUCTION"));
        assertThat(getResult.getName(), is(channel1.getName()));

        getResult = testChannelDao.getChannel(channel2.getSlug());
        assertChannelEqual(channel2, getResult);
    }

    @Test
    @DirtiesContext
    public void updateOrAddCategory(){

        resetDB();

        ArrayList<Category> originalContent = new ArrayList<Category>();
        originalContent.add(category1_1);
        originalContent.add(category1_2);
        testChannelDao.addCategory(originalContent);

        ArrayList<Category> updateContent = new ArrayList<Category>();
        category1_1.setName("CHANGED_CONTENT");
        Category category1_3 = makeTestCategory(category1_1.getChannel(), "글머리3");
        updateContent.add(category1_1);
        updateContent.add(category1_2);
        updateContent.add(category1_3);
        testChannelDao.updateAddCategory(updateContent);

        List<Category> getResult = testChannelDao.getCategory(category1_1.getChannel());

        assertThat(getResult.get(0).getChannel(), is(originalContent.get(0).getChannel()));
        assertThat(getResult.get(0).getName(), is("CHANGED_CONTENT"));

        assertThat(getResult.size(), is(3));
        assertCategoryEqual(getResult.get(2), category1_3);
    }
}
