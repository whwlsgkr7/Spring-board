package com.fastcampus.ch4.service;

import com.fastcampus.ch4.domain.UserDto;

import java.util.Map;

public interface UserService {
    int sign_up(UserDto userDto) throws Exception;

    UserDto IdCheck(String id) throws Exception;

    UserDto login(String id, String pwd) throws Exception;

    int getcount() throws Exception;

    int modify(UserDto userDto) throws Exception;

    int remove(String id) throws Exception;

    int removeAll() throws Exception;
}
