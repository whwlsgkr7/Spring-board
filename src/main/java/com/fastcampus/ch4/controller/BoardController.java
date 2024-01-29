package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.CommentDto;
import com.fastcampus.ch4.domain.PageHandler;
import com.fastcampus.ch4.domain.SearchCondition;
import com.fastcampus.ch4.service.BoardService;
import com.fastcampus.ch4.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @Autowired
    CommentService commentService;


    @GetMapping("/write") // 게시글을 작성하기 위해 양식을 불러오는 과정
    public String write(Model m){
        m.addAttribute("mode", "new");
        return "board";
    }
    // 매개변수로 참조형을 쓰는 경우 DS가 BoardDto 객체를 생성하고 WebDataBinder가 타입변환과 데이터검증을 통해 쿼리스트링과 BoardDto의 객체의 이름이 일치하는 변수에 자동으로 매핑해준다.
    @PostMapping ("/write") // 게시글을 작성하고 db에 저장하는 과정
    public String write(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr){
        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);
        try {
            int rowCnt = boardService.write(boardDto);
            if(rowCnt!=1) throw new Exception("Write failed");
            rattr.addFlashAttribute("msg", "write_OK");

            return "redirect:/board/list";
        } catch (Exception e) {
//            m.addAttribute("boardDto", boardDto); 참조형의 경우 자동으로 Model에 저장된다. 즉, 앞에 @ModelAttribute를 생략해도 자동으로 붙는다.
            rattr.addFlashAttribute("msg", "write_error");
            return "board";
        }
    }
// 쿼리스트링을 매개변수로 받을때 매개변수앞에는 @RequestParam이 생략되어있는것이다. required=true 또는 defaultValue를 설정할때만 쓴다.
    @GetMapping("/read") // 게시글을 읽는 과정, 쿼리스트링의 name을 매개변수로 선언하면 자동으로 값을 입력 받을수 있음, Reflectio API
    public String read(Integer bno, Integer page, Integer pageSize, Model m){
        try {
            BoardDto boardDto = boardService.read(bno);
            m.addAttribute("boardDto", boardDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "board";
    }

    // 게시글 리스트를 읽어오는 과정
    @GetMapping("/list")
    // , SearchCondition의 변수를 쿼리스트링으로 부터 받게다는 뜻, 쿼리스트링이 없는 경우 SearchCondition은 기본생성자에 의해 초기화
    //  즉, SearchCondition 변수에 지정해준 값, 그래서 쿼리스트링없이 게시글 리스트를 받아올수 있는것이다.
    public String list(Model m, SearchCondition sc, HttpServletRequest request) {
        if(!isLogin(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();
// getRequestURL()은 클라이언트가 요청한 전체 URL을 StringBuffer 객체로 반환한다. 단, 쿼리스트링은 제외
        try {
            int totalCnt = boardService.getSearchResultCnt(sc);
            m.addAttribute("totalCnt", totalCnt);

            PageHandler pageHandler = new PageHandler(totalCnt, sc);

            List<BoardDto> list = boardService.getSearchResultPage(sc);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);

            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            m.addAttribute("startOfToday", startOfToday.toEpochMilli());
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "LIST_ERR");
            m.addAttribute("totalCnt", 0);
        }

        return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }

    @GetMapping("/commentList")
    public String comment(Integer bno, Model m){
        try {
            List<CommentDto> cl = commentService.getList(bno);
            m.addAttribute("cl", cl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "comment";
    }

    @PostMapping ("/modify") // 게시글을 수정하고 db에 저장
    public String modify(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr){
        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);
        try {
            int rowCnt = boardService.modify(boardDto);
            if(rowCnt!=1) throw new Exception("modify failed"); // 수정, 삭제는 실수를 방지하기 위해 대화상자를 통해 사용자에게 한번 확인하고 잘 처리되었는지 대화상자를 통해 사용자에게 알려줘야한다.
            rattr.addFlashAttribute("msg", "modify_OK"); // 세션에 저장되어 사용된 뒤에 자동으로 삭제된다.

            return "redirect:/board/list";
        } catch (Exception e) {
            m.addAttribute("boardDto", boardDto); // 수정중인던 내용을 유지하기 위해 작성중이던 boardDto를 넘겨줌, 참조형은 자동으로 model에 저장되므로 생략가능
            rattr.addFlashAttribute("msg", "modify_error");
            return "board";
        }
    }


    @PostMapping("/remove") // 게시글을 삭제
    public String remove(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr){
        String writer = (String)session.getAttribute("id");
        try {
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize); // 모델에 담아주면 redirect주소 뒤에 자동으로 쿼리스트링으로 붙는다.
            int rowCnt = boardService.remove(bno, writer);
            if(rowCnt != 1){
                throw new Exception("remove error");
            }
            rattr.addFlashAttribute("msg", "remove OK");
        } catch (Exception e) {

            rattr.addFlashAttribute("msg", "remove error");
        }


        return "redirect:/board/list";
    }



    private boolean isLogin(HttpServletRequest request) { // 스프링에서는 HttpSession을 매개변수로 선언해서 session.getAttribute("id)도 가능하지만
        HttpSession session = request.getSession(); // list맵핑에서 request.getRequestURL()을 사용하기 위해 HttpServletRequest를 사용하므로 여기서도 맞춰서 사용
        return session.getAttribute("id")!=null;

    }


}
