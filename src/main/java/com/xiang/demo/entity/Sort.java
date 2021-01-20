package com.xiang.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

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
public class Sort implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分类名
     */
    private String sortName;

    /**
     * 分类注释
     */
    private String sortComments;

    /**
     * 逻辑删除
     */
    private Integer deleted;


}
