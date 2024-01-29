package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.UserDto;

import java.util.Map;

public interface UserDao {
    int insert(UserDto userDto) throws Exception;
    UserDto select(String id) throws Exception;
    UserDto select(String id, String pwd) throws Exception;

    int count() throws Exception;

    int update(UserDto userDto) throws Exception;

    int delete(String id) throws Exception;

    int deleteAll() throws Exception;
}
