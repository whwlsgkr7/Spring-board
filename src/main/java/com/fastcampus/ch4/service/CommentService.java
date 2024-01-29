package com.fastcampus.ch4.service;

import com.fastcampus.ch4.domain.CommentDto;

import java.util.List;
import java.util.Map;

public interface CommentService {
    int write(CommentDto commentDto) throws Exception;

    CommentDto read(Integer cno) throws Exception;

    List<CommentDto> getList(Integer bno) throws Exception;

    int getCount(Integer bno) throws Exception;

    int modify(CommentDto commentDto) throws Exception;

    int remove(Integer cno, Integer bno, String commenter) throws Exception;

    int removeAll(Integer bno) throws Exception;
}
