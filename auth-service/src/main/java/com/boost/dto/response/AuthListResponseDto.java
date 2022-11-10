package com.boost.dto.response;


import com.boost.repository.enums.Roles;
import com.boost.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthListResponseDto {

    private Long id;
    private String username;
    private String email;
    private Roles role;
    private Status status;


}