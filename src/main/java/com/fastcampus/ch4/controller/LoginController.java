package com.fastcampus.ch4.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fastcampus.ch4.dao.UserDao;
import com.fastcampus.ch4.domain.*;
import com.fastcampus.ch4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 1. 세션을 종료
        session.invalidate();
        // 2. 홈으로 이동
        return "redirect:/";
    }

    @PostMapping("/login") // loginForm이 input필드를 통해 제출한 값을 매개변수로 받음, Reflection API
    public String login(String id, String pwd, String toURL, boolean rememberId,
                        HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 1. id와 pwd를 확인
        if(!loginCheck(id, pwd)) {
            // URLEncoder.encode()는 URL에 포함될 수 없는 문자들을  URL에 포함할 수 있는 형식으로 변환한다.
            // non-ASCII문자를 ASCII문자를 이용해 문자코드(16진수) 문자열로 변환하는것이다.
            String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");

            return "redirect:/login/login?msg="+msg; // redirect는 Get요청이다.
        }
        // 2-2. id와 pwd가 일치하면,
        //  세션 객체를 얻어오기
        HttpSession session = request.getSession();
        //  세션 객체에 id를 저장
        session.setAttribute("id", id);

        if(rememberId) {
            //     1. 쿠키를 생성
            Cookie cookie = new Cookie("id", id); // ctrl+shift+o 자동 import
//		       2. 응답에 저장
            response.addCookie(cookie);
        } else {
            // 1. 쿠키를 삭제
            Cookie cookie = new Cookie("id", id); // ctrl+shift+o 자동 import
            cookie.setMaxAge(0); // 쿠키를 삭제
//		       2. 응답에 저장
            response.addCookie(cookie);
        }
// Home에서 바로 Login버튼을 누르면 로그인 후 Home으로 돌아가고 Home에서 Board로 바로 들어갈려고 했으면 Login후 바로 Board로 들어갈 수 있다.
        toURL = toURL==null || toURL.equals("") ? "/" : toURL; // loginForm을 통해 받은 toURL
// loginForm을 통해 받지 않고 아래처럼 request.getParameter("toURL")을 사용해서 얻어 올 수도 있다.
//        toURL = request.getParameter("toURL")==null || toURL.equals("") ? "/" : toURL;
        return "redirect:"+toURL;
    }

    private boolean loginCheck(String id, String pwd) {
        UserDto userDto = null;

        try {
            userDto = userService.login(id, pwd);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return userDto != null && userDto.getPwd().equals(pwd);
//        return "asdf".equals(id) && "1234".equals(pwd);
    }

    @GetMapping("/modify")
    public String myInfo(HttpSession session, Model m){
        if(!isLogin(session)){
            return "redirect:/login/login";
        }
        String id = (String)session.getAttribute("id");
        try {
            UserDto userDto = userService.IdCheck(id);
            m.addAttribute(userDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "myInfo";
    }

    @PostMapping("/modify")
    public String myInfo(UserDto userDto){
        try {
            userService.modify(userDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "myInfo";
    }

    private boolean isLogin(HttpSession session){
        return session.getAttribute("id") != null;
    }
}