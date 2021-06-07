package com.example.blabladem.dto;

import com.example.blabladem.domain.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private Long id;
    private String name;

    public static DepartmentDTO fromEntity(Department department){
        if (department==null){
            return null;
        }
        return DepartmentDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }
}
