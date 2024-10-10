package com.example.secondproject.wishlist;

import com.example.secondproject.jpa.Wish;
import com.example.secondproject.jpa.WishRepository;
import com.example.secondproject.mybatis.WishMapper;
import com.example.secondproject.naver.NaverAPIClient;
import com.example.secondproject.naver.dto.SearchImageRequestDto;
import com.example.secondproject.naver.dto.SearchImageResponseDto;
import com.example.secondproject.naver.dto.SearchRegionRequestDto;
import com.example.secondproject.naver.dto.SearchRegionResponseDto;
import com.example.secondproject.utils.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {
    @Autowired
    private NaverAPIClient naverAPIClient;

    @Autowired
    private WishListRepository wishListRepository;  // 자바 메모리로 crud

    @Autowired
    private WishRepository wishRepository;      //  jpa를 이용해서 rdb에 있는 wish테이블을 crud

    @Autowired
    private WishMapper wishMapper;

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

    public List<WishListVO> addWishFromMapper(WishListDto wishListDto) {
        WishListVO wishListVO = new WishListVO();

        wishListVO.setTitle(StringUtils.removeTags(wishListDto.getTitle()));
        wishListVO.setCategory(wishListDto.getCategory());
        wishListVO.setJibunAddress(wishListDto.getJibunAddress());
        wishListVO.setRoadAddress(wishListDto.getRoadAddress());
        wishListVO.setHomepageLink(wishListDto.getHomepageLink());
        wishListVO.setImageLink(wishListDto.getImageLink());

        wishListVO.setVisitIs(false);
        wishListVO.setVisitCount(0);
        wishListVO.setLastVisitDate(null);

        // 실제 DB에 저장입력
        wishMapper.insertWishVO(wishListVO);

        // 다시 위시리스트에 전체 조회
        return allWishFromMapper();
    }


    public List<WishListVO> addWishFromJpa(WishListDto wishListDto) {

        // dto -> entity로 변경: parameter로 받은 dto를 entity로 변경
        Wish wish = new Wish();
        wish.setTitle(StringUtils.removeTags(wishListDto.getTitle()));
        wish.setCategory(wishListDto.getCategory());
        wish.setJibunAddress(wishListDto.getJibunAddress());
        wish.setRoadAddress(wishListDto.getRoadAddress());
        wish.setHomepageLink(wishListDto.getHomepageLink());
        wish.setImageLink(wishListDto.getImageLink());

        wish.setVisitIs(false);
        wish.setVisitCount(0);
        wish.setLastVisitDate(null);

        Wish newWish = wishRepository.save(wish);

        List<WishListVO> wishListVOList = new ArrayList<>();
        List<Wish> wishEntityList = wishRepository.findAll();

        // entity -> vo로 변경: 만들어진 table의 데이터인 entity의 리스트를 vo 리스트로 변경
        for(Wish copyWish : wishEntityList) {
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

    // wish storage의 모든 데이터 가져오기(mybatis)
    public List<WishListVO> allWishFromMapper() {
        List<WishListVO> wishListVOList = wishMapper.getWishVOList();

        return wishListVOList;
    }

    // wish storage의 모든 데이터 가져오기(jpa)
    public List<WishListVO> allWishFromJpa(int idx) {
        Pageable pg = PageRequest.of(idx, 5, Sort.by(Sort.Direction.DESC, "id"));
        List<WishListVO> wishListVOList = new ArrayList<>();
        Page<Wish> wishEntityList = wishRepository.findAll(pg);

        // entity -> vo로 변경: 만들어진 table의 데이터인 entity의 리스트를 vo 리스트로 변경
        for(Wish copyWish : wishEntityList) {
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
    public boolean addVisitWishFromMapper(Integer wishId) {
        boolean isAddVisitSuccess = false;
        WishListVO wishListVO = wishMapper.getWishVO(wishId);
        if(wishListVO.getId().equals(wishId)) {
            wishMapper.updateVisitWish(wishId);
            isAddVisitSuccess = true;
        }
        return isAddVisitSuccess;
    }

    @Transactional
    public boolean addVisitWishFromJpa(Integer wishId) {
        boolean isAddVisitSuccess = false;

        List<Wish> wishList = wishRepository.findAll();

        for(Wish wish : wishList) {
            if(wish.getId().equals(wishId)) {
                wish.setVisitIs(true);  // 방문함으로 변경
                wish.setVisitCount(wish.getVisitCount() + 1); // 방문횟수 증가
                wish.setLastVisitDate(StringUtils.setDate()); // 방문일자 저장

                isAddVisitSuccess = true;
                break;
            }
        }

        return isAddVisitSuccess;
        //return wishListRepository.wishAddVisit(wishId);
    }

    public boolean deleteWishFromMapper(Integer wishId) {
        boolean isAddVisitSuccess = false;
        WishListVO wishListVO = wishMapper.getWishVO(wishId);
        if(wishListVO.getId().equals(wishId)) {
            wishMapper.deleteWishVO(wishId);
            isAddVisitSuccess = true;
        }
        return isAddVisitSuccess;
    }

    public boolean deleteWishFromJpa(Integer wishId) {
        boolean isAddVisitSuccess = false;
        Wish wish = wishRepository.findById(wishId).orElse(null);
        if(wish.getId().equals(wishId)) {
            wishRepository.delete(wish);
            isAddVisitSuccess = true;
        }
        return isAddVisitSuccess;
        //return wishListRepository.wishDelete(wishId);
    }
}
