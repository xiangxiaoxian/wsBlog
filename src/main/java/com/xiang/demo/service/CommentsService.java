package com.xiang.demo.service;

import com.xiang.common.Result;
import com.xiang.demo.entity.Comments;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XR
 * @since 2021-01-07
 */
public interface CommentsService extends IService<Comments> {

  Result insertOrUpdateCommentsById(Comments comments);

  Result deleteCommentsById(Comments comments);
}
