package com.mumu.db.system.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mumu.db.system.dao.UserDao;
import com.mumu.db.system.entity.User;
import com.mumu.db.system.mapper.UserMapper;
import org.springframework.stereotype.Service;


/**
 * 用户Dao层实现
 * @author ekko
 * @create 2021-12-06 15:54:20
 */
@Service
public class UserDaoImpl extends ServiceImpl<UserMapper, User> implements UserDao {

}
