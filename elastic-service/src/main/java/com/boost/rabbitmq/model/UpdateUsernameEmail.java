package com.boost.rabbitmq.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateUsernameEmail implements Serializable {
    private String username;
    private String email;
    private Long authid;
}
