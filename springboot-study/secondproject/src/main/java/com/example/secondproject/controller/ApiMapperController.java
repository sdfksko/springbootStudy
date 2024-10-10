package com.example.secondproject.controller;

import com.example.secondproject.wishlist.WishListDto;
import com.example.secondproject.wishlist.WishListMapperService;
import com.example.secondproject.wishlist.WishListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api2")
@RestController
public class ApiMapperController {
    @Autowired
    private WishListMapperService wishListMapperService;


    // 1. 검색 API(Get)
    @GetMapping("/search")
    public WishListDto search(@RequestParam String searchQuery) {
        WishListDto wishListDto = wishListMapperService.search(searchQuery);

        return wishListDto;
    }

    // 2. 위시리스트 추가 API(POST)
    @PostMapping("/wishadd")
    public List<WishListVO> wishAdd(@RequestBody WishListDto wishListDto) {  // json받아야 하기 때문에
        return wishListMapperService.addWish(wishListDto);
    }

    // 3. 위시리스트 목록 가져오기 API(GET)
    @GetMapping("/wishall")
    public List<WishListVO> wishAll() {
        return wishListMapperService.allWish();
    }

    // 4. 방문 추가 API
    @PostMapping("/wishvisit/{wishId}")
    public boolean wishVisit(@PathVariable("wishId") Integer wishId) {
        return wishListMapperService.addVisitWish(wishId);
    }

    // 5. 위시리스트 삭제 API
    @DeleteMapping("/wishdelete/{wishId}")
    public boolean wishDelete(@PathVariable("wishId") Integer wishId) {
        return wishListMapperService.deleteWish(wishId);
    }
}
