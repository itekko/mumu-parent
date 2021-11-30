package com.mumu.web.system;


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
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
