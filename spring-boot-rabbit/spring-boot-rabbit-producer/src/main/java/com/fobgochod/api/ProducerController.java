package com.fobgochod.api;

import com.fobgochod.consumer.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = {"/api/producer"})
public class ProducerController {

    @Autowired
    ProducerService producerService;

    @RequestMapping(value = "/{message}", method = RequestMethod.GET)
    public ResponseEntity<?> health(@PathVariable String message) {
        producerService.send(message);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
