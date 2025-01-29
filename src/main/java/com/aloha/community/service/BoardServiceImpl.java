package com.aloha.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aloha.community.domain.Board;
import com.aloha.community.domain.Files;
import com.aloha.community.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private FileService fileService;

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

        List<MultipartFile> fileList = board.getFileList();
        if(fileList != null){
            for(MultipartFile file : fileList){
                Files uploadFile = new Files();
                uploadFile.setFile(file);
                uploadFile.setParentTable("board");
                uploadFile.setParentNo(board.getNo());
                uploadFile.setType("main");

                fileService.upload(uploadFile);
            }
        }
                // // 게시글 중 파일이 있다면 파일을 로그로 출력.
        // List<MultipartFile> fileList = board.getFileList();
        // if(fileList != null){
        //     for(MultipartFile file : fileList){
        //         log.info("--------------------------------------------");
        //         log.info("Original File name: "+ file.getOriginalFilename());
        //         log.info("Contents file: "+file.getContentType());
        //         log.info("Parameter name: "+file.getName());
        //         log.info("File size: "+file.getSize() + " Bytes");
        //         log.info("--------------------------------------------");
        //     }
        // }
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
