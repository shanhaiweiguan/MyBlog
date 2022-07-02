package com.example.myblog.service.impl;

import com.example.myblog.VO.ErrorCode;
import com.example.myblog.VO.LoginParam;
import com.example.myblog.VO.Result;
import com.example.myblog.entity.User;
import com.example.myblog.repository.UserRepository;
import com.example.myblog.service.LoginService;
import com.example.myblog.utils.JwtUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialStruct;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepository userRepository;

    //
    private static final String salt = "hzdzkjdx";


    @Override
    public Result login(LoginParam loginParam) {
        /**
         * 1.检查参数合规性//
         * 2.根据用户名和密码去user表里进行查询
         * 3.如果不存在，登陆失败
         * 4.存在的话使用jwt生成token，返回给前端数据token
         * 5.将token放入redis里面设置过期时间60分钟
         */
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        //
        //密码加盐
        String pwd = DigestUtils.md5Hex(password + salt);
        //.2判断是否用户存在
        User user = userRepository.findUserByUsername(username);
        if (user == null){
            return Result.fail(ErrorCode.USER_NOT_FIND.getCode(), ErrorCode.USER_NOT_FIND.getMsg());
        }
        String result = user.getPassword();
        //.3判断密码是否错误
        if (!pwd.equals(result)){
            return Result.fail(ErrorCode.PWD_ERROR.getCode(), ErrorCode.PWD_ERROR.getMsg());
        }
        String token = JwtUtils.createToken(username);

        //写进redis中，并设置过期时间
        return Result.success(token);
    }
}
