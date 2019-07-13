package com.zerospace.datasport.model.controller.user;/**
 Created by 胡杰 on 2019/3/30. */

import com.zerospace.datasport.model.controller.BaseController;
import com.zerospace.datasport.model.domain.entity.User;
import com.zerospace.datasport.model.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * FileName: com.hjcc.ydItem.dataSport.control.user.UserInfoControl.java
 * Author: Jeremy_Hu
 * Date: 2019/3/30 下午8:15
 */
@RestController
public class UserInfoController extends BaseController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/hello")
    public List<User> hello(){
        List<User> user= null;
        user = userRepository.findAll();
    return user;
    }
}
