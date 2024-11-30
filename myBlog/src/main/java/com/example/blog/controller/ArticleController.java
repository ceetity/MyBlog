package com.example.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.common.Result;
import com.example.blog.entity.Article;
import com.example.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章控制器
 * 处理所有与文章相关的HTTP请求
 */
@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;


    // 获取最新文章列表
    @GetMapping("/latest")
    public Result getLatestArticles() {
        try {
            List<Article> articles = articleService.getLatestArticles();
            return Result.success(articles);
        } catch (Exception e) {
            return Result.error("获取最新文章失败");
        }
    }

    /**
     * 获取所有文章
     * @return 文章列表
     */
    @GetMapping
    public Result<List<Article>> list() {
        return Result.success(articleService.list());
    }





    /**
     * 根据ID获取文章
     * @param id 文章ID
     * @return 文章详情
     */
    @GetMapping("/{id}")
    public Result<Article> getById(@PathVariable Long id) {
        return Result.success(articleService.getById(id));
    }

    /**
     * 获取最新文章
     * @return 最新的3篇文章
//     */
//    @GetMapping("/latest")
//    public Result<List<Article>> getLatest() {
//        return Result.success(articleService.getLatestArticles());
//    }

    /**
     * 分页获取文章
     * @param page 页码（默认1）
     * @param size 每页大小（默认10）
     * @return 分页后的文章列表
     */
    @GetMapping("/page")
    public Result<Page<Article>> getPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return Result.success(articleService.getPageList(page, size));
    }

    /**
     * 根据分类获取文章
     * @param categoryId 分类ID
     * @return 该分类下的文章列表
     */
    @GetMapping("/category/{categoryId}")
    public Result<List<Article>> getByCategoryId(@PathVariable Long categoryId) {
        return Result.success(articleService.getByCategoryId(categoryId));
    }



    /**
     * 创建新文章
     * @param article 文章对象
     * @return 创建结果
     */
    @PostMapping
    public Result<Boolean> save(@RequestBody Article article) {
        return Result.success(articleService.save(article));
    }

    /**
     * 更新文章
     * @param article 文章对象
     * @return 更新结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody Article article) {
        return Result.success(articleService.update(article));
    }

    /**
     * 删除文章
     * @param id 文章ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success(articleService.deleteById(id));
    }
}