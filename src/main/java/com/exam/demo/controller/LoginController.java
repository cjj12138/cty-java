package com.exam.demo.controller;



import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exam.demo.Service.UserService;
import com.exam.demo.bean.R;
import com.exam.demo.entity.*;
import com.exam.demo.mapper.GoodInfoMapper;
import com.exam.demo.mapper.OrderDetailMapper;
import com.exam.demo.mapper.OrderInfoMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    GoodInfoMapper goodInfoMapper;
    @Autowired
    OrderDetailMapper orderDetailMapper;
    @Autowired
    OrderInfoMapper orderInfoMapper;

    @PostMapping("/login")
    public R Test(@RequestBody JSONObject params){
        User user=userService.login(params);
        if (user!=null) {
            return R.success("查询成功", user);
        }else {
            return R.error("查询失败");
        }
    }

    @PostMapping("/getUserInfo")
    public R getUserInfo(@RequestBody JSONObject params){
        UserInfo userInfo = userService.getUserInfo(params);
        return R.success("查询成功",userInfo);
    }

    @PostMapping("/updateUserInfo")
    public R updateUserInfo(@RequestBody UserInfo userInfo){
        userService.updateUserInfo(userInfo);
        return R.success("更新成功");
    }

    @PostMapping("uploadShoes")
    public R uploadShoes(MultipartHttpServletRequest multipartHttpServletRequest){
        MultiValueMap<String, MultipartFile> multiFileMap = multipartHttpServletRequest.getMultiFileMap();
        UUID uuid = UUID.randomUUID();
        String goodId =uuid.toString();
        String goodName = multipartHttpServletRequest.getParameter("goodName");
        String goodDes = multipartHttpServletRequest.getParameter("goodDes");
        String goodPrice = multipartHttpServletRequest.getParameter("goodPrice");
        String userId= multipartHttpServletRequest.getParameter("userId");
        String goodSize = multipartHttpServletRequest.getParameter("goodSize");
        GoodInfo goodInfo=new GoodInfo(goodId,goodName,goodDes,goodPrice,userId,"0",goodSize);
        int insert = goodInfoMapper.insert(goodInfo);
        String now = String.valueOf(System.currentTimeMillis());
        multiFileMap.forEach((s, multipartFiles) -> {
            for (MultipartFile multipartFile : multipartFiles) {
                String filePath="E://cty//vue//src//assets//storeMsg//"+now+multipartFile.getOriginalFilename();
                userService.insertInto(goodId,now+multipartFile.getOriginalFilename());
                try {
                    multipartFile.transferTo(new File(filePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return R.success("上传完成");
    }

    @PostMapping("getAllGoods")
    public R getAllGoods(){
        List<GoodInfo> goodInfoList=userService.getAllGoods();
        goodInfoList.stream().forEach(goodInfo -> {
            String goodId=goodInfo.getGoodId();
            String goodPath = goodInfoMapper.getGoodPath(goodId);
            List<String> list=new ArrayList<>();
            list.add(goodPath);
            goodInfo.setGoodPic(list);
        });
        return R.success("查询成功",goodInfoList);
    }


    @PostMapping("getAllGoodsMorePic")
    public R getAllGoodsMorePic(@RequestBody JSONObject param){
        String thisGoodId = param.getString("goodId");
        List<GoodInfo> goodInfoList = goodInfoMapper.selectList(new QueryWrapper<GoodInfo>().lambda().eq(GoodInfo::getGoodId, thisGoodId));
        goodInfoList.stream().forEach(goodInfo -> {
            String goodId=goodInfo.getGoodId();
            List<String> goodPath = goodInfoMapper.getGoodPathMorePic(goodId);
            List<String> list=new ArrayList<>();
            goodPath.stream().forEach(good->{
                list.add(good);
            });
            goodInfo.setGoodPic(list);
        });
        return R.success("查询成功",goodInfoList);
    }

    @RequestMapping("creatOrder")
    public R createOrder(@RequestBody JSONObject param){
        String goodId = param.getString("goodId");
        String userId = param.getString("userId");
        String orderId = UUID.randomUUID().toString();
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setOrderId(orderId);
        orderInfo.setOrderOwner(userId);
        orderInfo.setInsertTime(DateUtil.now());
        orderInfo.setOrderStatus("1");
        orderInfo.setGoodId(goodId);
        orderInfoMapper.insert(orderInfo);
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setOrderId(orderId);
        orderDetail.setInsertTime(DateUtil.now());
        orderDetail.setUserId(userId);
        orderDetailMapper.insert(orderDetail);
        goodInfoMapper.updateStatus(goodId,"2");
        return R.success("创建成功");
    }

    @RequestMapping("myCollection")
    public R myCollection(@RequestBody JSONObject param){
        String goodId = param.getString("goodId");
        String userId = param.getString("userId");
        goodInfoMapper.insertIntoMyLike(goodId,userId);
        return R.success("插入成功");
    }

    @RequestMapping("getMyfvt")
    public R getMyfvt(@RequestBody JSONObject param){
        String userId = param.getString("userId");
        List<String> list=goodInfoMapper.selectMyfvt(userId);
    }
}
