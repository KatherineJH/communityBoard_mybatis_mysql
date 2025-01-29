package com.aloha.community.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
// import lombok.NoArgsConstructor;

@Data
// @NoArgsConstructor // 기본 생성자를 생성해주는 어노테이션
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자를 생성해주는 어노테이션
@Builder // 빌더 패턴을 사용할 수 있게 해주는 어노테이션
public class Board {

    private int no;
    private String id;
    private String title;
    private String writer;
    private String content;
    private Date createdAt;
    private Date updatedAt;

    private List<MultipartFile> fileList; // 파일 목록

    // 이 생성자를 만들어주면, 게시글 작성했을 때 id가 null로 처리되지 않고 정상 작동한다.
    public Board(){
        this.id = UUID.randomUUID().toString();
    }
    
}
