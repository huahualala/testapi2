package com.sl.controller;

/**
 * Created by Administrator on 2017/3/3 0003.
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/advice")
public class AdviceController {


    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1() {
        throw new RuntimeException("advice1 - exception1");
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2() {
        throw new RuntimeException("advice1 - exception2");
    }


}
