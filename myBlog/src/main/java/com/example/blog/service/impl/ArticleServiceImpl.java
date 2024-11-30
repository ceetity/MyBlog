package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.Article;
import com.example.blog.mapper.ArticleMapper;
import com.example.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章服务层
 * 处理文章相关的业务逻辑
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public List<Article> getLatestArticles() {
        // 默认获取最新的5篇文章
//        return articleMapper.selectLatestArticles(5);
        return articleMapper.selectLatestArticles(5);
    }

    /**
     * 获取所有文章列表
     * @return 文章列表
     */
    @Override
    public List<Article> list() {
        return articleMapper.selectList(null);
    }

    /**
     * 根据ID获取文章
     * @param id 文章ID
     * @return 文章对象
     */
    @Override
    public Article getById(Long id) {
        return articleMapper.selectById(id);
    }

    /**
     * 获取最新的3篇文章
     * @return 最新文章列表
     */
//    public List<Article> getLatestArticles() {
//        return articleMapper.selectList(
//            new QueryWrapper<Article>()
//                .orderByDesc("create_time")
//                .last("LIMIT 3")
//        );
//    }

    /**
     * 分页获取文章列表
     * @param page 页码
     * @param size 每页大小
     * @return 分页后的文章列表
     */
    @Override
    public Page<Article> getPageList(int page, int size) {
        return articleMapper.selectPage(
            new Page<>(page, size),
            new QueryWrapper<Article>().orderByDesc("create_time")
        );
    }

    /**
     * 根据分类ID获取文章列表
     * @param categoryId 分类ID
     * @return 该分类下的文章列表
     */

    /**
     * 保存新文章
     * @param article 文章对象
     * @return 是否保存成功
     */
    @Override
    public boolean save(Article article) {

        return articleMapper.insert(article) > 0;
    }

    /**
     * 更新文章
     * @param article 文章对象
     * @return 是否更新成功
     */
    @Override
    public boolean update(Article article) {
        return articleMapper.updateById(article) > 0;
    }

    /**
     * 删除文章
     * @param id 文章ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteById(Long id) {
        return articleMapper.deleteById(id) > 0;
    }

    @Override
    public List<Article> getByCategoryId(Long categoryId) {
        return articleMapper.selectByCategoryId(categoryId);
    }
}