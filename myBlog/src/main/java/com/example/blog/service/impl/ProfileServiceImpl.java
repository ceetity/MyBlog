package com.example.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.Profile;
import com.example.blog.mapper.ProfileMapper;
import com.example.blog.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 个人信息服务层
 * 处理个人信息相关的业务逻辑
 */
@Service
public class ProfileServiceImpl extends ServiceImpl<ProfileMapper,Profile> implements ProfileService {
    @Autowired
    private ProfileMapper profileMapper;

    /**
     * 获取个人信息
     * @return 个人信息对象
     */
    @Override
    public Profile getProfile() {
        // 假设个人信息的ID为1
        return profileMapper.selectById(1L);
    }

    /**
     * 更新个人信息
     * @param profile 个人信息对象
     * @return 是否更新成功
     */
    @Override
    public boolean updateProfile(Profile profile) {
        return profileMapper.updateById(profile) > 0;
    }
}