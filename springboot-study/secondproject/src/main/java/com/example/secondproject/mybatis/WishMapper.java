package com.example.secondproject.mybatis;

import com.example.secondproject.mapper.WishMapperDto;
import com.example.secondproject.wishlist.WishListVO;

import java.util.List;

public interface WishMapper {
    // 1. 위시리스트 전부 가져오기
    List<WishListVO> getWishVOList();

    // 2. 위시리스트 데이터 입력저장하기(return 값은 들어간 데이터의 행개수)
    int insertWishVO(WishListVO wishListVO);

    // 3. 위시리스트 상세조회
    WishListVO getWishVO(Integer wishId);

    // 4. mapper로 방문 값 업데이트
    int updateVisitWish(Integer wishId);

    // 5. mapper로 특정 값이 들어있는 객체 삭제
    int deleteWishVO(Integer wishId);
}
