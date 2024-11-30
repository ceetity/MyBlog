package com.example.blog.controller;

import com.example.blog.common.Result;
import com.example.blog.entity.Profile;
import com.example.blog.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 个人信息控制器
 * 处理所有与个人信息相关的HTTP请求
 */
@RestController
@RequestMapping("/api/about")
public class AboutController {
    @Autowired
    private ProfileService profileService;

    /**
     * 获取个人信息
     * @return 个人信息详情
     */
    @GetMapping
    public Result<Profile> getProfile() {
        return Result.success(profileService.getProfile());
    }

    /**
     * 更新个人信息
     * @param profile 个人信息对象
     * @return 更新结果
     */
    @PutMapping
    public Result<Boolean> updateProfile(@RequestBody Profile profile) {
        return Result.success(profileService.updateProfile(profile));
    }
}