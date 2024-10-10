package com.example.firstproject.config.oauth;

import com.example.firstproject.config.auth.SpringSecPrincipalDetails;
import com.example.firstproject.config.oauth.provider.GoogleUserInfo;
import com.example.firstproject.config.oauth.provider.KakaoUserInfo;
import com.example.firstproject.config.oauth.provider.NaverUserInfo;
import com.example.firstproject.config.oauth.provider.OAuth2UserInfo;
import com.example.firstproject.dao.repository.UserRepository;
import com.example.firstproject.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class SpringSecOAuth2PrincipalDetailsService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println("getClientRegistration: " + userRequest.getClientRegistration());
        System.out.println("getAccessToken: " + userRequest.getAccessToken().getTokenValue());
        System.out.println("getAttributes: " + oAuth2User.getAttributes());

        // 회원가입을 진행
        OAuth2UserInfo oAuth2UserInfo = null;
        String providerName = userRequest.getClientRegistration().getRegistrationId();  // google, naver, kakao
        if(providerName.equals("google")) {
            // 구글 로그인 성공 응답 정보 저장
            System.out.println("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());

        } else if(providerName.equals("naver")) {
            // 네이버 로그인 성공 응답 정보 저장
            System.out.println("네이버 로그인 요청");
            oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));

        } else if(providerName.equals("kakao")) {
            // 카카오 로그인 성공 응답정보 저장
            System.out.println("카카오 로그인 요청");
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        } else {
            System.out.println("우리는 구글과 네이버, 카카오 SNS 로그인만 지원해요~");
            return null;
        }

        String provider = oAuth2UserInfo.getProvider(); // google, naver, kakao
        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider + "_" + providerId;
        String password = "-";
        String email = oAuth2UserInfo.getEmail();
        String name = oAuth2UserInfo.getName();
        String role = "ROLE_USER";

        User userEntity = userRepository.findByUsername(username);
        User user = null;
        if(Objects.isNull(userEntity)) {    // 한번도 해당 사이트에 접근하지 않은 사람
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setProvider(provider);
            user.setProviderId(providerId);
            user.setRole(role);

            userRepository.save(user);
        }
        else {
            return new SpringSecPrincipalDetails(userEntity, oAuth2User.getAttributes());
        }

        return new SpringSecPrincipalDetails(user, oAuth2User.getAttributes());
    }
}
