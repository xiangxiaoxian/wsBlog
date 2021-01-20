package com.xiang.demo.service.impl;

import com.xiang.common.Result;
import com.xiang.demo.entity.Comments;
import com.xiang.demo.mapper.ArticleMapper;
import com.xiang.demo.mapper.CommentsMapper;
import com.xiang.demo.service.CommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 评论实现类
 *
 * @author XR
 * @since 2021-01-07
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments>
    implements CommentsService {

  @Resource private CommentsMapper commentsMapper;

  @Resource private ArticleMapper articleMapper;

  // 新增或修改评论
  @Override
  @Transactional
  public Result insertOrUpdateCommentsById(Comments comments) {
    if (ObjectUtils.isEmpty(comments.getId())) { // 判断该实体是否有id
      commentsMapper.insert(comments);
      articleMapper.upCommentsByArticleId(comments.getArticleId());
      return Result.success(200, "评论成功", comments);
    }
    commentsMapper.updateById(comments);
    return Result.success(200, "修改成功", null);
  }

  // 删除评论
  @Override
  @Transactional
  public Result deleteCommentsById(Comments comments) {
    commentsMapper.deleteById(comments.getId());
    articleMapper.lowCommentsByArticleId(comments.getArticleId());
    return Result.success(200, "评论删除成功", null);
  }
}
