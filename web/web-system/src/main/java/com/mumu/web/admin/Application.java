package com.mumu.web.admin;


import com.mumu.common.constant.CommonConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * admin 启动类
 * @author ekko
 * @description
 * @create 2021-11-23 7:35 下午
 */
@SpringBootApplication(scanBasePackages= CommonConstants.COMMON_PACKAGE)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
