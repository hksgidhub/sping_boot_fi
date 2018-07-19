package com.imooc.controller;

import com.imooc.pojo.IMoocJSONResult;
import com.imooc.pojo.UserInfo;
import com.imooc.service.UserInfoService;
import org.n3r.idworker.Id;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("mybatis2")
public class InfoCRUDController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private Sid sid;

	@Autowired
	private Id idutil;

	@RequestMapping("/saveUserInfo")
	public IMoocJSONResult saveUserInfo() throws Exception {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(idutil.next());
		userInfo.setJobNumber("343");
		userInfo.setCreateTime(new Date());
		userInfo.setName("saveuserinfo");
		userInfoService.saveUser(userInfo);

		return IMoocJSONResult.ok("保存成功");
	}

	@RequestMapping("/updateUserInfo")
	public IMoocJSONResult updateUserInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId((long) 1);
		userInfo.setName("update");
		userInfo.setJobNumber("111111");

		userInfoService.updateUser(userInfo);

		return IMoocJSONResult.ok("保存成功");
	}

	@RequestMapping("/deleteUserInfo")
	public IMoocJSONResult deleteUser(@RequestParam("userid") Long userId) {

		userInfoService.deleteUser(userId);

		return IMoocJSONResult.ok("删除成功");
	}

	@RequestMapping("/queryUserInfoById")
	public IMoocJSONResult queryUserById(@RequestParam("userid") Long userId) {

		return IMoocJSONResult.ok(userInfoService.queryUserById(userId));
	}

	@RequestMapping("/queryUserListInfo")
	public IMoocJSONResult queryUserList() {
		UserInfo user = new UserInfo();
		user.setName("saveuserinfo");
		List<UserInfo> userList = userInfoService.queryUserList(user);

		return IMoocJSONResult.ok(userList);
	}

	@RequestMapping("/queryUserInfoListPaged")
	public IMoocJSONResult queryUserListPaged(@RequestParam("pages") Integer page) {
		if (page == null) {
			page = 1;
		}
		int pageSize = 10;
		UserInfo user = new UserInfo();
		user.setName("saveuserinfo");
		List<UserInfo> userList = userInfoService.queryUserListPaged(user, page, pageSize);

		return IMoocJSONResult.ok(userList);
	}


	@RequestMapping("/queryUserInfoByIdCustom")
	public IMoocJSONResult queryUserByIdCustom(@RequestParam("userid") Long id) {

		return IMoocJSONResult.ok(userInfoService.queryUserInfoByIdCustom(id));
	}

	@RequestMapping("/saveUserInfoTransactional")
	public IMoocJSONResult saveUserTransactional() {

		UserInfo userInfo = new UserInfo();
		userInfo.setId(idutil.next());
		userInfo.setJobNumber("2222222");
		userInfo.setCreateTime(new Date());
		userInfo.setName("Transactional");

		userInfoService.saveUserTransactional(userInfo);

		return IMoocJSONResult.ok("保存成功");
	}
}
