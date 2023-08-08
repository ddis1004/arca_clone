package controller;

import dao.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("/b")
public class BoardController {

    @GetMapping("/{board_name}/write")
    public String writeControl(@PathVariable("board_name") String boardName){
        return "post_write";
    }

    @GetMapping("/{board_name}/{post_number}")
    public String viewPost(@PathVariable("board_name")String boardName, @PathVariable("post_number") int postNumber, Model model){
        Post post = new Post();
        post.setId(postNumber);
        post.setTimeCreated(LocalDateTime.now());
        post.setUserNickname(""+postNumber);
        post.setTitle("TEST_TITLE_" + postNumber);
        post.setContent("<h1>TEST_CONTENT_" + postNumber + "<h1>");
        post.setUserNumber(UUID.randomUUID());
        post.setRateUp(0);
        post.setRateDown(0);
        post.setViewCount(0);
        post.setBest(false);
        post.setCategoryId(0);
        model.addAttribute("requested_post", post);
        return "post_view";
    }

}
