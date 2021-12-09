package com.mumu.service.system.service.impl;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Sets;
import com.mumu.db.common.builder.BaseBuilder;
import com.mumu.db.system.dao.RoleDao;
import com.mumu.db.system.dao.UserDao;
import com.mumu.db.system.entity.Role;
import com.mumu.db.system.entity.User;
import com.mumu.service.system.bo.UserBO;
import com.mumu.service.system.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static com.mumu.common.constant.CodeConstants.PASSWORD_ERROR;
import static com.mumu.common.constant.CodeConstants.USER_NOT_EXIST;

/**
 * @author ekko
 * @description
 * @create 2021-12-02 7:40 下午
 */
@Slf4j
@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserDao userDao;

    private final RoleDao roleDao;

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public String login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        User one = userDao.getOne(new QueryWrapper<>(user), false);
        Assert.notNull(one,USER_NOT_EXIST);
        Assert.isTrue(StringUtils.equals(password,one.getPassword()),PASSWORD_ERROR);
        return one.getId();
    }

    /**
     * 用户信息
     * @return
     */
    @Override
    public UserBO getUserInfo() {
        Object loginId = StpUtil.getLoginId();
        Assert.notNull(loginId, NotLoginException.INVALID_TOKEN);
        User user = userDao.getById((Serializable) loginId);
        Assert.notNull(loginId, NotLoginException.INVALID_TOKEN);
        UserBO userBO = BaseBuilder.copyProperties(user, UserBO.class);
        List<Role> list = roleDao.getRoleByUserId(userBO.getId());
        if(CollectionUtils.isEmpty(list)){
            userBO.setRoles(Sets.newHashSet());
            return userBO;
        }
        userBO.setRoles(list.stream().map(Role::getCode).collect(Collectors.toSet()));
        return userBO;
    }

    public static void main(String[] args) {
        System.out.println(SecureUtil.sha256("123456"));
        System.out.println(SecureUtil.sha256("123456"));
    }
}
