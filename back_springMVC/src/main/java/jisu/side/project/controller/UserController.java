package jisu.side.project.controller;

import jisu.side.project.dto.Role;
import jisu.side.project.dto.User;
import jisu.side.project.dto.UserDto;
import jisu.side.project.dto.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * jpa 작동 확인
 *  @2024.01.03
 * */
@Slf4j
@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    /** 가입 **/
    @PostMapping("/sign")
    public String singUser(@Validated UserDto user, Role role){
        log.info("singUser : {},{}",user.toString(), role);
        userService.insert(user, role);
        return "index";
    }

    /** 확인 **/
    @PostMapping("/select/{id}")
    public String whoIs (@Validated String username){
        log.info("isUser : {}", username);
        User ami = userService.isUser(username);
        if(ami==null) ami=userService.isAdmin(username);
        log.info("ami : {}", ami);
        return ami!=null? ami.getAuth().getRole().toString() : "notUser";
    }

}
