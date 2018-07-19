package com.imooc.service.impl;

import com.github.pagehelper.PageHelper;
import com.imooc.mapper.UserInfoMapper;
import com.imooc.mapper.UserInfoMapperCustom;
import com.imooc.pojo.UserInfo;
import com.imooc.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private UserInfoMapperCustom userInfoMapperCustom;

	//根据对象插入数据
	//注解事务，REQUIRED有就使用，没有创建
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUser(UserInfo userInfo) throws Exception {

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		userInfoMapper.insert(userInfo);
	}

	//更新数据
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUser(UserInfo userInfo) {
		userInfoMapper.updateByPrimaryKeySelective(userInfo);
		//userInfoMapper.updateByPrimaryKey(userInfo);
	}

	//根据主键删除
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUser(Long userId) {
		userInfoMapper.deleteByPrimaryKey(userId);
	}

	//根据主键查询
	//注解事务，SUPPORTS有事务也执行，没有也执行，适用于查询数据
	@Transactional(propagation = Propagation.SUPPORTS)
	public UserInfo queryUserById(Long userId) {

		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return userInfoMapper.selectByPrimaryKey(userId);
	}

	//根据条件查询多条
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<UserInfo> queryUserList(UserInfo userInfo) {

		try {
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Example example = new Example(UserInfo.class);
		Example.Criteria criteria = example.createCriteria();
		//模糊查询
		if (!StringUtils.isEmptyOrWhitespace(userInfo.getName())) {
//			criteria.andEqualTo("username", user.getUsername());
			criteria.andLike("name", "%" + userInfo.getName() + "%");
		}

//		if (!StringUtils.isEmptyOrWhitespace(user.getNickname())) {
//			criteria.andLike("nickname", "%" + user.getNickname() + "%");
//		}

		List<UserInfo> userList = userInfoMapper.selectByExample(example);

		return userList;
	}

	//分页查询
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<UserInfo> queryUserListPaged(UserInfo userInfo, Integer page, Integer pageSize) {
		// 开始分页
        PageHelper.startPage(page, pageSize);

		Example example = new Example(UserInfo.class);
		Example.Criteria criteria = example.createCriteria();

		if (!StringUtils.isEmptyOrWhitespace(userInfo.getName())) {
			criteria.andLike("name", "%" + userInfo.getName() + "%");
		}
		//example.orderBy("createTime").desc();
		List<UserInfo> userList = userInfoMapper.selectByExample(example);

		return userList;
	}

	//自定义mapper查询
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public UserInfo queryUserInfoByIdCustom(Long id) {

		List<UserInfo> userList = userInfoMapperCustom.queryUserInfoSimplyInfoById(id);

		if (userList != null && !userList.isEmpty()) {
			return (UserInfo)userList.get(0);
		}

		return null;
	}

	//事务测试
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUserTransactional(UserInfo userInfo) {

		userInfoMapper.insert(userInfo);

		int a = 1 / 0;

		userInfo.setJobNumber("6666666");
		//updateByPrimaryKeySelective 根据主键更新属性不为null的值
		userInfoMapper.updateByPrimaryKeySelective(userInfo);
	}
}
