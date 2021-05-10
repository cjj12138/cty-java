package com.exam.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author A
 */
@Data
public class SystemNotification {
    private String actionId;
    private String actionTitle;
    private String actionMsg;
    @TableField(fill = FieldFill.INSERT)
    private String insertTime;
    private String activityDate;
    private String activityUser;
}
