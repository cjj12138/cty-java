package com.exam.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exam.demo.bean.R;
import com.exam.demo.entity.GoodInfo;
import com.exam.demo.entity.User;
import com.exam.demo.entity.UserInfo;
import com.exam.demo.mapper.GoodInfoMapper;
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
}
