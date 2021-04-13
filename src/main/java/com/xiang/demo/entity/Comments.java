package com.xiang.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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
     * 父评论昵称
     */
    private String parentCommentsNickName;

    /**
     * 逻辑删除
     */
    private Integer deleted;

    /*
     * 用户
     * */
    @TableField(exist = false)
    private User user;

    /*
    * 回复评论
    * */
    @TableField(exist = false)
    private List<Comments> commentsReply;
}
