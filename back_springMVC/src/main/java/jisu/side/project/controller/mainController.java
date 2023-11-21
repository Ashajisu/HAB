package jisu.side.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * view resolver 작동 확인
 *  @2023.11.6
 *  @2023.11.21
 *
 *  page List
 *  "/" resources/static/index.html 권한불필요
 *  "/main/second" webapp/view/second.html 로그인필요 :
 *      spring.mvc.view.prefix=/WEB-INF/views/
 *      spring.mvc.view.suffix=.html
 * */
@Controller
@RequestMapping("/main")
public class mainController {

    /** 로그인 성공화면
     * */
    @GetMapping("/second")
    public String second(){
//        return "/second.html";
        return "second";
    }

}
