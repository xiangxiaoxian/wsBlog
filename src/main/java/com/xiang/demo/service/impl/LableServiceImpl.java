package com.xiang.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.Article;
import com.xiang.demo.entity.ArticleLable;
import com.xiang.demo.entity.Lable;
import com.xiang.demo.mapper.ArticleLableMapper;
import com.xiang.demo.mapper.ArticleMapper;
import com.xiang.demo.mapper.LableMapper;
import com.xiang.demo.service.LableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XR
 * @since 2021-01-07
 */
@Service
public class LableServiceImpl extends ServiceImpl<LableMapper, Lable> implements LableService {

    @Resource
    private LableMapper lableMapper;

    @Resource
    private ArticleLableMapper articleLableMapper;

    @Resource
    private ArticleMapper articleMapper;



    //查询所有标签并分页
    @Override
    public Result getAllLablesAndPages(Page page,String searchField) {
        QueryWrapper<Lable> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("lable_name",searchField);
        return Result.success(200,"查询成功",lableMapper.selectPage(page,queryWrapper));
    }

    //新增或修改标签
    @Override
    @Transactional
    public Result insertOrUpdateLableById(Lable lable) {
        if (ObjectUtils.isEmpty(lable.getId())){//判断传递的实体有无id
            lableMapper.insert(lable);
            return Result.success(200,"添加成功",lable);
        }
        lableMapper.updateById(lable);
        return Result.success(200,"编辑成功",null);
    }

    //根据id删除单个标签
    @Override
    @Transactional
    public Result deleteLableById(Long id) {
        //删除之前判断该标签下是否有文章
        QueryWrapper<ArticleLable> articleLableQueryWrapper=new QueryWrapper<>();
        articleLableQueryWrapper.eq("lable_id",id).eq("deleted",0);
        if (!ObjectUtils.isEmpty(articleLableMapper.selectList(articleLableQueryWrapper))){
            return Result.error(400,"该标签下已有文章,无法删除");
        }
        lableMapper.deleteById(id);
        return Result.success(200,"删除成功",null);
    }

    //根据id列表删除多个标签
    @Override
    @Transactional
    public Result deleteLablesByBatchIds(Collection<Long> batchIds) {
        //删除之前判断该标签下是否有文章
       List<ArticleLable> articleLables=articleLableMapper.selectArticlesByBatchIds(batchIds);
       if (!ObjectUtils.isEmpty(articleLables)){
           return Result.error(400,"所选标签下存在文章，无法删除，请重新选择");
       }
       articleLableMapper.deleteBatchIds(batchIds);
        return Result.success(200,"批量删除成功",null);
    }

    //根据类别id查询当前标签下所有文章并进行分页
    @Override
    public Result getArticlesByLableIdAndPage(Long id, Page page) {
        Page<Article> articleList = articleMapper.getArticlesByLableIdAndPage(id, page);
        return Result.success(200, "success", articleList);
    }


}
