package com.boost.dto.response;

import com.boost.repository.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleResponseDto {
    private Long id;
    private String username;
    private String role;
}
