package com.exam.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exam.demo.bean.R;
import com.exam.demo.entity.GoodInfo;
import com.exam.demo.entity.SystemNotification;
import com.exam.demo.entity.User;
import com.exam.demo.entity.UserInfo;
import com.exam.demo.mapper.GoodInfoMapper;
import com.exam.demo.mapper.SystemNotificationMapper;
import com.exam.demo.mapper.UserInfoMapper;
import com.exam.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author A
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    GoodInfoMapper goodInfoMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    SystemNotificationMapper systemNotificationMapper;

    @RequestMapping("getGoodByAdmin")
    public R getGoodByAdmin(){
        List<GoodInfo> goodInfoList = goodInfoMapper.selectList(new QueryWrapper<GoodInfo>().lambda().eq(GoodInfo::getGoodStatus, 0));
        goodInfoList.stream().forEach(goodInfo -> {
            String goodId=goodInfo.getGoodId();
            String goodPath = goodInfoMapper.getGoodPath(goodId);
            List<String> list=new ArrayList<>();
            list.add(goodPath);
            goodInfo.setGoodPic(list);
        });
        return R.success("查询成功",goodInfoList);
    }

    @RequestMapping("getRoleId")
    public R getRoleId(@RequestBody JSONObject params){
        String userId = params.getString("userId");
        User roleId = userMapper.getRoleId(userId);
        return R.success("查询成功",roleId);
    }


    @RequestMapping("getAllUser")
    public R getAllUser(){
        List<UserInfo> userInfos = userInfoMapper.selectList(new QueryWrapper<UserInfo>().lambda());
        return R.success("查询成功",userInfos);
    }

    @RequestMapping("updateGoodStatus")
    public R updateGoodStatus(@RequestBody JSONObject param){
        String goodId = param.getString("goodId");
        String status=param.getString("status");
        goodInfoMapper.updateStatus(goodId,status);
        return R.success("更新成功");
    }

    @RequestMapping("getAppraisalShoes")
    public R getAppraisalShoes(){
        List<GoodInfo> goodInfoList = goodInfoMapper.selectList(new QueryWrapper<GoodInfo>().lambda().eq(GoodInfo::getGoodStatus, 2));
        goodInfoList.stream().forEach(goodInfo -> {
            String goodId=goodInfo.getGoodId();
            List<String> goodPath = goodInfoMapper.getGoodPathMorePic(goodId);
            List<String> list=new ArrayList<>();
            goodPath.stream().forEach(good->{
                list.add(good);
            });
            goodInfo.setGoodPic(list);
        });
        return R.success("获取成功",goodInfoList);
    }

    @RequestMapping("setSystemNotification")
    public R setSystemNotification(@RequestBody SystemNotification systemNotification){
        int insert = systemNotificationMapper.insert(systemNotification);
        return R.success("插入成功");
    }

}
