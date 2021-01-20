package com.xiang.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiang.common.Result;
import com.xiang.demo.entity.User;
import com.xiang.demo.entity.UserRole;
import com.xiang.demo.mapper.UserRoleMapper;
import com.xiang.demo.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  用户角色服务实现类
 * </p>
 *
 * @author XR
 * @since 2021-01-07
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
