package com.fobgocod.api;

import com.fobgochod.sample.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: Xiao
 * @date: 2022/3/16 15:42
 * @description: 功能描述
 */
@RestController
public class DemoController {

    @DubboReference(version = "${demo.service.version}")
    private DemoService demoService;

    @GetMapping("/demo/{name}")
    public ResponseEntity<?> test(@PathVariable String name) {
        return ResponseEntity.ok(demoService.sayHello(name));
    }
}
