package com.aloha.community.service;

import java.util.List;
import com.aloha.community.domain.Board;

public interface BoardService {

    public List<Board> list() throws Exception;

    public Board select(String id) throws Exception; // @Param("id") 는 Mapper에서 사용하는거라, 여기서 쓸 필요 없음. 물론 다른 클래스에서는 또 쓰임.

    public int insert(Board board) throws Exception;

    public int update(Board board) throws Exception;

    public int delete(String id) throws Exception;
    
}
