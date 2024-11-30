package com.example.blog.config;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Redisson的配置类
@Configuration
public class RedissonConfig {


    @Bean
    public RedissonClient redissonClient(){
        //配置
        Config config=new Config();
        config.useSingleServer()
                .setAddress("redis://192.168.88.130:6379")  //写你自己的，这是我的ip和端口
                .setPassword("123456");  //写你自己的，这我的密码。  如果你忘了密码，可以执行这个命令（linux中） # cat /etc/redis/redis.conf | grep requirepass
        //根据config创建出RedissonClient对象
     return Redisson.create(config);
    }
}
