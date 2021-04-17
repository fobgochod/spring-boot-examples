package com.fobgochod.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

/**
 * 健康檢查
 *
 * @author zhouxiao
 * @date 2019/3/18
 */
@RestController
public class EnvController {

    @Value("${app.version}")
    private String version;
    @Value("${app.build.time}")
    private String buildTime;

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> health() {
        return ResponseEntity.ok(new LinkedHashMap<String, String>(3) {{
            put("version", version);
            put("build-time", buildTime);
            put("server-time", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        }});
    }
}
