/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost
 Source Database       : mumu

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : utf-8

 Date: 12/30/2021 19:22:35 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `system_department`
-- ----------------------------
DROP TABLE IF EXISTS `system_department`;
CREATE TABLE `system_department` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `pid` varchar(32) NOT NULL DEFAULT '' COMMENT '父级部门',
  `name` varchar(90) NOT NULL DEFAULT '' COMMENT '部门名称',
  `order_no` int NOT NULL DEFAULT '0' COMMENT '排序权重',
  `remarks` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `revision` int NOT NULL DEFAULT '0' COMMENT '乐观锁',
  `created_by` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';

-- ----------------------------
--  Table structure for `system_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `system_dictionary`;
CREATE TABLE `system_dictionary` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `code` varchar(90) NOT NULL DEFAULT '' COMMENT 'code编码',
  `name` varchar(90) NOT NULL DEFAULT '' COMMENT '名称',
  `dictionary_value` varchar(90) NOT NULL DEFAULT '' COMMENT '值',
  `order_no` int NOT NULL DEFAULT '0' COMMENT '排序权重',
  `module` varchar(90) NOT NULL DEFAULT '' COMMENT '模块;分组使用,建议用英文、数字、下划线组合',
  `pid` varchar(32) NOT NULL DEFAULT '0' COMMENT '父级ID;当为0时，代表顶级',
  `remarks` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `revision` int NOT NULL DEFAULT '0' COMMENT '乐观锁',
  `created_by` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典表';

-- ----------------------------
--  Records of `system_dictionary`
-- ----------------------------
BEGIN;
INSERT INTO `system_dictionary` VALUES ('1467768368839794689', 'STATUS', '状态', '0', '0', 'SYSTEM', '0', '系统状态', '0', '', '2021-12-06 16:10:23', '', '2021-12-06 16:10:23', '0'), ('1467768842645151746', 'STATUS', '正常', '0', '0', 'SYSTEM', '1467768368839794689', '系统状态', '0', '', '2021-12-06 16:12:16', '', '2021-12-06 16:12:16', '0'), ('1467768885401886722', 'STATUS', '禁用', '1', '0', 'SYSTEM', '1467768368839794689', '系统状态', '0', '', '2021-12-06 16:12:26', '', '2021-12-06 16:12:26', '0'), ('1467769359710560257', 'PERMISSION_TYPE', '权限类型', '0', '0', 'SYSTEM', '0', '权限类型', '0', '', '2021-12-06 16:14:19', '', '2021-12-06 16:14:19', '0'), ('1467769509518516225', 'PERMISSION_TYPE', '目录', '0', '0', 'SYSTEM', '1467769359710560257', '权限类型', '0', '', '2021-12-06 16:14:55', '', '2021-12-06 16:14:55', '0'), ('1467769579303346178', 'PERMISSION_TYPE', '菜单', '1', '1', 'SYSTEM', '1467769359710560257', '权限类型', '0', '', '2021-12-06 16:15:12', '', '2021-12-06 16:15:12', '0'), ('1467769630209613826', 'PERMISSION_TYPE', '按钮', '2', '1', 'SYSTEM', '1467769359710560257', '权限类型', '0', '', '2021-12-06 16:15:24', '', '2021-12-06 16:15:24', '0'), ('1467769854458077185', 'DATA_PERMISSION_TYPE', '数据权限类型', '0', '0', 'SYSTEM', '0', '数据权限类型', '0', '', '2021-12-06 16:16:17', '', '2021-12-06 16:16:17', '0'), ('1467769971915366402', 'DATA_PERMISSION_TYPE', '全部权限', '0', '0', 'SYSTEM', '1467769854458077185', '全部权限', '0', '', '2021-12-06 16:16:45', '', '2021-12-06 16:16:45', '0'), ('1467770029738041345', 'DATA_PERMISSION_TYPE', '部门权限', '1', '1', 'SYSTEM', '1467769854458077185', '部门权限', '0', '', '2021-12-06 16:16:59', '', '2021-12-06 16:18:45', '0'), ('1467770092245753857', 'DATA_PERMISSION_TYPE', '个人权限', '2', '1', 'SYSTEM', '1467769854458077185', '个人权限', '0', '', '2021-12-06 16:17:14', '', '2021-12-06 16:17:14', '0'), ('1467770147967082497', 'DATA_PERMISSION_TYPE', '自定义权限', '3', '1', 'SYSTEM', '1467769854458077185', '自定义权限', '0', '', '2021-12-06 16:17:27', '', '2021-12-06 16:17:27', '0');
COMMIT;

-- ----------------------------
--  Table structure for `system_resource`
-- ----------------------------
DROP TABLE IF EXISTS `system_resource`;
CREATE TABLE `system_resource` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `pid` varchar(32) NOT NULL DEFAULT '0' COMMENT '父ID',
  `name` varchar(90) NOT NULL DEFAULT '' COMMENT '权限名称',
  `css_class` varchar(255) NOT NULL DEFAULT '' COMMENT 'css样式',
  `url` varchar(90) NOT NULL DEFAULT '' COMMENT '后台url地址',
  `http_method` varchar(90) NOT NULL DEFAULT '' COMMENT 'http请求方法',
  `route_path` varchar(90) NOT NULL DEFAULT '' COMMENT '前端路由地址',
  `component_name` varchar(90) NOT NULL DEFAULT '' COMMENT '前端组件名称',
  `component_path` varchar(90) NOT NULL DEFAULT '' COMMENT '前端组件路径',
  `permission` varchar(90) NOT NULL DEFAULT '' COMMENT '权限标识',
  `order_no` int NOT NULL DEFAULT '0' COMMENT '排序权重',
  `type` varchar(32) NOT NULL DEFAULT '0' COMMENT '类型(字典PERMISSION_TYPE)',
  `revision` int NOT NULL DEFAULT '0' COMMENT '乐观锁',
  `created_by` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源表';

-- ----------------------------
--  Records of `system_resource`
-- ----------------------------
BEGIN;
INSERT INTO `system_resource` VALUES ('1467774940731207681', '0', '系统管理', 'system', '', '', '', '', '', '', '0', '0', '0', '', '2021-12-06 16:36:30', '', '2021-12-06 16:42:51', '0'), ('1467779280988540930', '1467774940731207681', '角色管理', 'role', '/role/page', '', '', '', '', 'role:list', '0', '0', '0', '', '2021-12-06 16:53:45', '', '2021-12-06 16:53:45', '0'), ('1467779672904306689', '1467774940731207681', '用户管理', 'user', '/user/page', '', '', '', '', 'user:list', '0', '0', '0', '', '2021-12-06 16:55:18', '', '2021-12-06 16:55:18', '0');
COMMIT;

-- ----------------------------
--  Table structure for `system_role`
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `code` varchar(90) NOT NULL DEFAULT '' COMMENT '角色编码',
  `permission_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '权限类型(字典DATA_PERMISSION_TYPE)',
  `name` varchar(90) NOT NULL DEFAULT '' COMMENT '角色名称',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态(字典STATUS)',
  `revision` int NOT NULL DEFAULT '0' COMMENT '乐观锁',
  `created_by` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
--  Records of `system_role`
-- ----------------------------
BEGIN;
INSERT INTO `system_role` VALUES ('1467780856469901313', 'admin', '0', '超级管理员', '0', '0', '', '2021-12-06 17:00:00', '', '2021-12-06 17:00:00', '0');
COMMIT;

-- ----------------------------
--  Table structure for `system_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `system_role_resource`;
CREATE TABLE `system_role_resource` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `role_id` varchar(32) NOT NULL DEFAULT '' COMMENT '角色ID',
  `resource_id` varchar(32) NOT NULL DEFAULT '' COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色资源中间表';

-- ----------------------------
--  Records of `system_role_resource`
-- ----------------------------
BEGIN;
INSERT INTO `system_role_resource` VALUES ('1', '1467780856469901313', '1467774940731207681'), ('2', '1467780856469901313', '1467779280988540930'), ('3', '1467780856469901313', '1467779672904306689');
COMMIT;

-- ----------------------------
--  Table structure for `system_user`
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `username` varchar(90) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态(字典STATUS)',
  `revision` int NOT NULL DEFAULT '0' COMMENT '乐观锁',
  `created_by` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
--  Records of `system_user`
-- ----------------------------
BEGIN;
INSERT INTO `system_user` VALUES ('1', 'ekko', '123456', '1', '0', '', '2021-12-06 16:07:45', '', '2021-12-06 16:07:45', '0');
COMMIT;

-- ----------------------------
--  Table structure for `system_user_department`
-- ----------------------------
DROP TABLE IF EXISTS `system_user_department`;
CREATE TABLE `system_user_department` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `department_id` varchar(32) NOT NULL DEFAULT '' COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户部门表';

-- ----------------------------
--  Table structure for `system_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `role_id` varchar(32) NOT NULL DEFAULT '' COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色中间表';

-- ----------------------------
--  Records of `system_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `system_user_role` VALUES ('1', '1', '1467780856469901313');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
