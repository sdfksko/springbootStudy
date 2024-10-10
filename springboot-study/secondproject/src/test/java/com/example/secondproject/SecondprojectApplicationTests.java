package com.example.secondproject;

import com.example.secondproject.naver.dto.SearchImageRequestDto;
import com.example.secondproject.naver.dto.SearchImageResponseDto;
import com.example.secondproject.naver.dto.SearchRegionRequestDto;
import com.example.secondproject.naver.dto.SearchRegionResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.secondproject.naver.NaverAPIClient;

@SpringBootTest
class SecondprojectApplicationTests {

	@Autowired
	private NaverAPIClient naverAPIClient;

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("네이버 지역검색 OpenAPI 테스트")
	void naverSearchRegionAPITest() {
		// 1. 요청 dto 파라미터 세팅
		SearchRegionRequestDto searchRegionRequestDto = new SearchRegionRequestDto();
		searchRegionRequestDto.setQuery("아구찜");

		// 2. 실제 네이버 지역검색 OpenAPI 호출
		SearchRegionResponseDto searchRegionResponseDto
				= naverAPIClient.searchRegion(searchRegionRequestDto);
		System.out.println("네이버 지역검색 OpenAPI response json " + searchRegionResponseDto);
	}


	@Test
	@DisplayName("네이버 이미지검색 OpenAPI 테스트")
	void naverSearchImageAPITest() {
		// 1. 요청 dto 파라미터 세팅
		SearchImageRequestDto searchImageRequestDto = new SearchImageRequestDto();
		searchImageRequestDto.setQuery("고양이");

		// 2. 실제 네이버 이미지검색 OpenAPI 호출
		SearchImageResponseDto searchImageResponseDto
				= naverAPIClient.searchImage(searchImageRequestDto);
		System.out.println("네이버 이미지검색 OpenAPI response json " + searchImageResponseDto);
	}
}
