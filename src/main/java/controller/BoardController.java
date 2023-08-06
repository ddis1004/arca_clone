package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;

@Controller
@RequestMapping("/b")
public class BoardController {

    @GetMapping("/{board_name}/write")
    public String writeControl(@PathVariable("board_name") String boardName){

        return "tuitest";
    }

    @GetMapping("{board_name}/{post_number}")
    public String viewPost(@PathVariable("board_name")String boardName, @PathVariable("post_number") int postNumber){
        return "post_view";
    }

}
