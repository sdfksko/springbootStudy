package com.example.secondproject.wishlist;

import com.example.secondproject.mapper.WishMapperDto;
import com.example.secondproject.mybatis.WishMapper2;
import com.example.secondproject.naver.NaverAPIClient;
import com.example.secondproject.naver.dto.SearchImageRequestDto;
import com.example.secondproject.naver.dto.SearchImageResponseDto;
import com.example.secondproject.naver.dto.SearchRegionRequestDto;
import com.example.secondproject.naver.dto.SearchRegionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListMapperService {
    @Autowired
    private NaverAPIClient naverAPIClient;

    @Autowired
    private WishMapper2 wishMapper2;

    // Naver API를 호출
    public WishListDto search(String paramQuery) {
        WishListDto wishListDto = new WishListDto();

        // 1. NaverAPI 지역검색 호출해서 dto값 매핑 코드
        SearchRegionRequestDto searchRegionRequestDto = new SearchRegionRequestDto();
        searchRegionRequestDto.setQuery(paramQuery);

        SearchRegionResponseDto searchRegionResponseDto = naverAPIClient.searchRegion(searchRegionRequestDto);
        // searchRegionResponseDto -> WishListDto에 매핑
        List<SearchRegionResponseDto.SearchRegionItem> searchRegionItemList = searchRegionResponseDto.getItems();
        if (searchRegionItemList != null && searchRegionItemList.size() > 0) {
            SearchRegionResponseDto.SearchRegionItem searchRegionItem = searchRegionItemList.get(0);

            wishListDto.setTitle(searchRegionItem.getTitle());
            wishListDto.setCategory(searchRegionItem.getCategory());
            wishListDto.setJibunAddress(searchRegionItem.getAddress());
            wishListDto.setRoadAddress(searchRegionItem.getRoadAddress());
            wishListDto.setHomepageLink(searchRegionItem.getLink());
        }

        // 2. NaverAPI 이미지검색 호출해서 dto값 매칭 코드
        SearchImageRequestDto searchImageRequestDto = new SearchImageRequestDto();
        searchImageRequestDto.setQuery(paramQuery);

        SearchImageResponseDto searchImageResponseDto = naverAPIClient.searchImage(searchImageRequestDto);
        // searchImageResponseDto -> WishListDto에 매핑
        List<SearchImageResponseDto.SearchImageItem> searchImageItemList = searchImageResponseDto.getItems();
        if (searchImageItemList != null && searchImageItemList.size() > 0) {
            SearchImageResponseDto.SearchImageItem searchImageItem = searchImageItemList.get(0);

            wishListDto.setImageLink(searchImageItem.getLink());
        }
        return wishListDto;
    }

    @Transactional
    public List<WishListVO> addWish(WishListDto wishListDto) {
        wishMapper2.saveWish(wishListDto);

        List<WishListVO> wishListVOList = new ArrayList<>();
        List<WishMapperDto> wishList = wishMapper2.getWishList();

        for(WishMapperDto copyWish : wishList) {
            WishListVO wishListVO = new WishListVO();

            wishListVO.setId(copyWish.getId());
            wishListVO.setTitle(copyWish.getTitle());
            wishListVO.setCategory(copyWish.getCategory());
            wishListVO.setJibunAddress(copyWish.getJibunAddress());
            wishListVO.setRoadAddress(copyWish.getRoadAddress());
            wishListVO.setHomepageLink(copyWish.getHomepageLink());
            wishListVO.setImageLink(copyWish.getImageLink());

            wishListVO.setVisitIs(copyWish.getVisitIs());
            wishListVO.setVisitCount(copyWish.getVisitCount());
            wishListVO.setLastVisitDate(copyWish.getLastVisitDate());

            wishListVOList.add(wishListVO);
        }


        return wishListVOList;

        // Storage(DB, Memory, dto에 저장)
        //return wishListRepository.wishSave(wishListDto);
    }

    public List<WishListVO> allWish() {
        List<WishListVO> wishListVOList = new ArrayList<>();
        List<WishMapperDto> wishList = wishMapper2.getWishList();
        for(WishMapperDto copyWish : wishList) {
            WishListVO wishListVO = new WishListVO();

            wishListVO.setId(copyWish.getId());
            wishListVO.setTitle(copyWish.getTitle());
            wishListVO.setCategory(copyWish.getCategory());
            wishListVO.setJibunAddress(copyWish.getJibunAddress());
            wishListVO.setRoadAddress(copyWish.getRoadAddress());
            wishListVO.setHomepageLink(copyWish.getHomepageLink());
            wishListVO.setImageLink(copyWish.getImageLink());

            wishListVO.setVisitIs(copyWish.getVisitIs());
            wishListVO.setVisitCount(copyWish.getVisitCount());
            wishListVO.setLastVisitDate(copyWish.getLastVisitDate());

            wishListVOList.add(wishListVO);
        }

        return wishListVOList;
    }

    @Transactional
    public boolean addVisitWish(Integer wishId) {
        boolean isAddVisitSuccess = false;
        WishMapperDto wishMapperDto = wishMapper2.getWish(wishId);

        if(wishMapperDto.getId().equals(wishId)) {
            wishMapper2.updateVisitWish(wishId);
            isAddVisitSuccess = true;
        }

        return isAddVisitSuccess;
        //return wishListRepository.wishAddVisit(wishId);
    }

    public boolean deleteWish(Integer wishId) {
        boolean isAddVisitSuccess = false;
        WishMapperDto wishMapperDto = wishMapper2.getWish(wishId);

        if(wishMapperDto.getId().equals(wishId)) {
            wishMapper2.deleteWish(wishId);
            isAddVisitSuccess = true;
        }
        return isAddVisitSuccess;
        //return wishListRepository.wishDelete(wishId);
    }
//    public WishListVO deleteWish(Integer wishId) {
//        Wish wish = wishRepository.findById(wishId).orElse(null);
//        WishListVO wishListVO = new WishListVO();
//
//        wishListVO.setId(wish.getId());
//        wishListVO.setTitle(wish.getTitle());
//        wishListVO.setCategory(wish.getCategory());
//        wishListVO.setJibunAddress(wish.getJibunAddress());
//        wishListVO.setRoadAddress(wish.getRoadAddress());
//        wishListVO.setHomepageLink(wish.getHomepageLink());
//        wishListVO.setImageLink(wish.getImageLink());
//
//        wishListVO.setVisitIs(wish.getVisitIs());
//        wishListVO.setVisitCount(wish.getVisitCount());
//        wishListVO.setLastVisitDate(wish.getLastVisitDate());
//
//        wishRepository.delete(wish);
//        return wishListVO;
//        //return wishListRepository.wishDelete(wishId);
//    }
}
