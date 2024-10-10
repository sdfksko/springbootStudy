package com.example.firstproject.controller;

import com.example.firstproject.config.auth.SpringSecPrincipalDetails;
import com.example.firstproject.dao.repository.UserRepository;
import com.example.firstproject.models.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * Desc: 일반 로그인한 유저이면 authentication에 유저에 대한 정보가 저장된 객체 만들어짐
     *       로그인을 하지 않은 유저가 해당 url로 접근을 하면 authentication은 null이 됨
     * @param authentication
     * @return
     */
    @GetMapping("/test/login")
    @ResponseBody
    public String testLogin(
            Authentication authentication,
            @AuthenticationPrincipal SpringSecPrincipalDetails userDetails) {

        if (authentication != null) {
            SpringSecPrincipalDetails springSecPrincipalDetails
                    = (SpringSecPrincipalDetails) authentication.getPrincipal();
            System.out.println("일반 로그인한 유저 정보(authinfo): " + springSecPrincipalDetails.getUser());
            System.out.println("userDetails의 username: " + userDetails.getUser());

            return "세션정보확인";
        }

        return "일반 로그인을 하지 않았습니다.";
    }


    /*
    * Desc: 소셜로그인(oAuth2)한 정보 확인 용ㄷ 테스트
    * @param
    * @return
    * */
    @GetMapping("/test/oauth/login")
    @ResponseBody
    public String testLogin(Authentication authentication) {

        if(authentication != null) {
            OAuth2User oAuth2User
                    = (OAuth2User) authentication.getPrincipal();

            System.out.println("소셜 로그인한 유저 정보(authinfo): " + oAuth2User.getAttributes());

            return "세션정보확인";
        }

        return "소셜 로그인을 하지 않았습니다";
    }


    @GetMapping({"", "/"})
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal SpringSecPrincipalDetails springSecPrincipalDetails) {
        System.out.println("springSecPrincipalDetails -> " + springSecPrincipalDetails.getUser());
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    /*
    * 로그인 화면으로 이동
    *  @return
    * */
    @GetMapping("/loginForm")
    public String loginForm(
            @RequestParam(required = false) String message,
            Model model) {
        model.addAttribute("message", message);
        return "loginForm";
    }

    /*
    * 회원가입
    * @return
    * */
    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    /*
    * 회원가입 처리
    * @return
    * */
    @PostMapping("/join")
//    @ResponseBody               // return값이 string의 값은 json형태로 받겠다.
    public String join(User user) {
        System.out.println(user);

        user.setRole("ROLE_USER");

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        user.setPassword(encPassword);

        userRepository.save(user);  // 회원정보 저장
        return "redirect:/loginForm";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    @ResponseBody
    public String info() {
        return "개인정보";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/data")
    @ResponseBody
    public String data() {
        return "데이터정보";
    }

}