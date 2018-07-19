package com.imooc.service;

import com.imooc.pojo.UserInfo;

import java.util.List;

public interface UserInfoService {

	public void saveUser(UserInfo user) throws Exception;

	public void updateUser(UserInfo user);

	public void deleteUser(Long userId);

	public UserInfo queryUserById(Long userId);

	public List<UserInfo> queryUserList(UserInfo user);

	public List<UserInfo> queryUserListPaged(UserInfo user, Integer page, Integer pageSize);

	public UserInfo queryUserInfoByIdCustom(Long id);

	public void saveUserTransactional(UserInfo user);
}
