package com.xiang.demo.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.Article;
import com.xiang.demo.entity.RolePermission;
import com.xiang.demo.entity.User;
import com.xiang.demo.entity.UserRole;
import com.xiang.demo.mapper.ArticleMapper;
import com.xiang.demo.mapper.UserMapper;
import com.xiang.demo.mapper.UserRoleMapper;
import com.xiang.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiang.utils.JwtUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 服务实现类
 *
 * @author XR
 * @since 2021-01-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
  @Resource private UserMapper userMapper;

  @Resource private JwtUtils jwtUtils;

  @Resource private UserRoleMapper userRoleMapper;

  @Resource private ArticleMapper articleMapper;

  // 登录方法实现
  @Override
  public Result login(User user, HttpServletResponse response) {
    QueryWrapper<Map<String, Object>> wrapper = new QueryWrapper<>();
    wrapper
        .eq("username", user.getUsername())
        .or()
        .eq("email", user.getUsername())
        .or()
        .eq("phone_number", user.getUsername());
    User loginUser = userMapper.login(wrapper); // 按照用户名或邮箱或电话号码查询账号信息
    if (ObjectUtils.isEmpty(loginUser)) {
      return Result.error(400, "用户不存在");
    } else if (!loginUser.getPassword().equals(SecureUtil.md5(user.getPassword()))) {
      return Result.error(400, "密码不正确");
    }
    if (-1 == loginUser.getStatus()) {
      return Result.error(400, "账户以被锁定，请联系管理员进行解锁");
    }

    String jwt = jwtUtils.generateToken(loginUser.getId());

    response.setHeader("Authorization", jwt);
    response.setHeader("Access-control-Expose-Headers", "Authorization");
    return Result.success(200, "登录成功", loginUser);
  }

  // 注册方法或者资料修改实现
  @Override
  @Transactional
  public Result registerOrUpdateUserById(User user) {
    // 对密码进行md5加密
    user.setPassword(SecureUtil.md5(user.getPassword()));
    if (null == user.getId()) { // 根据实体有无id进行资料修改或注册判断
      UserRole userRole = new UserRole();
      QueryWrapper<User> wrapper = new QueryWrapper<User>();
      wrapper.lambda().eq(User::getUsername, user.getUsername()); // 根据用户名查询当前用户是否被注册
      if (!ObjectUtils.isEmpty(userMapper.selectOne(wrapper))) {
        return Result.error(400, "账号已经被注册使用");
      }
      userMapper.insert(user);
      userRole.setUserId(userMapper.selectOne(wrapper).getId()); // 对注册的用户进行角色分配
      userRole.setRoleId(new Long(3));
      userRoleMapper.insert(userRole);
      return Result.success(200, "注册成功", user);
    } else {
      userMapper.updateById(user);
      return Result.success(200, "修改成功", user);
    }
  }

  // 按照id查找当前登录用户资料信息
  @Override
  public Result getUserById(Long id) {
    return Result.success(200, "成功查找", userMapper.getUserById(id));
  }

  // 按照id删除单个用户
  @Override
  @Transactional
  public Result deleteUserById(Long id) {
    // 判断该角色下是否拥有文章
    QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
    articleQueryWrapper.eq("user_id", id).eq("deleted",0);
    List<Article> articleList = articleMapper.selectList(articleQueryWrapper);
    if (!ObjectUtils.isEmpty(articleList)) {
      return Result.error(400, "该账户下存在博文,无法删除");
    }
    // 删除之前先删除有关该账号的所有相关的资料信息
    // 删除相关角色信息
    QueryWrapper<UserRole> wrapperByUserRole = new QueryWrapper<>();
    wrapperByUserRole.eq("user_id", id);
    userRoleMapper.delete(wrapperByUserRole);
    return Result.success(200, "删除成功", userMapper.deleteById(id));
  }

  // 按照id批量删除多个用户
  @Override
  @Transactional
  public Result deleteUsersByBatchId(Collection<Long> batchIds) {
    // 查询角色下是否有文章
    List<Article> articleList = articleMapper.selectArticleByUserBatchIds(batchIds);
    // 删除相关角色信息
    userRoleMapper.deleteUserRoleByBatchIds(batchIds);
    return Result.success(200, "批量删除成功", userMapper.deleteBatchIds(batchIds));
  }

  // 查询所有用户并进行分页处理
  @Override
  public Result getAllUserAndPages(Page page,String searchField) {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("nick_name",searchField);
    return Result.success(200, "查询成功", (Page<User>) userMapper.selectMapsPage(page, queryWrapper));
  }

  // 给用户分配角色
  @Override
  @Transactional
  public Result assignRoles(Long userId, Collection<Long> batchRoleIds) {
    // 删除前先删除该用户之前拥有的角色列表
    QueryWrapper<UserRole> wrapperByUserRole = new QueryWrapper<>();
    wrapperByUserRole.eq("user_id", userId);
    userRoleMapper.delete(wrapperByUserRole);
    // 分配新的角色
    UserRole userRole=new UserRole();
    userRole.setUserId(userId);
    for (Long roleId : batchRoleIds) {
      userRole.setRoleId(roleId);
      userRoleMapper.insert(userRole);
    }
    return Result.success(200,"分配成功",null);
  }
}
