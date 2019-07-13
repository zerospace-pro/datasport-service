package com.zerospace.datasport.joggle.service.feign;

import com.zerospace.datasport.joggle.service.BaseService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 Created by 胡杰 on 2019/7/14. */

@FeignClient(name = "dataSport-model")
public interface TestFeignService extends BaseService {


}
