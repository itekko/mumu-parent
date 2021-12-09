DROP TABLE IF EXISTS system_role;
CREATE TABLE system_role(
                            id VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '主键' ,
                            code VARCHAR(90) NOT NULL  DEFAULT '' COMMENT '角色编码' ,
                            permission_type tinyint(1) NOT NULL  DEFAULT 0 COMMENT '权限类型(字典DATA_PERMISSION_TYPE)' ,
                            name VARCHAR(90) NOT NULL  DEFAULT '' COMMENT '角色名称' ,
                            status tinyint(1) NOT NULL  DEFAULT 0 COMMENT '状态(字典STATUS)' ,
                            revision INT NOT NULL  DEFAULT 0 COMMENT '乐观锁' ,
                            created_by VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '创建人' ,
                            created_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
                            updated_by VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '更新人' ,
                            updated_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
                            delete_flag tinyint(1) NOT NULL  DEFAULT 0 COMMENT '删除标识' ,
                            PRIMARY KEY (id)
)  COMMENT = '角色表';

DROP TABLE IF EXISTS system_dictionary;
CREATE TABLE system_dictionary(
                                  id VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '主键' ,
                                  code VARCHAR(90) NOT NULL  DEFAULT '' COMMENT 'code编码' ,
                                  name VARCHAR(90) NOT NULL  DEFAULT '' COMMENT '名称' ,
                                  dictionary_value VARCHAR(90) NOT NULL  DEFAULT '' COMMENT '值' ,
                                  order_no INT NOT NULL  DEFAULT 0 COMMENT '排序权重' ,
                                  module VARCHAR(90) NOT NULL  DEFAULT '' COMMENT '模块;分组使用,建议用英文、数字、下划线组合' ,
                                  pid VARCHAR(32) NOT NULL  DEFAULT '0' COMMENT '父级ID;当为0时，代表顶级' ,
                                  remarks VARCHAR(255) NOT NULL  DEFAULT '' COMMENT '备注' ,
                                  revision INT NOT NULL  DEFAULT 0 COMMENT '乐观锁' ,
                                  created_by VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '创建人' ,
                                  created_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
                                  updated_by VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '更新人' ,
                                  updated_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
                                  delete_flag tinyint(1) NOT NULL  DEFAULT 0 COMMENT '删除标识' ,
                                  PRIMARY KEY (id)
)  COMMENT = '字典表';

DROP TABLE IF EXISTS system_user;
CREATE TABLE system_user(
                            id VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '主键' ,
                            username VARCHAR(90) NOT NULL  DEFAULT '' COMMENT '用户名' ,
                            password VARCHAR(255) NOT NULL  DEFAULT '' COMMENT '密码' ,
                            status tinyint(1) NOT NULL  DEFAULT 0 COMMENT '状态(字典STATUS)' ,
                            revision INT NOT NULL  DEFAULT 0 COMMENT '乐观锁' ,
                            created_by VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '创建人' ,
                            created_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
                            updated_by VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '更新人' ,
                            updated_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
                            delete_flag tinyint(1) NOT NULL  DEFAULT 0 COMMENT '删除标识' ,
                            PRIMARY KEY (id)
)  COMMENT = '用户表';

DROP TABLE IF EXISTS system_user_role;
CREATE TABLE system_user_role(
                                 id VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '主键' ,
                                 user_id VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '用户ID' ,
                                 role_id VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '角色ID' ,
                                 PRIMARY KEY (id)
)  COMMENT = '用户角色中间表';

DROP TABLE IF EXISTS system_resource;
CREATE TABLE system_resource(
                                id VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '主键' ,
                                name VARCHAR(90) NOT NULL  DEFAULT '' COMMENT '权限名称' ,
                                css_class VARCHAR(255) NOT NULL  DEFAULT '' COMMENT 'css样式' ,
                                url VARCHAR(90) NOT NULL  DEFAULT '' COMMENT '后台url地址' ,
                                http_method VARCHAR(90) NOT NULL  DEFAULT '' COMMENT 'http请求方法' ,
                                route_path VARCHAR(90) NOT NULL  DEFAULT '' COMMENT '前端路由地址' ,
                                component_name VARCHAR(90) NOT NULL  DEFAULT '' COMMENT '前端组件名称' ,
                                component_path VARCHAR(90) NOT NULL  DEFAULT '' COMMENT '前端组件路径' ,
                                permission VARCHAR(90) NOT NULL  DEFAULT '' COMMENT '权限标识' ,
                                dictionary_value VARCHAR(90) NOT NULL  DEFAULT '' COMMENT '值' ,
                                order_no INT NOT NULL  DEFAULT 0 COMMENT '排序权重' ,
                                type VARCHAR(32) NOT NULL  DEFAULT 0 COMMENT '类型(字典PERMISSION_TYPE)' ,
                                revision INT NOT NULL  DEFAULT 0 COMMENT '乐观锁' ,
                                created_by VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '创建人' ,
                                created_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
                                updated_by VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '更新人' ,
                                updated_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
                                delete_flag tinyint(1) NOT NULL  DEFAULT 0 COMMENT '删除标识' ,
                                PRIMARY KEY (id)
)  COMMENT = '资源表';

DROP TABLE IF EXISTS system_role_resource;
CREATE TABLE system_role_resource(
                                     id VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '主键' ,
                                     role_id VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '角色ID' ,
                                     resource_id VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '资源ID' ,
                                     PRIMARY KEY (id)
)  COMMENT = '角色资源中间表';

DROP TABLE IF EXISTS system_department;
CREATE TABLE system_department(
                                  id VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '主键' ,
                                  pid VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '父级部门' ,
                                  name VARCHAR(90) NOT NULL  DEFAULT '' COMMENT '部门名称' ,
                                  order_no INT NOT NULL  DEFAULT 0 COMMENT '排序权重' ,
                                  remarks VARCHAR(255) NOT NULL  DEFAULT '' COMMENT '备注' ,
                                  revision INT NOT NULL  DEFAULT 0 COMMENT '乐观锁' ,
                                  created_by VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '创建人' ,
                                  created_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
                                  updated_by VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '更新人' ,
                                  updated_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
                                  delete_flag tinyint(1) NOT NULL  DEFAULT 0 COMMENT '删除标识' ,
                                  PRIMARY KEY (id)
)  COMMENT = '部门表';

DROP TABLE IF EXISTS system_user_department;
CREATE TABLE system_user_department(
                                       id VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '主键' ,
                                       user_id VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '用户ID' ,
                                       department_id VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '部门ID' ,
                                       PRIMARY KEY (id)
)  COMMENT = '用户部门表';

