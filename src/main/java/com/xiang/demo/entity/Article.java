package com.xiang.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;

/**
 * @author XR
 * @since 2021-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class Article implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 主键 */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** 发布用户id */
  private Long userId;

  /** 文章标题 */
  private String title;

  /** 文章内容 */
  private String content;

  /** 发布时间 */
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime pubTime;

  /** 点赞数 */
  private Long star;

  /** 回复数 */
  private Long reply;

  /** 浏览量 */
  private Long browse;

  /** 逻辑删除 */
  private Integer deleted;

  /*
   * 用户
   * */
  @TableField(exist = false)
  private User user;

  /*
   * 评论
   * */
  @TableField(exist = false)
  private List<Comments> comments;

  /*
   * 分类
   * */
  @TableField(exist = false)
  private Sort sort;
  /*
   * 标签
   * */
  @TableField(exist = false)
  private Lable lable;
}
