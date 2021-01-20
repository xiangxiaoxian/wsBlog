package com.xiang.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.xiang.demo.entity.User;
import com.xiang.demo.service.UserService;
import com.xiang.utils.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;

/**
 * @author XR
 * @version 1.0.0
 * @ClassName AccountRealm.java
 * @Description TODO
 * @createTime 2021年01月09日 13:49:00
 */
@Component
public class AccountRealm extends AuthorizingRealm {
    @Resource
    JwtUtils jwtUtils;

    @Resource
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;

        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();

        User user = userService.getById(Long.valueOf(userId));
        if (user == null) {
            throw new UnknownAccountException("账户不存在");
        }

        if (user.getStatus() == -1) {
            throw new LockedAccountException("账户已被锁定");
        }


        return new SimpleAuthenticationInfo(user, jwtToken.getCredentials(), getName());

    }
}
