package com.example.blabladem.thirdparty.impl;

import com.example.blabladem.thirdparty.ExternalUserInfoService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class ExternalUserInfoServiceImpl implements ExternalUserInfoService {
    public Map<Long, Long> getExtraUserInfo(Set<Long> userIds) {
        Map<Long, Long> userRating = new HashMap<>();
        userIds
                .forEach(id-> userRating.put(id, ThreadLocalRandom.current().nextLong(0,4)));
        return userRating;
    }
}
