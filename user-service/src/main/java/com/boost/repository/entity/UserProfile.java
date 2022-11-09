package com.boost.repository.entity;
import com.boost.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@Table(name = "tbluserprofile")
//@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class UserProfile implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

    String id;
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
//    @Enumerated(EnumType.STRING)
    @Builder.Default
    Status status= Status.PENDING;
    List<String> follows = new ArrayList<>();
    List<String> followers = new ArrayList<>();







}
