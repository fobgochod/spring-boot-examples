package com.fobgocod.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康檢查
 *
 * @author zhouxiao
 * @date 2019/3/18
 */
@RestController
@RequestMapping(value = {"/api/env"})
public class EnvController {

    @RequestMapping(value = "/health", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> health() {
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
