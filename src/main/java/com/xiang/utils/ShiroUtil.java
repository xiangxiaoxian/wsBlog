package com.xiang.utils;


import com.xiang.demo.entity.User;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    public static User getProfile() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

}
