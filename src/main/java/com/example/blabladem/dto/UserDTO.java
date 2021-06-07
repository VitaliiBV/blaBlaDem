package com.example.blabladem.dto;

import com.example.blabladem.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    private String name;

    private DepartmentDTO department;

    public static UserDTO fromEntity(User user){
        if (user==null){
            return null;
        }
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .department(DepartmentDTO.fromEntity(user.getDepartment()))
                .build();
    }
}
