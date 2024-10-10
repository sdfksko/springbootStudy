<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="card m-2" id="comments-new">
    <div class="card-body">
        <!-- 댓글 작성 form -->
        <form>
            <!-- 닉네임 입력 -->
            <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">닉네임</label>
                <input type="text" class="form-control" id="new-comment-nickname" aria-describedby="emailHelp">
            </div>
            <!-- 댓글 작성 -->
            <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label">댓글 내용</label>
                <input type="text" class="form-control" id="new-comment-body" rows="3">
            </div>
            <!-- 히든 input -->
            <input type="hidden" id="new-comment-article-id" value='<c:out value="${article.id}" />'>
            <!-- 댓글 전송 버튼 -->
            <button type="button" class="btn btn-primary" id="comment-create-btn">댓글 작성</button>
        </form>
    </div>
</div>
<script>
    // 댓글 생성 버튼 세팅
    const commentCreateBtn = document.querySelector("#comment-create-btn");
    // 댓글 클릭 이벤트 감지하여 처리
    commentCreateBtn.addEventListener("click", function(){
        // console.log("댓글 생성 버튼 클릭");

        // 댓글에 관한 정보를 json 형태로 만들기
        const comment = {
            nickname: document.querySelector("#new-comment-nickname").value,
            body: document.querySelector("#new-comment-body").value,
            articleId: document.querySelector("#new-comment-article-id").value
        };
        console.log('new comment', comment);

        // fetch() -> 비동기 통신을 위한 API
        fetch('/api/articles/' + comment.articleId + '/replys', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(comment),
        }).then(function(response) {        // API주소를 호출하고 응답까지 기다린 다음 실행됌
            // 응답 처리문

            const responseMsg = response.ok ? "댓글이 등록되었습니다" : "댓글 등록에 실패했습니다";
            alert(responseMsg);

            // 현재 페이지를 새로고침하는 코드
            window.location.reload();
        });
    });
</script>