// console.log('hello');

// 화면결과용의 Vue 객체 생성

let idx = 0;

var searchResult = new Vue({
    el: '#search-result',
    data: {
        search_result: {},
    },
});

// 위시리스트 결과용의 Vue 객체 생성
var wishListResult = new Vue({
    el: '#wish-list-result',
    data: {
        wish_list: {},
    },
    methods: {
        deleteWishList(wishId) {
            //위시리스트 삭제 요청하기
            console.log('delete wishId', wishId);

            $.ajax({
                type: 'delete',
                url: '/api/wishdelete/' + wishId,
                success: function(response, status, xhr) {
                    console.log('해당 리스트 삭제 완료', response);
                    alert('해당 리스트 삭제에 성공하였습니다');

                    $.get(`/api/wishall/${idx}`, function(response) {
                        wishListResult.wish_list = response;
                    });
                },
                error: function(request, status, error) {
                    alert('해당 리스트 삭제에 실패하였습니다');
                },
            });
        },
        addVisitCount(wishId) {
            console.log('add visit count wishId', wishId);

            $.ajax({
                type: 'post',
                url: '/api/wishvisit/' + wishId,
                success: function(response, status, xhr) {
                    console.log('방문수 증가 완료', response);
                    //alert('방문수 증가가 성공하였습니다');

                    // 위시리스트 목록 가져오기
                    $.get(`/api/wishall/${idx}`, function(response) {
                        wishListResult.wish_list = response;
                    });
                },
                error: function(request, status, error) {
                    alert('방문수 증가가 실패하였습니다');
                },
            });
        }
    },
});

// 엔터 키를 눌렀을 때 이벤트
$('#searchBox').keyup(function(e) {
    if (e.keyCode == 13) {
        $('#searchButton').click();
    }
});

// 검색 버튼을 눌렀을 때 이벤트
$('#searchButton').click(function() {
    console.log('search btn click');

    const query = $('#searchBox').val();
    // 실제 backend에 /api/search 요청해서 데이터 가져오기(ajax)
    $.get('/api/search?searchQuery=' + query, function(response) {
        console.log('search response값', response);

        searchResult.search_result = response;
        const title = document.getElementById('wish-title');
        if(title)
            title.innerHTML = searchResult.search_result.title.replace(/<^>]*>?/g, '');

        $('#search-result').show();
    });
});

// 위시리스트 추가 버튼을 눌렀을 때
$('#wish-button').click(function() {
    console.log('wish btn click');

    // ajax 비동기로 위시리스트 내용을 post로 요청
    $.ajax({
        type: 'post',           // http method를 post로 요청
        url: '/api/wishadd',    // api url
        contentType: 'application/json',
        data: JSON.stringify(searchResult.search_result),   // Vue객체인 searchResult의 search_result를 json으로 변환
        success: function(response, status, xhr) {
            console.log('위시리스트 추가 완료', response);   // WishListVO

            wishListResult.wish_list = response;  // response는 list로 되어있음

            // 위시리스트 목록 가져오기
            $.get(`/api/wishall/${idx}`, function(response) {  // 백틱(`)을 사용한 문자열 보간
                wishListResult.wish_list = response;
                console.log(response);
                $('#wish-list-result').show();
            });

            //alert('위시리스트 추가가 성공하였습니다');
        },
        error: function(request, status, error) {
            alert('위시리스트 추가가 실패하였습니다');
        },
    });
});

$(document).ready(function() {
    console.log('jquery ready');
    $('#search-result').hide(); // 검색결과를 숨김
    $('#wish-list-result').hide(); // 위시리스트를 숨김

    // 위시리스트 목록 가져오기
    $.get(`/api/wishall/${idx}`, function(response) {
        wishListResult.wish_list = response;
        console.log(response);
        $('#wish-list-result').show();
    });
});

$('#prev').click(function() {
    console.log('prev 클릭');
    if(idx != 0) {
        idx -= 1;
    }
    // 위시리스트 목록 가져오기
    $.get(`/api/wishall/${idx}`, function(response) {
        wishListResult.wish_list = response;
        console.log(response);
    });
    console.log(idx);
});

$('#next').click(function() {
    console.log('next 클릭');
    idx += 1;
    console.log(idx);
    // 위시리스트 목록 가져오기
    $.get(`/api/wishall/${idx}`, function(response) {
        wishListResult.wish_list = response;
        console.log(response);
    });
});

$('.page').click(function() {
    let pageEl = $(this).html();
    idx = pageEl - 1;
    console.log(pageEl);
    // 위시리스트 목록 가져오기
    $.get(`/api/wishall/${idx}`, function(response) {
        wishListResult.wish_list = response;
        console.log(response);
    });
});