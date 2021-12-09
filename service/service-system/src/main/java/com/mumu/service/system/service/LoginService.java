package com.mumu.service.system.service;

import com.mumu.service.system.bo.UserBO;

/**
 * 登录服务层
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
public interface LoginService {

    /**
     * 登录返回用户ID
     * @param username 用户名
     * @param password 密码
     * @return
     */
    String login(String username, String password);

    /**
     * 用户信息
     * @return
     */
    UserBO getUserInfo();
}
