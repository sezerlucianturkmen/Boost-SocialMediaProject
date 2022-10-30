package com.boost.repository.entity;
import com.boost.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "tbluserprofile")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserProfile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long authid;
    String username;
    String name;
    String email;
    String phone;
    String photo;
    String address;
    String about;
    @Builder.Default
    Long created=System.currentTimeMillis();
    Long updated;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    Status status= Status.PENDING;







}
