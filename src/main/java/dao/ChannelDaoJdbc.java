package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ChannelDaoJdbc implements ChannelDao {

    protected JdbcTemplate jdbcTemplate;
    protected ChannelRowMapper channelRowMapper;
    protected CategoryRowMapper categoryRowMapper;

    private class ChannelRowMapper implements RowMapper<Channel>{
        @Override
        public Channel mapRow(ResultSet resultSet, int i) throws SQLException {
            Channel channel = new Channel();
            channel.setSlug(resultSet.getString("slug"));
            channel.setName(resultSet.getString("name"));
            channel.setMinRecommend(resultSet.getInt("min_recommend"));
            channel.setIconImage(resultSet.getString("icon_image"));
            channel.setDefaultImage(resultSet.getString("default_image"));
            channel.setIntroduction(resultSet.getString("introduction"));
            return channel;
        }
    }

    private class CategoryRowMapper implements RowMapper<Category>{
        @Override
        public Category mapRow(ResultSet resultSet, int i) throws SQLException {
            Category category = new Category();
            category.setId(resultSet.getString("id"));
            category.setChannel(resultSet.getString("channel_slug"));
            category.setName(resultSet.getString("name"));
            category.setNoBest(resultSet.getInt("no_best") > 0);
            category.setNoBestChannel(resultSet.getInt("no_best_channel") > 0);
            category.setNoRateDown(resultSet.getInt("no_rate_down") > 0);
            category.setNoDelete(resultSet.getInt("no_delete") > 0);
            category.setMinRecommend(resultSet.getInt("min_recommend"));
            return category;
        }
    }

    protected ChannelDaoJdbc(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
        channelRowMapper = new ChannelRowMapper();
        categoryRowMapper = new CategoryRowMapper();
    }


    @Override
    public void addChannel(Channel channel) {
        jdbcTemplate.update("insert into channel(slug, name, min_recommend, icon_image, default_image, introduction)" +
                "values(?, ?, ?, ?, ?, ?)",
                channel.getSlug(), channel.getName(), channel.getMinRecommend(),
                channel.getIconImage(), channel.getDefaultImage(), channel.getIntroduction());
    }

    @Override
    public Channel getChannel(String slug) {
        return jdbcTemplate.queryForObject("select * from channel where slug = ?", new Object[]{slug}, channelRowMapper);
    }

    @Override
    public List<Category> getCategory(String slug) {
        return jdbcTemplate.query("select * from category where channel_slug = ?", new Object[]{slug}, categoryRowMapper);
    }

    @Override
    public void addCategory(Category category) {
        jdbcTemplate.update("insert into category(id, channel_slug, name, no_best, no_best_channel, no_rate_down, no_delete, min_recommend)"
                + "values(?, ?, ?, ?, ?, ?, ?, ?)",
                category.getId(), category.getChannel(), category.getName(),
                category.isNoBest(), category.isNoBestChannel(), category.isNoRateDown(), category.isNoDelete(), category.getMinRecommend());
    }

    @Override
    public void addCategory(List<Category> category) {
        for(int i = 0; i < category.size(); i++){
            addCategory(category.get(i));
        }
    }

    @Override
    public List<String> getCategoryNames(String slug) {
        return jdbcTemplate.queryForList("select name from category where channel_slug = ?", new Object[]{slug}, String.class);
    }

    @Override
    public void updateCategory(Category category) {
        jdbcTemplate.update("update category set name = ?, no_best = ?, no_best_channel = ?, no_rate_down = ?, no_delete = ?, min_recommend = ? " +
                        "where channel_slug = ? and id = ?",
                category.getName(), category.isNoBest(), category.isNoBestChannel(), category.isNoRateDown(), category.isNoDelete(), category.getMinRecommend(),
                category.getChannel(), category.getId());
    }

    @Override
    public boolean channelExist(String slug) { //returns true if it exists
        return jdbcTemplate.queryForObject("select exists(select * from channel where slug = ?)", new Object[]{slug}, Boolean.class);
    }

    @Override
    public void updateAddCategory(List<Category> categoryList) { //updates category if already exists, insert if it doesn't
        for(int i = 0; i < categoryList.size(); i++){
            Category category = categoryList.get(i);
            jdbcTemplate.update("insert into category(id, channel_slug, name, no_best, no_best_channel, no_rate_down, no_delete, min_recommend)"
                            + "values(?, ?, ?, ?, ?, ?, ?, ?) " +
                            "on duplicate key update " +
                            "name = ?, no_best = ?, no_best_channel = ?, no_rate_down = ?, no_delete = ?, min_recommend = ?",
                    category.getId(), category.getChannel(),
                    category.getName(), category.isNoBest(), category.isNoBestChannel(), category.isNoRateDown(), category.isNoDelete(), category.getMinRecommend(),
                    category.getName(), category.isNoBest(), category.isNoBestChannel(), category.isNoRateDown(), category.isNoDelete(), category.getMinRecommend());
        }
    }

    @Override
    public void updateChannel(Channel channel) {
        jdbcTemplate.update("update channel set " +
                        "name = ?, min_recommend = ?, icon_image = ?, default_image = ?, introduction = ?" +
                        "where slug = ?",
                channel.getName(), channel.getMinRecommend(), channel.getIconImage(), channel.getDefaultImage(), channel.getIntroduction(),
                channel.getSlug()
        );
    }
}
