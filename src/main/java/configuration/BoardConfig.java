package configuration;

import controller.BoardController;
import dao.Channel;
import dao.Post;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import service.PostService;
import service.TestPostService;

@Configuration
public class BoardConfig{


    @Bean
    public BoardController boardController(){
        return new BoardController();
    }

    @Bean
    public PostService postService() {
        return new TestPostService();
    }

}
