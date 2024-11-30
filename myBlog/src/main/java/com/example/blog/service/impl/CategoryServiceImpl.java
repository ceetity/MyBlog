package com.example.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.Category;
import com.example.blog.mapper.CategoryMapper;
import com.example.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类服务层
 * 处理分类相关的业务逻辑
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 获取所有分类列表
     * @return 分类列表
     */
    @Override
    public List<Category> list() {
        return categoryMapper.selectList(null);
    }

    /**
     * 根据ID获取分类
     * @param id 分类ID
     * @return 分类对象
     */
    @Override
    public Category getById(Long id) {
        return categoryMapper.selectById(id);
    }

    /**
     * 保存新分类
     * @param category 分类对象
     * @return 是否保存成功
     */
    @Override
    public boolean save(Category category) {
        return categoryMapper.insert(category) > 0;
    }

    /**
     * 更新分类
     * @param category 分类对象
     * @return 是否更新成功
     */
    @Override
    public boolean update(Category category) {
        return categoryMapper.updateById(category) > 0;
    }

    /**
     * 删除分类
     * @param id 分类ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteById(Long id) {
        return categoryMapper.deleteById(id) > 0;
    }
}