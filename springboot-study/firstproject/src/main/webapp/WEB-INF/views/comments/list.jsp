<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="comments-list">
    <c:forEach var="comment" items="${commentDtoList}">
        <div class="card m-2" id='comments-<c:out value="${comment.id}" />'>
            <div class="card-header">
                <c:out value="${comment.nickname}" />    <!-- 댓글 닉네임 -->
                <!-- Button trigger modal(댓글 수정 버튼) -->
                <button type="button"
                        class="btn btn-sm btn-outline-primary"
                        data-bs-toggle="modal"
                        data-bs-target="#comment-edit-modal"
                        data-bs-id='<c:out value="${comment.id}" />'
                        data-bs-nickname='<c:out value="${comment.nickname}" />'
                        data-bs-body='<c:out value="${comment.body}" />'
                        data-bs-article-id='<c:out value="${comment.articleId}" />'
                >
                    수정
                </button>
                <!-- 댓글 삭제 버튼-->
                <button type="button"
                    class="btn btn-sm btn-outline-danger comment-delete-btn"
                    data-comment-id='<c:out value="${comment.id}" />'
                >
                    삭제
                </button>
            </div>
            <div class="card-body">
                <c:out value="${comment.body}" />    <!-- 댓글 내용 -->
            </div>
        </div>
    </c:forEach>
</div>

<!-- Modal -->
<div class="modal fade" id="comment-edit-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">댓글 수정</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- 댓글 수정 폼 -->
                <form>
                    <!-- 닉네임 입력 -->
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">닉네임</label>
                        <input type="text" class="form-control" id="edit-comment-nickname" aria-describedby="emailHelp">
                    </div>
                    <!-- 댓글 작성 -->
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">댓글 내용</label>
                        <input type="text" class="form-control" id="edit-comment-body" rows="3">
                    </div>
                    <!-- 히든 input -->
                        <input type="hidden" id="edit-comment-id">
                        <input type="hidden" id="edit-comment-article-id">
                    <!-- 댓글 전송 버튼 -->
                    <button type="button" class="btn btn-primary" id="comment-update-btn">수정 완료</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    // modal 요소를 선택
    const commentEditModal = document.querySelector("#comment-edit-modal");

    // modal이 열릴 때 기존의 값을 세팅하는 이벤트
    commentEditModal.addEventListener("show.bs.modal", function(event) {
           // 1. 트리거 버튼 선택
           const triggerBtn = event.relatedTarget; // Button trigger modal

           // 2. 화면에 보이는 댓글의 각각 데이터가 가져오기
           const id = triggerBtn.getAttribute("data-bs-id");                // id
           const nickname = triggerBtn.getAttribute("data-bs-nickname");    // nickname
           const body = triggerBtn.getAttribute("data-bs-body");            // body
           const articleId = triggerBtn.getAttribute("data-bs-articleId");  // articleId

           // 3. 댓글 수정폼에 데이터 반영
           document.querySelector("#edit-comment-id").value = id;
           document.querySelector("#edit-comment-nickname").value = nickname;
           document.querySelector("#edit-comment-body").value = body;
           document.querySelector("#edit-comment-article-id").value = articleId;
    });

    // 댓글 수정완료 버튼 세팅
    const commentUpdateBtn = document.querySelector("#comment-update-btn");
    commentUpdateBtn.addEventListener("click", function() {
        //console.log("댓글 수정 버튼 클릭");

        // 수정 댓글에 관한 정보를 json 형태로 만들기
        const comment = {
            id: document.querySelector("#edit-comment-id").value,
            nickname: document.querySelector("#edit-comment-nickname").value,
            body: document.querySelector("#edit-comment-body").value,
            articleId: document.querySelector("#edit-comment-article-id").value
        };

        // fetch() -> 비동기 통신을 위한 API
        fetch('/api/replys/' + comment.id, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(comment),
        }).then(function(response) {        // API주소를 호출하고 응답까지 기다린 다음 실행됌
            // 응답 처리문

            const responseMsg = response.ok ? "댓글이 수정되었습니다" : "댓글 수정에 실패했습니다";
            alert(responseMsg);

            // 현재 페이지를 새로고침하는 코드
            window.location.reload();
        });
    });
    // 댓글 삭제 버튼 세팅
    const commentDeleteBtnList = document.querySelectorAll(".comment-delete-btn");
    commentDeleteBtnList.forEach(commentDeleteBtn => {
        commentDeleteBtn.addEventListener("click", (e) => {
            console.log('삭제 버튼이 클릭되었습니다..!');
            const commentDelBtn = e.target
            const commentId = commentDelBtn.getAttribute("data-comment-id");
            console.log(`삭제 버튼 클릭: ${commentId}번 댓글`);

            // fetch() -> 비동기 통신을 위한 API
            fetch('/api/replys/' + commentId, {
                method: 'Delete',
                headers: {
                    'Content-Type': 'application/json'
                },
            }).then(function(response) {        // API주소를 호출하고 응답까지 기다린 다음 실행됌
                // 응답 처리문

                const responseMsg = response.ok ? "댓글이 삭제되었습니다" : "댓글 삭제에 실패했습니다";
                alert(responseMsg);

                // 현재 페이지를 새로고침하는 코드
                window.location.reload();
            });
        });
    });
</script>