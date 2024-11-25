package com.hitsz.pae.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    private Integer id;//物理IP
    private Integer stuId;//学员id
    private String token;

    public Session(Integer id, String jwt) {
        this.stuId = id;
        this.token = jwt;
    }
}