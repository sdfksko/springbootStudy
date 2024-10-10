package com.example.secondproject.mybatis;

import com.example.secondproject.mapper.WishMapperDto;
import com.example.secondproject.wishlist.WishListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WishMapper2 {
    // mapper로 값 저장
    void saveWish(WishListDto wishListDto);

    // mapper로 리스트 조회
    List<WishMapperDto> getWishList();

    // mapper로 특정 값이 들어있는 객체 조회
    WishMapperDto getWish(Integer wishId);

    // mapper로 방문 값 업데이트
    void updateVisitWish(Integer wishId);

    // mapper로 특정 값이 들어있는 객체 삭제
    void deleteWish(Integer wishId);

}
