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
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限名
     */
    private String permissionName;

    /**
     * 权限注释
     */
    private String permissionComments;

    /**
     * 权限地址
     */
    private String address;

    /**
     * 逻辑删除
     */
    private Integer deleted;

    /*
     * 角色列表
     * */
    @TableField(exist = false)
    private List<Role> roles;


}
