package com.github.hanjungho.oauth2jwttest.domain.dto;

import java.util.Map;

public class KakaoResponse implements OAuth2Response {

    private final Map<String, Object> attributes;
    private final Map<String, Object> properties;

    public KakaoResponse(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.properties = (Map<String, Object>) attributes.get("properties");
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getEmail() {
        return null; // 카카오 정책상 이메일은 따로 scope 요청해야 받을 수 있음
    }

    @Override
    public String getName() {
        return properties.get("nickname").toString();
    }
}
