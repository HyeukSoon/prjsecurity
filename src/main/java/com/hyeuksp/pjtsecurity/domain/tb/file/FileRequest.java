package com.hyeuksp.pjtsecurity.domain.tb.file;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FileRequest {

    private Long id;                // 파일 번호 (PK)
    private Long postId;            // 게시글 번호 (FK)
    private String originalName;    // 원본 파일명
    private String saveName;        // 저장 파일명
    private long size;              // 파일 크기

    @Builder
    public FileRequest(String originalName, String saveName, long size) {
        this.originalName = originalName;
        this.saveName = saveName;
        this.size = size;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

}
/****
 * 메서드
 * 설명
 *
 *
 * 생성자
 * 생성자 메서드에 @Builder 어노테이션이 선언되어 있는데요. @Builder는 롬복에서 제공해주는 기능으로,
 * 빌더 패턴(Builder pattern)으로 객체를 생성할 수 있게 해줍니다. 빌더 패턴은 생성자 파라미터가 많은 경우에 가독성을 높여주기도 하고,
 * 아래 코드와 같이 변수에 값을 넣어주는 순서를 달리하거나, 원하는 변수에만 값을 넣어 객체를 생성할 수 있습니다.
 * 파미페럿님 블로그에 빌더 패턴에 대한 설명이 정말 쉽게 정리되어 있으니 한번쯤은 읽어보시기를 권장드립니다.
 *
 *
 * setPostId( )
 * 파일은 게시글이 생성(INSERT) 된 후에 처리되어야 합니다. 해당 메서드는 생성된 게시글 ID를 파일 요청 객체의 postId에 저장하는 용도로 사용되는데요.
 * 객체 생성 시점에 같이 처리하지 않고 set 메서드를 이용해서 처리하는 이유는 뒤에서 설명드리도록 하겠습니다.
 * 출처: https://congsong.tistory.com/39 [Let's develop:티스토리]
 */