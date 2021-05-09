package com.exam.demo.entity;


import lombok.Data;

/**
 * @author A
 */
@Data
public class OrderInfo {
    private String orderId;
    private String orderOwner;
    private String insertTime;
    private String orderStatus;
    private String goodId;
}
