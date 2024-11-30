package com.example.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.entity.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {

    public List<Category> list();

    public Category getById(Long id);

    public boolean save(Category category);

    public boolean update(Category category);

    public boolean deleteById(Long id);
}
