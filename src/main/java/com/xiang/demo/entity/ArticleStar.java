package com.xiang.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ArticleStar.java
 * @Description TODO
 * @createTime 2021年03月20日 15:32:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class ArticleStar implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 状态
     */
    private Integer status;
}
