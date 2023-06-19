package com.example.springbootmongodb;

import com.example.springbootmongodb.entity.User;
import com.example.springbootmongodb.repository.UserRepository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @Author 尚智江
 * @description
 * @CreateDate 2023/6/16 13:02
 */
@SpringBootTest
public class MongoDBRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    // 添加操作
    @Test
    public void insert() {
        User user = new User();
        user.setName("ppp");
        user.setAge(30);
        user.setEmail("8888%");
        User save = userRepository.save(user);
        System.out.println(save);
    }

    // 查询操作
    @Test
    public void findAll() {
        List<User> all = userRepository.findAll();
        System.out.println(all);
    }

    // 根据id查询
    @Test
    public void findById() {
        User user = userRepository.findById("648b31d2ca44f460f9789b2a").get();
        System.out.println(user);
    }

    // 条件查询
    @Test
    public void findUserList() {
        // name = abc  age = 18
        User user = new User();
        user.setName("abc");
        user.setAge(18);
        Example<User> userExample = Example.of(user);
        List<User> all = userRepository.findAll(userExample);
        System.out.println(all);
    }

    // 模糊查询
    @Test
    public void findLikeUserList() {
        // name = abc  age = 18
        // 设置模糊查询的规则
        ExampleMatcher matcher = ExampleMatcher.matching()
                // 表示模糊查询
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                // 忽略大小写
                .withIgnoreCase(true);
        User user = new User();
        user.setName("b");
//        user.setAge(1);
        Example<User> userExample = Example.of(user, matcher);
        List<User> all = userRepository.findAll(userExample);
        System.out.println(all);
    }

    // 分页查询
    @Test
    public void findPageUserList() {
        // 设置分页参数  0-->>代表第一页
        Pageable pageable = PageRequest.of(0, 1);
        User user = new User();
        user.setName("bc");
        Example<User> userExample = Example.of(user);
        Page<User> page = userRepository.findAll(userExample, pageable);
        // 总页数
        int totalPages = page.getTotalPages();
        // 总元素个数
        long totalElements = page.getTotalElements();
        // 数据集合值
        List<User> content = page.getContent();
        System.out.println(
                "总页数: " + totalPages + '\n' +
                        "总元素个数: " + totalElements + '\n' +
                        "集合值" + content);
    }

    // 修改
    @Test
    public void update() {
        User user = userRepository.findById("648b31d2ca44f460f9789b2a").get();
        System.out.println("修改前的值：" + user);
        user.setAge(100);
        user.setName("mmmmmm");
        User save = userRepository.save(user);
        System.out.println("修改成功: " + save);
        User user1 = userRepository.findById("648b31d2ca44f460f9789b2a").get();
        System.out.println("修改后的值：" + user1);
    }

    // 删除
    @Test
    public void delete() {
        // 删除
        userRepository.deleteById("648b31dfd1fcdd7fbf827edd");
    }
}

