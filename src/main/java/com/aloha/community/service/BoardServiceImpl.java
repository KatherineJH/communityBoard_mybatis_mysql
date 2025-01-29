package com.aloha.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aloha.community.domain.Board;
import com.aloha.community.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    // 목록
    @Override
    public List<Board> list() throws Exception {
        List<Board> list = boardMapper.list();
        return list;
    }

    // 조회
    @Override
    public Board select(String id) throws Exception {
        Board board = boardMapper.select(id);
        return board;
    }

    // 등록
    @Override
    public int insert(Board board) throws Exception {
        int result = boardMapper.insert(board);
        return result;
    }

    // 수정
    @Override
    public int update(Board board) throws Exception {
        int result = boardMapper.update(board);
        return result;
    }

    // 삭제
    @Override
    public int delete(String id) throws Exception {
        int result = boardMapper.delete(id);
        return result;
    }
    
}
