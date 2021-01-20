package com.xiang.shiro;


import org.apache.shiro.authc.AuthenticationToken;


/**
 * @author XR
 * @version 1.0.0
 * @ClassName JwtToken.java
 * @Description TODO
 * @createTime 2021年01月09日 13:59:00
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String jwt) {
        this.token = jwt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
