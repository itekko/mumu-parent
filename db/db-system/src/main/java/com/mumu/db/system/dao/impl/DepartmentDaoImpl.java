package com.mumu.db.system.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mumu.db.system.dao.DepartmentDao;
import com.mumu.db.system.entity.Department;
import com.mumu.db.system.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;


/**
 * 部门Dao层实现
 * @author ekko
 * @create 2021-12-02 17:29:17
 */
@Service
public class DepartmentDaoImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentDao {

}
