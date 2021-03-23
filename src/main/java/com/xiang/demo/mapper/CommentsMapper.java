package com.xiang.demo.mapper;

import com.xiang.common.Result;
import com.xiang.demo.entity.Comments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XR
 * @since 2021-01-07
 */
public interface CommentsMapper extends BaseMapper<Comments> {


    List<Comments> selectCommentsByArticleId(Long articleId);
}
