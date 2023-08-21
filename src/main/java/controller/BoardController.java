package controller;

import dao.CategoryList;
import dao.Channel;
import dao.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import service.PostService;
import service.SimplifiedPostInfo;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static test.ChannelDaoTest.makeTestChannel;
import static test.PostDaoTest.makeTestPost;

@Controller
@RequestMapping("/b")
public class BoardController {

    @Autowired
    PostService postService;

    @GetMapping("/{channel_name}/write")
    public String writeControl(@PathVariable("board_name") String boardName) {
        return "post_write";
    }

    @GetMapping("/{channel_name}/{post_id}")
    public String viewPost(@PathVariable("channel_name") String channelName, @PathVariable("post_id") int postId,
                           @RequestParam(value="p", required=false, defaultValue="1") String page, @RequestParam(value="keyword", required=false)String keyword,
                           @RequestParam(value="category", required = false)String category, Model model) {
        Post post = postService.getPost(postId);
        Channel channel = postService.getChannel(channelName);
        CategoryList categoryList = postService.getCategoryList(channelName, category);
        HashMap<String, String> listparam = new HashMap<String,String>();
        listparam.put("page", page);
        listparam.put("searchWord", keyword);
        listparam.put("category", category);

        List<SimplifiedPostInfo> postListData = postService.getPostListData(listparam);
        model.addAttribute("user_profile_image", "default_user_profile.png");
        model.addAttribute("requested_post", post);
        model.addAttribute("requested_channel", channel);
        model.addAttribute("category_list", categoryList);
        model.addAttribute("post_list_data", postListData);
        int startpage = Integer.parseInt(page) - 4 > 0 ? Integer.parseInt(page) : 1;
        model.addAttribute("page_start", startpage);
        return "post_view";
    }

}
