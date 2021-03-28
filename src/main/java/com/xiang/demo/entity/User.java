package com.xiang.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.Email;

/**
 * <p>
 *
 * </p>
 *
 * @author XR
 * @since 2021-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 逻辑删除
     */
    private Integer deleted;

    /*
    * 角色列表
    * */
    @TableField(exist = false)
    private Role roles;



}
