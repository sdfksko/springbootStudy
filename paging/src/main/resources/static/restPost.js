let listResult = new Vue({
        el: '#list',
        data: {
            list_result: [],  // 게시글 리스트
            totalPages: 1,    // 전체 페이지 수
            currentPage: 0    // 현재 페이지 (0부터 시작)
        },
        methods: {
            // 페이지 이동 함수
            goToPage(page) {
                if (page >= 0 && page < this.totalPages) {
                    this.currentPage = page;
                    this.fetchPosts(page);  // 해당 페이지의 게시글을 가져옴
                }
            },
            // 서버에서 게시글과 페이징 데이터를 가져오는 함수
            fetchPosts(page) {
                $.ajax({
                    type: 'get',
                    url: '/api/posts?page=' + page,
                    contentType: 'application/json',
                    success: (response) => {
                        this.list_result = response.content;  // 게시글 리스트
                        this.totalPages = response.totalPages;  // 전체 페이지 수
                        this.currentPage = response.number;  // 현재 페이지 번호
                    },
                    error: function(err) {
                        console.error('Error fetching posts:', err);
                    }
                });
            }
        },
        mounted() {
            // 페이지가 로드될 때 첫 페이지 데이터를 가져옴
            this.fetchPosts(0);
        }
    });