package com.example.blog.controller;

import com.example.blog.common.Result;
import com.example.blog.entity.Category;
import com.example.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类控制器
 * 处理所有与分类相关的HTTP请求
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 获取所有分类
     * @return 分类列表
     */
    @GetMapping
    public Result<List<Category>> list() {
        return Result.success(categoryService.list());
    }

    /**
     * 根据ID获取分类
     * @param id 分类ID
     * @return 分类详情
     */
    @GetMapping("/{id}")
    public Result<Category> getById(@PathVariable Long id) {
        return Result.success(categoryService.getById(id));
    }

    /**
     * 创建新分类
     * @param category 分类对象
     * @return 创建结果
     */
    @PostMapping
    public Result<Boolean> save(@RequestBody Category category) {
        return Result.success(categoryService.save(category));
    }

    /**
     * 更新分类
     * @param category 分类对象
     * @return 更新结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody Category category) {
        return Result.success(categoryService.update(category));
    }

    /**
     * 删除分类
     * @param id 分类ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success(categoryService.deleteById(id));
    }
}