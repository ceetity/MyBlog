package com.example.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.Profile;
import com.example.blog.mapper.ProfileMapper;
/**
 * @author ${ceetity}
 * @github https://github.com/ceetity/works.git
 * @date 2024.11.30
 */


public interface ProfileService extends IService<Profile> {
    public Profile getProfile();

    public boolean updateProfile(Profile profile);
}
