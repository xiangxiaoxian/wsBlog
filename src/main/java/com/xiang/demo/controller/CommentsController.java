package com.xiang.demo.controller;

import com.xiang.common.Result;
import com.xiang.demo.entity.Comments;
import com.xiang.demo.mapper.CommentsMapper;
import com.xiang.demo.service.CommentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 评论控制器
 *
 * @author XR
 * @since 2021-01-07
 */
@Api(
    value = "CommentsController",
    tags = {"评论相关接口"})
@RestController
@RequestMapping("/comments")
public class CommentsController {

  @Resource private CommentsService commentsService;

  @ApiOperation(value = "新增/修改评论")
  @PutMapping()
  public Result insertOrUpdateCommentsById(@RequestBody Comments comments) {
    return commentsService.insertOrUpdateCommentsById(comments);
  }

  @ApiOperation(value = "删除评论")
  @DeleteMapping("/{id}")
  public Result deleteCommentsById(@PathVariable Long id,@RequestParam("articleId") Long articleId) {
    return commentsService.deleteCommentsById(id,articleId);
  }

  @ApiOperation(value = "根据文章id查询该文章下评论")
  @GetMapping("{articleId}")
  public Result selectCommentsByArticleId(@PathVariable Long articleId) {
    return commentsService.selectCommentsByArticleId(articleId);
  }
}
