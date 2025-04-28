package com.github.hanjungho.oauth2jwttest.domain.dto;

import java.util.Map;

public class NaverResponse implements OAuth2Response{

    private final Map<String, Object> attribute;

    public NaverResponse(Map<String, Object> attribute) {

        this.attribute = (Map<String, Object>) attribute.get("response");
    }

    @Override
    public String getProvider() {

        return "naver";
    }

    @Override
    public String getProviderId() {

        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        // kakao_account에서 이메일 정보를 가져옵니다
        return (attribute != null && attribute.containsKey("email")) ?
                attribute.get("email").toString() : null;
    }

    @Override
    public String getName() {
        // properties에서 nickname 정보를 가져옵니다
        return (attribute != null && attribute.containsKey("name")) ?
                attribute.get("name").toString() : null;
    }
}
