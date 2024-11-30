package com.example.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.entity.Article;

import java.util.List;
/**
 * @author ${ceetity}
 * @github https://github.com/ceetity/works.git
 * @date 2024.11.30
 */


public interface ArticleService extends IService<Article> {
    public List<Article> getLatestArticles();

    public List<Article> list();

    public Article getById(Long id);

    public Page<Article> getPageList(int page, int size);

    public boolean save(Article article);

    public boolean update(Article article);

    public boolean deleteById(Long id);

    public List<Article> getByCategoryId(Long categoryId);
}
