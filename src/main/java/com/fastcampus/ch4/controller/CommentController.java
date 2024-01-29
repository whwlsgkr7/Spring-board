package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.CommentDto;
import com.fastcampus.ch4.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> list(Integer bno){
        try {
            List<CommentDto> list = commentService.getList(bno);
            return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK); // ResponseEntity로 감싸서 원하는 상태코드를 같이 반환
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
        }

    }

    // {"pcno":0, "comment":"hi"}
    @PostMapping("/comments") // JSON으로 보내는 데이터를 자바 객체로 받기 위해서는 RequstBody 어노테이션을 매개변수 앞에 붙여야한다.
    public ResponseEntity<String> write(@RequestBody CommentDto commentDto, Integer bno, HttpSession session){
//        String commenter = "asdf";
//        commentDto.setCommenter(commenter);
        commentDto.setBno(bno);
        commentDto.setCommenter((String)session.getAttribute("id"));
// 대댓글이 아니라 댓글을 쓸때는 pcno는 cno와 같게 자동으로 초기화되기 때문에 write를 호출할때 안넘겨줘도 된다.
        try {
            int rowCnt = commentService.write(commentDto);
            if(rowCnt!=1){
                throw new Exception("Write failed");
            }
            return new ResponseEntity<>("Write_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Write_ERR", HttpStatus.BAD_REQUEST);
        } // REST API를 이용할 때는 에러가 났을때 상태코드가 제대로 표시되지 않기 때문에 ResponseEntity<>를 이용해서 서버에서 설정해줘야한다.

    }



    @PatchMapping("/comments/{cno}") // cno는 쿼리스트링의 값이 아니기 때문에 매개변수 앞에 PathVariable 어노테이션을 붙여야한다.
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto commentDto, HttpSession session){
//        String commenter = "asdf";
//        commentDto.setCommenter(commenter);
        String id = (String)session.getAttribute("id");
        commentDto.setCommenter(id);
        commentDto.setCno(cno);
        try {
            int rowCnt = commentService.modify(commentDto);
            if(rowCnt!=1){
                throw new Exception();
            }
            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/comments/{cno}")
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session){
//        String commenter = "asdf";
        String commenter = (String)session.getAttribute("id");
        try {
            int rowCnt = commentService.remove(cno, bno, commenter);
            if(rowCnt != 1){
                throw new Exception("Delete Failed");
            }
            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }


}
