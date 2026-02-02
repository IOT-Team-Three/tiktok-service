package com.iot;

import com.iot.entity.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.DigestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Configuration
@SuppressWarnings("all")
public class FastMethodConfig {

    /**
     * md5加密
     * @param password
     * @return
     */
    @Bean
    public String md5(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

    @Getter
    private static final int statusSafeCode = new Random().nextInt(Integer.MAX_VALUE);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 生成唯一标识
     * @return
     */
    @Bean
    public String getUnique() {
        Random rand = new Random();
        int randomNum = rand.nextInt(Integer.MAX_VALUE);
        redisTemplate.opsForValue().set("user:" + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(), randomNum,3600, TimeUnit.SECONDS);
        return String.valueOf(randomNum);
    }

    /**
     * 检查状态安全码
     * @param statusSafeCode
     * @return
     */
    public boolean checkStatusSafeCode(int statusSafeCode) {
        return FastMethodConfig.statusSafeCode == statusSafeCode;
    }

    /**
     * 检查唯一标识
     * @param userId
     * @param unique
     * @return
     */
    public boolean checkUnique(int userId, String unique) {
        return unique.equals(Objects.requireNonNull(redisTemplate.opsForValue().get("user:" + userId)).toString());
    }

    /**
     * 从token中获取用户
     * @param token
     * @return
     */
     @Bean
    public User getUserFromToken(String token) {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
