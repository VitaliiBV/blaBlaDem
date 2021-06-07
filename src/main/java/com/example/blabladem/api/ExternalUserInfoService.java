package com.example.blabladem.api;

import com.example.blabladem.dto.ExtraUserInfoDTO;

import java.util.List;

public interface ExternalUserInfoService {
    List<ExtraUserInfoDTO> getExtraUserInfo(List<Long> userIds);
}
