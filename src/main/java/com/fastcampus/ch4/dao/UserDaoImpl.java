package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    SqlSession session;

    String namespace="com.fastcampus.ch4.dao.UserMapper.";

    @Override
    public int insert(UserDto userDto) throws Exception{
        return session.insert(namespace + "insert", userDto);
    }

    @Override
    public UserDto select(String id) throws Exception{
        return session.selectOne(namespace + "selectId", id);
    }

    @Override
    public UserDto select(String id, String pwd) throws Exception{
        Map map = new HashMap();
        map.put("id", id);
        map.put("pwd", pwd);
        return session.selectOne(namespace + "selectOne", map);
    }

    @Override
    public int count() throws Exception{
        return session.selectOne(namespace + "count");
    }

    @Override
    public int update(UserDto userDto) throws Exception{
        return session.update(namespace + "update", userDto);
    }

    @Override
    public int delete(String id) throws Exception{
        return session.delete(namespace + "deleteOne", id);
    }

    @Override
    public int deleteAll() throws Exception{
        return session.delete(namespace + "deleteAll");
    }
}