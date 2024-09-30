
$(document).ready(function() {
    fetchPosts(0);
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
    const postList = $('ul');
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
    const pagination = $('.pagination');
    pagination.empty(); // 기존 페이지네이션 제거

    for (let i = 0; i < data.totalPages; i++) {
        const li = `<li class="page-item">
                        <a class="page-link" href="#" onclick="fetchPosts(${i})">${i + 1}</a>
                    </li>`;
        pagination.append(li);
    }
}

