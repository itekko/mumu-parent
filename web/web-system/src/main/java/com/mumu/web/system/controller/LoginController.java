package com.mumu.web.system.controller;

/**
 * @author ekko
 * @description
 * @create 2021-12-02 7:28 下午
 */

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.SecureUtil;
import com.mumu.db.common.builder.BaseBuilder;
import com.mumu.service.system.service.LoginService;
import com.mumu.web.common.model.R;
import com.mumu.web.system.vo.in.LoginVO;
import com.mumu.web.system.vo.in.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 登录web层
 * @author ekko
 * @create 2021-12-02 17:29:17
 */
@Api(tags = "登录")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("home")
public class LoginController {

    private final LoginService loginService;

    /**
     * 登录
     * @param
     * @return
     */
    @ApiOperation(value = "登录")
    @PostMapping("login")
    public R<String> login(@RequestBody LoginVO loginVO){
        String loginId = loginService.login(loginVO.getUsername(), loginVO.getPassword());
        // 登录
        StpUtil.login(loginId);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return R.success(tokenInfo.getTokenValue());
    }

    /**
     * 用户信息
     * @param
     * @return
     */
    @ApiOperation(value = "用户信息")
    @GetMapping("getUserInfo")
    public R<UserInfoVO> getUserInfo(){
        return R.success(BaseBuilder.copyProperties(loginService.getUserInfo(),UserInfoVO.class));
    }


    /**
     * 登录
     * @param
     * @return
     */
    @ApiOperation(value = "登出")
    @GetMapping("loginout")
    public R<Void> login(){
        Object loginId = StpUtil.getLoginId();
        log.info("登录ID:{}",loginId);
        StpUtil.logout();
        return R.success(null);
    }


    public static void main(String[] args) {
        System.out.println(SecureUtil.md5("admin"));
    }
}
