package com.xiang.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Mapper 接口
 *
 * @author XR
 * @since 2021-01-07
 */
public interface UserMapper extends BaseMapper<User> {

  User login(@Param(Constants.WRAPPER) QueryWrapper<Map<String, Object>> wrapper);

  User getUserById(Long id);

  Page<User> selectAllUserBySearchField(Page page,@Param(Constants.WRAPPER) QueryWrapper<User> queryWrapper);
}
