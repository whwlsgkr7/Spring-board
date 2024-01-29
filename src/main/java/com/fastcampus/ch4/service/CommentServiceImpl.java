package com.fastcampus.ch4.service;

import com.fastcampus.ch4.dao.BoardDao;
import com.fastcampus.ch4.dao.CommentDao;
import com.fastcampus.ch4.domain.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
//    @Autowired
    CommentDao commentDao;
//    @Autowired
    BoardDao boardDao;

// 생성자 주입을 사용하는 이유는 어떠한 이유로 주입이 안되었을떄 컴파일에러가 나서 에러가 있음을 알수 있기떄문이다.
    public CommentServiceImpl(CommentDao commentDao, BoardDao boardDao) {
        this.commentDao = commentDao;
        this.boardDao = boardDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class) // Exception 에러가 나면 rollback을 한다. 즉, updateCommentCnt와 insert모두 완료한
    public int write(CommentDto commentDto) throws Exception{ // 해야함.
        boardDao.updateCommentCnt(commentDto.getBno(), 1);
        return commentDao.insert(commentDto);
    }

    @Override
    public CommentDto read(Integer cno) throws Exception{
        return commentDao.select(cno);
    }

    @Override
    public List<CommentDto> getList(Integer bno) throws Exception{
        return commentDao.selectAll(bno);
    }

    @Override
    public int getCount(Integer bno) throws Exception{
        return commentDao.count(bno);
    }

    @Override
    public int modify(CommentDto commentDto) throws Exception{
        return commentDao.update(commentDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Integer cno, Integer bno, String commenter) throws Exception{
        boardDao.updateCommentCnt(bno, -1);
        return commentDao.delete(cno, commenter);
    }

    @Override
    public int removeAll(Integer bno) throws Exception{
        return commentDao.deleteAll(bno);
    }
}
