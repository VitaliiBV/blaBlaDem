package com.example.blabladem.thirdparty;

import java.util.Map;
import java.util.Set;

public interface ExternalUserInfoService {
    Map<Long, Long> getExtraUserInfo(Set<Long> userIds);
}
