package com.fastcampus.ch4.service;

import com.fastcampus.ch4.dao.BoardDao;
import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardDao dao;

    @Override
    public int write(BoardDto dto) throws Exception{
        return dao.insert(dto);
    }

    @Override
    public BoardDto read(Integer bno) throws Exception{
        BoardDto dto = dao.select(bno);
        dao.increaseViewCnt(bno);
        return dto;
    }

    @Override
    public List<BoardDto> getList() throws Exception{
        return dao.selectAll();
    }

    @Override
    public int getCount() throws Exception{
        return dao.count();
    }

    @Override
    public List<BoardDto> getPage(Map map) throws Exception{
        return dao.selectPage(map);
    }

    @Override
    public List<BoardDto> getSearchResultPage(SearchCondition sc) throws Exception{
        return dao.searchSelectPage(sc);
    }

    @Override
    public int getSearchResultCnt(SearchCondition sc) throws Exception{
        return dao.searchResultCnt(sc);
    }

    public int modifyCommentCnt(Integer bno, int i) throws Exception{
        return dao.updateCommentCnt(bno, i);
    }

    @Override
    public int modify(BoardDto dto) throws Exception{
        return dao.update(dto);
    }

    @Override
    public int remove(Integer bno, String writer) throws Exception{
        return dao.delete(bno, writer);
    }




}
