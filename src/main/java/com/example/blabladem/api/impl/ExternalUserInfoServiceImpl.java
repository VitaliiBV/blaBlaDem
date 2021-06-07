package com.example.blabladem.api.impl;

import com.example.blabladem.api.ExternalUserInfoService;
import com.example.blabladem.dto.ExtraUserInfoDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ExternalUserInfoServiceImpl implements ExternalUserInfoService {
    @Override
    public List<ExtraUserInfoDTO> getExtraUserInfo(List<Long> userIds) {
        return Collections.emptyList();
    }
}
