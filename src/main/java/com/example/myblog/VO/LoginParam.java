package com.example.myblog.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginParam {
    private String username;
    private String password;
}
