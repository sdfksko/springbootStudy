package com.example.firstproject.models.entity;

import com.example.firstproject.models.dto.ReplyDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity     // 해당 클래스가 엔티티임을 선언, 클래스 필드를 바탕으로 DB에 테이블 생성
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor  // 매개변수가 아무것도 없는 기본 생성자 자동 생성
@ToString   // 모든 필드를 출력할 수 있는 toString 메소드 자동 생성
@Getter    // 모든 필드에 대한 getter 메소드 자동 생성
public class Reply {
    // 댓글 번호(대표키 및 기본키)
    @Id     // 기본키(엔티티의 대표값 지정)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 생성 추가
    private Long id;

    // 댓글 닉네임
    @Column
    private String nickname;

    // 댓글 내용
    @Column
    private String body;

    // 댓글에 연계된 Article정보
    @ManyToOne  // Reply(댓글)과 Article(게시글)은 다대일 관계로 설정
    @JoinColumn(name = "article_id") // 외래키를 지정
    private Article article;    // 해당 댓글의 부모 게시글

    public static Reply createReply(
            ReplyDto replyDto,
            Article article) {
        // Article의 id와 Reply의 articleId는 같아야 한다. 다르면 예외처리
        if(article.getId() != replyDto.getArticleId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");
        if(replyDto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");

        // article.getId() == replyDto.getArticleId()
        // replyDto.getId() == null
        return new Reply(
                null, //commentDto.getId(),
                replyDto.getNickname(),
                replyDto.getBody(),
                article);
    }
    public void patch(ReplyDto replyDto) {
        if(this.id != replyDto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 댓글의 id가 일치하지 않습니다.");

        if (replyDto.getNickname() != null) {     // 수정할 닉네임 정보가 있으면
            this.nickname = replyDto.getNickname();   // 실제 닉네임 변경
        }
        if (replyDto.getBody() != null) {         // 수정할 본문 데이터가 있으면
            this.body = replyDto.getBody();           // 실제 본문 변경
        }
    }
}
