package com.exam.demo.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exam.demo.entity.GoodInfo;
import com.exam.demo.entity.User;
import com.exam.demo.entity.UserInfo;
import com.exam.demo.mapper.GoodInfoMapper;
import com.exam.demo.mapper.UserInfoMapper;
import com.exam.demo.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    GoodInfoMapper goodInfoMapper;

    public User check(){
        return userMapper.checkuser();
    }

    public User login(JSONObject params) {
        String userId = params.getString("userId");
        String password = params.getString("password");
        User user = this.userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUserId, userId).eq(User::getUserPassword, password));
        return user;
    }

    public UserInfo getUserInfo(JSONObject params) {
        String userId = params.getString("userId");
        return userInfoMapper.selectOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getUserId, userId));
    }

    public void updateUserInfo(UserInfo userInfo) {
        userInfoMapper.updateById(userInfo);
    }

    public void insertInto(String uuid, String filePath) {
        userInfoMapper.insertPath(uuid,filePath);
    }

    public List<GoodInfo> getAllGoods() {
        return goodInfoMapper.selectList(new QueryWrapper<GoodInfo>().lambda().eq(GoodInfo::getGoodStatus,1));
    }
}
