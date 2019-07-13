package com.zerospace.datasport.joggle.service;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 Created by 胡杰 on 2019/7/14. */


@RequestMapping(value = "/client1")
public interface BaseService {

    @RequestMapping(value = "/inner/test")
    String innerTest();
}
