package com.example.blabladem.api.impl;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class ExternalUserInfoServiceImpl {
    public Map<Long, Long> getExtraUserInfo(Set<Long> userIds) {
        Map<Long, Long> userRating = new HashMap<>();
        userIds
                .forEach(id-> userRating.put(id, ThreadLocalRandom.current().nextLong(0,4)));
        return userRating;
    }
}
