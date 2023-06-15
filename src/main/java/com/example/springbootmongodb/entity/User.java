package com.example.springbootmongodb.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * @Author 尚智江
 * @description
 * @CreateDate 2023/6/15 23:23
 */


@Data
@Document("User")
public class User {

    @Id// id生成策略
    private String id;
    private String name;
    private Integer age;
    private String email;
    private String createDate;
}

