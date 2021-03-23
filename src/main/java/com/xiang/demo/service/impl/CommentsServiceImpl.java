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
import java.time.LocalDateTime;
import java.util.List;

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
      comments.setCommentsDate(LocalDateTime.now());
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
  public Result deleteCommentsById(Long id,Long articleId) {
    commentsMapper.deleteById(id);
    articleMapper.lowCommentsByArticleId(articleId);
    return Result.success(200, "评论删除成功", null);
  }

  //根据文章id查询该文章下评论
  @Override
  public Result selectCommentsByArticleId(Long articleId) {
    List<Comments> commentsList= commentsMapper.selectCommentsByArticleId(articleId);
    return Result.success(200,"查询成功",commentsList);
  }
}
