package com.example.secondproject.wishlist;

import com.example.secondproject.utils.StringUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishListRepository {
    // List 자바메모리 wish정보
    private List<WishListVO> wishListVOList = new ArrayList<>();

    public List<WishListVO> wishSave(WishListDto wishListDto) {
        // wishListDto -> wishListVo로 변환
        WishListVO wishListVO = new WishListVO();
        wishListVO.setId(wishListVOList.size() + 1);
        wishListVO.setTitle(StringUtils.removeTags(wishListDto.getTitle()));
        wishListVO.setCategory(wishListDto.getCategory());
        wishListVO.setJibunAddress(wishListDto.getJibunAddress());
        wishListVO.setRoadAddress(wishListDto.getRoadAddress());
        wishListVO.setHomepageLink(wishListDto.getHomepageLink());
        wishListVO.setImageLink(wishListDto.getImageLink());

        wishListVO.setVisitIs(false);
        wishListVO.setVisitCount(0);
        wishListVO.setLastVisitDate(null);

        // 나의 위시리스트 메모리 db에 추가 저장
        wishListVOList.add(wishListVO);

        return wishListVOList;
    }

    public List<WishListVO> allWish() {
        return wishListVOList;
    }

    public boolean wishAddVisit(Integer wishId) {
        boolean isAddVisitSuccess = false;

        for(WishListVO wishListVO : wishListVOList) {
            if(wishListVO.getId().equals(wishId)) {
                wishListVO.setVisitIs(true);  // 방문함으로 변경
                wishListVO.setVisitCount(wishListVO.getVisitCount() + 1); // 방문횟수 증가
                wishListVO.setLastVisitDate(StringUtils.setDate()); // 방문일자 저장

                isAddVisitSuccess = true;
                break;
            }
        }
        return isAddVisitSuccess;
    }

    public WishListVO wishDelete(Integer wishId) {
        for(WishListVO wishListVO : wishListVOList) {
            if(wishListVO.getId().equals(wishId)) {
                WishListVO wishListVOClone = wishListVO;
                wishListVOList.remove(wishListVO);
                return wishListVOClone;
            }
        }
        return null;
    }
}
