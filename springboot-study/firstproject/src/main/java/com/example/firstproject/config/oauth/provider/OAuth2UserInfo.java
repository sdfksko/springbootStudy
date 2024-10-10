package com.example.firstproject.config.oauth.provider;

import java.util.Map;

public interface OAuth2UserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();

}
