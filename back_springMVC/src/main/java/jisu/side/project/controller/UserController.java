package jisu.side.project.controller;

import jisu.side.project.dto.Auth;
import jisu.side.project.dto.Role;
import jisu.side.project.dto.user.User;
import jisu.side.project.dto.user.UserDto;
import jisu.side.project.dto.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * jpa 작동 확인 _ security없이
 *  2024.01.09
 * */
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    /** 가입 **/
    @PostMapping("/sign/user")
    public String insertUser(@RequestBody UserDto userDto){
        log.info("insert : {}",userDto.toString());
        userService.insert(userDto, Role.USER);
        return userDto.getId();
    }
    @PostMapping("/sign/admin")
    public String insertAdmin(@RequestBody UserDto userDto){
        log.info("insert : {}",userDto.toString());
        userService.insert(userDto, Role.ADMIN);
        return userDto.getId();
    }

    /** 확인
     * RequestParam 어노테이션은 쿼리 파라미터를 받아오기 위해 사용되며, username= ? 로 작성해야합니다.
     *요청 URL 경로 변수에서 값을 가져오기 위해서는 PathVariable 어노테이션을 사용해야 합니다.
     * **/
    @GetMapping("/select/{username}")
    public String select (@PathVariable String username){
        User ami = userService.select(username);
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
    public String changePw (@RequestBody UserDto userDto){
        userService.changePw(userDto);
        return userDto.getId();
    }

    @PostMapping("/change/quit")
    public String quit (@RequestBody UserDto userDto){
        userService.quit(userDto);
        return userDto.getId();
    }

}
