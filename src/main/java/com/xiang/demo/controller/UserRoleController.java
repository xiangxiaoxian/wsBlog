package com.xiang.demo.controller;


import com.xiang.common.Result;
import com.xiang.demo.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  用户角色
 * </p>
 *
 * @author XR
 * @since 2021-01-07
 */
@Api(value = "UserRoleController",tags = {"用户角色相关接口"})
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

}
