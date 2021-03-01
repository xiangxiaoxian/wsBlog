package com.xiang.demo.controller;

import com.xiang.common.Result;
import com.xiang.demo.entity.User;
import com.xiang.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author XR
 * @version 1.0.0 @ClassName AccountController.java @Description 登录登出相关
 * @createTime 2021年01月09日 17:11:00
 */
@Api(
    value = "AccountController",
    tags = {"登录相关接口"})
@RestController
@RequestMapping
public class AccountController {
  @Resource private UserService userService;

  // 登录接口
  @ApiOperation(value = "登录接口")
  @PostMapping("/login")
  public Result login(@RequestBody User user, HttpServletResponse response) {
    return userService.login(user, response);
  }

  // 登出接口
  @ApiOperation(value = "登出接口")
  @RequiresAuthentication
  @GetMapping("/logout")
  public Result logout() {
    System.out.println("111");
    SecurityUtils.getSubject().logout();
    return Result.success(200, null, null);
  }

  // 注册接口
  @ApiOperation(value = "注册接口")
  @PutMapping("/register")
  public Result register(@RequestBody User user) {
    return userService.registerOrUpdateUserById(user);
  }
  // 验证码接口
  @ApiOperation(value = "验证码接口")
  @PostMapping("/register/validation")
  public Result validation(@RequestBody User user) {
    return userService.validationSend(user.getEmail());
  }

  // 验证码接口
  @ApiOperation(value = "忘记密码验证码发送接口")
  @PostMapping("/forgotPassword")
  public Result forgotPasswordByVerificationCode(@RequestBody User user) {
    return userService.forgotPasswordByVerificationCode(user);
  }
}
