package com.example.blog;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.example.blog.DTO.UserDTO;
import com.example.blog.entity.User;
import com.example.blog.utils.RegexUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MmApplicationTests {
    @Test
    public void test01(){
        System.out.println(RegexUtils.isPhoneInvalid("123"));
        System.out.println(RegexUtils.isPhoneInvalid("15355786643"));
        System.out.println(RegexUtils.isEmailInvalid("123"));
        System.out.println(RegexUtils.isCodeInvalid("123"));
}

@Test
    public void test02(){
        String token;
        for(int i=0;i<=10;i++) {
           token = UUID.randomUUID().toString(true);
           System.out.println(token);
        }
        System.out.println(UUID.randomUUID().toString(false));
}


@Test
    public void test03(){
    User user=new User();
    user.setId(2L).setEmail("123456789@qq.com")
            .setPassword("123456")
            .setNickName("tom_kk7");
    System.out.println(user);
    UserDTO userDTO= BeanUtil.copyProperties(user,UserDTO.class);
    System.out.println(userDTO);
}
}
