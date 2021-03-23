package com.xiang.demo.service.impl;

import com.xiang.common.Result;
import com.xiang.demo.entity.Notice;
import com.xiang.demo.mapper.NoticeMapper;
import com.xiang.demo.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;

/**
 * 服务实现类
 *
 * @author XR
 * @since 2021-03-22
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

  @Resource private NoticeMapper noticeMapper;

  // 新增/修改公告
  @Override
  @Transactional
  public Result insertOrUpdateNoticeById(Notice notice) {
    if (ObjectUtils.isEmpty(notice.getId())) { // 传递id为空，新增
      notice.setPubTime(new Date());
      noticeMapper.insert(notice);
      return Result.success(200, "公告发布成功", null);
    }
    noticeMapper.updateById(notice);
    return Result.success(200, "公共修改成功", null);
  }

  // 删除单条公告
  @Override
  @Transactional
  public Result deleteNoticeById(Long id) {
    noticeMapper.deleteById(id);
    return Result.success(200, "删除成功", null);
  }

  // 删除多条公告
  @Override
  @Transactional
  public Result deleteNoticesByBatchIds(Collection<Long> batchIds) {
    noticeMapper.deleteBatchIds(batchIds);
    return Result.success(200, "批量删除成功", null);
  }
}
