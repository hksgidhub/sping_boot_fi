package com.imooc.mapper;

import com.imooc.pojo.UserInfo;

import java.util.List;

public interface UserInfoMapperCustom {

	List<UserInfo> queryUserInfoSimplyInfoById(Long id);
}