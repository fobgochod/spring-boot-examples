package com.fobgocod.api;

import com.fobgocod.domain.Hello;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康檢查
 *
 * @author zhouxiao
 * @date 2019/3/18
 */
@RestController
@RequestMapping(value = {"/api/hello"})
public class HelloController {

    @GetMapping
    public ResponseEntity<?> helloWho(@RequestParam String name) {
        return ResponseEntity.ok(String.format("Hello %s!", name));
    }

    @RequestMapping(value = "/name", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> hello(@RequestBody Hello hello) {
        hello.setSid(hello.getSid() + 1);
        hello.setId(hello.getId() + hello.getId());
        hello.setName(hello.getName() + hello.getName());
        return ResponseEntity.ok(hello);
    }

}
