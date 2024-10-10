package com.example.firstproject.config.oauth.provider;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo{
    private Map<String, Object> attrs;

    public KakaoUserInfo(Map<String, Object> attrs) {
        this.attrs = attrs;
    }

    @Override
    public String getProviderId() {
        return attrs.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getName() {
        return (String)((Map)attrs.get("properties")).get("nickname");
    }
}
