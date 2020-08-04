package com.baomidou.samples;

import javax.annotation.Resource;

import com.baomidou.samples.incrementer.CustomIdGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.samples.entity.User;
import com.baomidou.samples.mapper.UserMapper;

/**
 * @author nieqiuqiu 2019/11/30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class IdGeneratorTest {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private CustomIdGenerator customIdGenerator;

    @Test
    public void test() {
        User user = new User();
        user.setName("靓仔");
        user.setAge(18);
        userMapper.insert(user);
        Assert.assertEquals(Long.valueOf(1L), user.getId());
    }

    /**
     * 测试自动生成ID
     */
    @Test
    public void testGenerateId() {
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.setName("靓仔");
            user.setAge(18);
            Long aLong = customIdGenerator.nextId(user);
            System.out.println(aLong);
        }
    }
}
