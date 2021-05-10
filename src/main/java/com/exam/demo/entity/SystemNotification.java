package com.exam.demo.entity;

import lombok.Data;

@Data
public class SystemNotification {
    private String actionId;
    private String actionTitle;
    private String actionMsg;
    private String insertTime;
    private String activityDate;
    private String activityUser;
}
