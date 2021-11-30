package com.mumu.web.common.controller;

import com.mumu.web.common.model.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ekko
 * @description
 * @create 2021-11-25 1:58 下午
 */
@RestController
@RequestMapping("restful")
public class TestController {

    @GetMapping("hello")
    public R<String> hello(String hello){
        return R.success(hello);
    }
}
