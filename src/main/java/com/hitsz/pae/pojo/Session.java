package com.hitsz.pae.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    private Integer id;//物理IP
    private String phone;//学员id
    private String token;

    public Session(String phone, String jwt) {
        this.phone = phone;
        this.token = jwt;
    }
}