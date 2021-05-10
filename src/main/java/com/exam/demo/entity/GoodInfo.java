package com.exam.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * @author A
 */
@Data
public class GoodInfo {
    @TableField(exist = false)
    private String orderOwner;
    @TableField(exist = false)
    private String orderId;
    private String goodId;
    private String goodName;
    private String goodDes;
    private String goodPrice;
    private String goodOwner;
    private String goodStatus;
    @TableField(exist = false)
    private List<String> goodPic;
    private String goodSize;

    public GoodInfo(String goodId, String goodName, String goodDes, String goodPrice, String goodOwner, String goodStatus,String goodSize) {
        this.goodId = goodId;
        this.goodName = goodName;
        this.goodDes = goodDes;
        this.goodPrice = goodPrice;
        this.goodOwner = goodOwner;
        this.goodStatus = goodStatus;
        this.goodSize =goodSize;
    }
}
