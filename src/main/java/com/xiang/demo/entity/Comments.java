package com.xiang.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
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
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 评论日期
     */
    private LocalDateTime commentsDate;

    /**
     * 点赞数
     */
    private Long star;

    /**
     * 评论用户
     */
    private Long userId;

    /**
     * 评论 文章
     */
    private Long articleId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 父评论
     */
    private Long parentCommentsId;

    /**
     * 逻辑删除
     */
    private Integer deleted;

    /*
     * 用户
     * */
    @TableField(exist = false)
    private User commentsUser;


}
