package com.exam.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author A
 */
@Data
@TableName("user_login")
public class User {
    private String userId;
    private String userName;
    private String userPassword;
}
