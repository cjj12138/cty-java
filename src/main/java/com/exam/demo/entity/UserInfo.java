package com.exam.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author A
 */
@TableName("user_info")
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "user_id")
    private String userId;
    private String userName;
    private String shippingAddress;
    private String telephone;
    private String reputationPoints;
}
