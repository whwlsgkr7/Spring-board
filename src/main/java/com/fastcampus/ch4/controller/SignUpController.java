package com.fastcampus.ch4.controller;

import java.net.URLEncoder;
import com.fastcampus.ch4.domain.UserDto;
import com.fastcampus.ch4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/sign")
public class SignUpController {
    @Autowired
    UserService userService;

    @GetMapping("/add")
    public String signForm(){return "signForm";}

    @PostMapping("/add")
    public String sign(UserDto userDto){
        if(!signCheck(userDto.getId())) {
            // id가 이미 존재하면 다른 아이디를 입력해달라고 하기
            String msg = URLEncoder.encode("이미 사용중인 id입니다.", StandardCharsets.UTF_8);

            return "redirect:/sign/add?msg="+msg;
        }

        try {
            userService.sign_up(userDto);
            return "redirect:/login/login";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/sign/add";
        }
    }

    private boolean signCheck(String id){
        UserDto userDto = null;
        try {
            userDto = userService.IdCheck(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return userDto == null;
    }


}
