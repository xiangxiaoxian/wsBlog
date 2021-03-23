package com.xiang.demo.service;

import com.xiang.common.Result;
import com.xiang.demo.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XR
 * @since 2021-03-22
 */
public interface NoticeService extends IService<Notice> {

    Result insertOrUpdateNoticeById(Notice notice);

    Result deleteNoticeById(Long id);

    Result deleteNoticesByBatchIds(Collection<Long> batchIds);
}
