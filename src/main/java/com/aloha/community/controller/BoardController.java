package com.aloha.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aloha.community.domain.Board;
import com.aloha.community.service.BoardService;

import lombok.extern.slf4j.Slf4j;

/**
 * 목록         /board/list     [GET]
 * 조회         /board/select   [GET]
 * 등록         /board/insert   [GET]
 * 등록 처리    /board/insert   [POST]
 * 수정         /board/update   [GET]
 * 수정 처리    /board/update   [POST]
 * 삭제 처리    /board/delete   [POST]
*/
@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
    
    @Autowired
    private BoardService boardService;

    /**
     * 목록
     * @return
     * @throws Exception 
    */
    @GetMapping("/list")
    public String list(Model model) throws Exception{ // View 에서 접근 가능하게 하려면 Model interface를 parameter로 넣어줘야 한다.
        List<Board> boardLsit = boardService.list();
        model.addAttribute("boardList", boardLsit);
        return "/board/list";
    }
    
    /**
     * 조회
     * @param id
     * @return
     * @throws Exception 
    */
    @GetMapping("/select")
    public String select(Model model, @RequestParam("id") String id) throws Exception{
        Board board = boardService.select(id);
        model.addAttribute("board", board);
        return "/board/select";
    }

    /**
     * 등록
     * @return
     */
    @GetMapping("/insert")
    public String insert(){
        return "/board/insert";
    }
    
    /**
     * 등록 처리
     * @param board
     * @return
     * @throws Exception 
    */
    @PostMapping("/insert")
    public String insertPost(Board board) throws Exception{
        log.info("board: " + board); // 파일첨부가 된 게시글을 등록하는 순간, 터미널에 로고 찍히면서 그 안에 파일들 아이디도 같이 전달되는 것을 확인할 수 있다.
        int result = boardService.insert(board);
        if(result>0){
            return "redirect:/board/list";
        } else {
            return "redirect:/board/insert?error";
        }
    } 
    
    /**
     * 수정
     * @param id
     * @return
     * @throws Exception 
    */
    @GetMapping("/update")
    public String update(Model model, @RequestParam("id") String id) throws Exception{
        Board board = boardService.select(id);
        model.addAttribute("board", board);
        return "/board/update";
    }
    
    /**
     * 수정 처리
     * @param board
     * @return
     * @throws Exception 
    */
    @PostMapping("/update")
    public String updatePost(Board board) throws Exception{
        int result = boardService.update(board);
        if(result>0){
            return "redirect:/board/list";
        } else {
            return "redirect:/board/update?error%id="+board.getId();
        }
    }

    // 삭제 처리
    @PostMapping("/delete")
    public String delete(@RequestParam("id") String id) throws Exception{
        int result = boardService.delete(id);
        if(result >0){
            return "redirect:/board/list";
        } else{
            return "redirect:/board/update?error%id="+id;
        }
    }
    
    
}
