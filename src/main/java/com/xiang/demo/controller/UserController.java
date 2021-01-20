package com.xiang.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.User;
import com.xiang.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author XR
 * @version 1.0.0 @ClassName UserController.java @Description 用户相关
 * @createTime 2021年01月09日 17:11:00
 */
@Api(
    value = "UserController",
    tags = {"用户相关接口"})
@RestController
@RequestMapping("/user")
public class UserController {
  @Resource private UserService userService;

  @ApiOperation(value = "按照id查找当前登录用户资料信息")
  @GetMapping("/{id}")
  public Result getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @ApiOperation(value = "按照id修改当前用户资料信息,包括角色信息")
  @PutMapping()
  public Result updateUserById(@RequestBody User user) {
    return userService.registerOrUpdateUserById(user);
  }

  @ApiOperation(value = "按照id删除单个用户")
  @DeleteMapping("/{id}")
  public Result deleteUserById(@PathVariable Long id) {
    return userService.deleteUserById(id);
  }

  @ApiOperation(value = "按照id批量删除多个用户")
  @DeleteMapping()
  public Result deleteUsersByBatchId(@RequestBody Collection<Long> batchIds) {
    return userService.deleteUsersByBatchId(batchIds);
  }

  @ApiOperation(value = "查询所有用户资料并分页")
  @PostMapping()
  public Result getAllUserAndPages(@RequestBody Page page,@RequestParam String searchField) {
    return userService.getAllUserAndPages(page,searchField);
  }

  @ApiOperation(value = "给用户分配角色")
  @PostMapping("/userRole/{userId}")
  public Result assignRoles(@PathVariable Long userId, @RequestBody Collection<Long> batchRoleIds) {
    return userService.assignRoles(userId, batchRoleIds);
  }


}
