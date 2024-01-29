package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.CommentDto;

import java.util.List;
import java.util.Map;

public interface CommentDao {
    int insert(CommentDto commentDto) throws Exception;

    CommentDto select(Integer cno) throws Exception;

    List<CommentDto> selectAll(Integer bno) throws Exception;

    int count(Integer bno) throws Exception;

    int update(CommentDto commentDto) throws Exception;

    int delete(Integer cno, String commenter) throws Exception;

    int deleteAll(Integer bno) throws Exception;
}
