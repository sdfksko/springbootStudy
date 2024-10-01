$(document).ready(function() {
        fetchPosts(0); // 페이지가 로드될 때 첫 페이지의 게시물을 가져옵니다.
});

function fetchPosts(page) {
    $.ajax({
        url: `/api/posts?page=${page}`,
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            updatePosts(data);
        },
        error: function(err) {
            console.error('Error fetching posts:', err);
        }
    });
}

function updatePosts(data) {
    const postList = $('#postList');
    postList.empty(); // 기존 게시물 제거

    $.each(data.content, function(index, post) {
        const li = `<li>
                        <span>${post.id}</span>
                        <span>${post.title}</span>
                        <span>${post.content}</span>
                    </li>`;
        postList.append(li);
    });

    updatePagination(data);
}

function updatePagination(data) {
    const pagination = $('#pagination');
    pagination.empty(); // 기존 페이지네이션 제거

    // 첫 페이지 버튼
    if (data.number > 0) {
        pagination.append(`<li class="page-item"><a class="page-link" href="#" onclick="fetchPosts(0)">첫 페이지</a></li>`);
    }

    // 이전 페이지 버튼
    if (data.number > 0) {
        pagination.append(`<li class="page-item"><a class="page-link" href="#" onclick="fetchPosts(${data.number - 1})">이전 페이지</a></li>`);
    }

    // 페이지 번호
    for (let i = 0; i < data.totalPages; i++) {
        const li = `<li class="page-item ${data.number === i ? 'active' : ''}"><a class="page-link" href="#" onclick="fetchPosts(${i})">${i + 1}</a></li>`;
        pagination.append(li);
    }

    // 다음 페이지 버튼
    if (data.number < data.totalPages - 1) {
        pagination.append(`<li class="page-item"><a class="page-link" href="#" onclick="fetchPosts(${data.number + 1})">다음 페이지</a></li>`);
    }

    // 마지막 페이지 버튼
    if (data.number < data.totalPages - 1) {
        pagination.append(`<li class="page-item"><a class="page-link" href="#" onclick="fetchPosts(${data.totalPages - 1})">마지막 페이지</a></li>`);
    }
}