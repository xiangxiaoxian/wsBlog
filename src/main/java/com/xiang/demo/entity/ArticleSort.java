package com.xiang.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class ArticleSort implements Serializable {

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
     * 类型id
     */
    private Long sortId;

    /**
     * 逻辑删除
     */
    private Integer deleted;


}
