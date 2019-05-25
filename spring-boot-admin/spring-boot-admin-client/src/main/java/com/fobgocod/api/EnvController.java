package com.fobgocod.api;

import com.fobgocod.domain.EnvProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    EnvProperties envProperties;

    /**
     * 是否可行
     */
    @GetMapping(value = {""}, produces = "application/json; charset=utf-8")
    public ResponseEntity<?> getRuntimeEnv() {
        envProperties.refresh();
        return ResponseEntity.ok(envProperties);
    }

    @RequestMapping(value = "/health", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> health() {
        return ResponseEntity.ok().build();
    }
}
