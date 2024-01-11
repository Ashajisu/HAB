package jisu.side.project.controller;

import jisu.side.project.dto.Auth;
import jisu.side.project.dto.Role;
import jisu.side.project.dto.user.Member;
import jisu.side.project.dto.user.MemberDto;
import jisu.side.project.dto.user.UserService;
import jisu.side.project.security.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * jpa 작동 확인 _ security없이
 *  2024.01.09
 *       * RequestParam 어노테이션은 쿼리 파라미터를 받아오기 위해 사용되며, username= ? 로 작성해야합니다.
 *      *요청 URL 경로 변수에서 값을 가져오기 위해서는 PathVariable 어노테이션을 사용해야 합니다.
 * */
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    /** 가입 **/
    @PostMapping("/sign/user")
    public String insertUser(@RequestBody MemberDto memberDto){
        log.info("insert : {}", memberDto.toString());
        userService.insert(memberDto, Role.USER);
        return memberDto.getId();
    }
    @PostMapping("/sign/admin")
    public String insertAdmin(@RequestBody MemberDto memberDto){
        log.info("insert : {}", memberDto.toString());
        userService.insert(memberDto, Role.ADMIN);
        return memberDto.getId();
    }
    /** 로그인 **/
    @PostMapping("/sign/login")
    public SecurityUser login (@RequestParam String id, @RequestParam String password){
        log.info("login proccess : {}, {}",id,password);
        MemberDto memberDto = new MemberDto(id,password);
        SecurityUser signin = userService.login(memberDto);
        return signin;
    }

    @GetMapping("/get/userid")
    public String getUser(Authentication authentication){
        log.info("authenticateion.getName : {}",authentication.getName());
        Member member = userService.select(authentication.getName());
        return member.getId();
    }

    /** 확인 **/
    @GetMapping("/select/{username}")
    public String select (@PathVariable String username){
        Member ami = userService.select(username);
        log.info("select : {}", ami);
        return ami.getId();
    }

    /** 권한 **/
    @GetMapping("/isuser/{username}")
    public String isUser (@PathVariable String username){
        Auth ami = userService.isUser(username);
        log.info("isUser : {}", ami);
        return ami.getRole().toString();
    }

    /** 변경 **/
    @PostMapping("/change/pw")
    public String changePw (@RequestBody MemberDto memberDto){
        userService.changePw(memberDto);
        return memberDto.getId();
    }

    @PostMapping("/change/quit")
    public String quit (@RequestBody MemberDto memberDto){
        userService.quit(memberDto);
        return memberDto.getId();
    }



}
