package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class PostDaoJdbc implements PostDao{

    JdbcTemplate jdbcTemplate;
    PostRowMapper postRowMapper;

    public PostDaoJdbc(DataSource dataSource){
        this.postRowMapper = new PostRowMapper();
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    class PostRowMapper implements RowMapper<Post> {
        @Override
        public Post mapRow(ResultSet resultSet, int i) throws SQLException {
            Post post = new Post();
            post.setId(resultSet.getInt("id"));
            post.setUserNickname(resultSet.getString("user_nickname"));
            post.setUserNumber(UUID.fromString(resultSet.getString("user_number")));
            post.setTimeCreated(resultSet.getTimestamp("time_created").toLocalDateTime());
            post.setTitle(resultSet.getString("title"));
            post.setContent(resultSet.getString("content"));
            post.setRateUp(resultSet.getInt("rate_up"));
            post.setRateDown(resultSet.getInt("rate_down"));
            post.setViewCount(resultSet.getInt("view_count"));
            post.setBest(resultSet.getBoolean("best"));
            post.setCategoryId(resultSet.getInt("category_id"));
            return post;
        }
    }

    @Override
    public void add(Post post) {
        this.jdbcTemplate.update("insert into post(id, user_number, user_nickname, time_created, " +
                        "title, content, rate_up, rate_down, view_count, best, category_id) " +
                        "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                post.getId(), post.getUserNumber().toString(), post.getUserNickname(), post.getTimeCreated(),
                post.getTitle(), post.getContent(), post.getRateUp(), post.getRateDown(), post.getViewCount(), post.isBest(), post.getCategoryId());
    }

    @Override
    public Post searchById(int id) {
        return jdbcTemplate.queryForObject("select * from post where id = ?", new Object[]{id}, postRowMapper);
    }

    @Override
    public List<Post> searchByUser(String nickname) {
        List<Post> result = jdbcTemplate.query("select * from post where user_nickname = ?", new Object[]{nickname}, postRowMapper);
        return result;
    }

    @Override
    public void deletePost(int id) {
        jdbcTemplate.update("delete from post where id = ?", id);
    }

    @Override
    public void updatePost(Post post) {
        jdbcTemplate.update("update post set " +
                "user_number = ?, user_nickname = ?, time_created = ?, title = ?, content = ?," +
                "rate_up = ?, rate_down = ?, view_count = ?, best = ?, category_id = ? " +
                "where id = ?",
                post.getUserNumber().toString(), post.getUserNickname(), post.getTimeCreated(), post.getTitle(), post.getContent(),
                post.getRateUp(), post.getRateDown(), post.getViewCount(), post.isBest(), post.getCategoryId(),
                post.getId());
    }

    public void deleteAll(){
        jdbcTemplate.update("delete from post");
    }

    public int getCount(){
        Integer count = jdbcTemplate.queryForObject(
                "select count(*) from post", Integer.class);
        return count;
    }

    public List<Post> getAll(){
        List<Post> result = jdbcTemplate.query("select * from post order by id", postRowMapper);
        return result;
    }
}
