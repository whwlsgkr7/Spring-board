package com.fastcampus.ch4.service;

import com.fastcampus.ch4.dao.UserDao;
import com.fastcampus.ch4.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int sign_up(UserDto userDto) throws Exception{
        return userDao.insert(userDto);
    }

    @Override
    public UserDto IdCheck(String id) throws Exception{
        return userDao.select(id);
    }

    @Override
    public UserDto login(String id, String pwd) throws Exception{
        return userDao.select(id, pwd);
    }

    @Override
    public int getcount() throws Exception{
        return userDao.count();
    }

    @Override
    public int modify(UserDto userDto) throws Exception{
        return userDao.update(userDto);
    }

    @Override
    public int remove(String id) throws Exception{
        return userDao.delete(id);
    }

    @Override
    public  int removeAll() throws Exception{
        return userDao.deleteAll();
    }
}
