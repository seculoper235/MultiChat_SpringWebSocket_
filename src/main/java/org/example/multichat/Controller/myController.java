package org.example.multichat.Controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class myController {
    @RequestMapping("/mychat")
    public String enterChat() {
        return "mychat";
    }

    public ModelAndView entryChat(ModelAndView mav) {
        mav.setViewName("mychat");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("사용자 이름: " +user.getUsername());
        mav.addObject("userid", user.getUsername());

        return mav;
    }
}
