package com.mgp.customer.service;

import com.mgp.api.service.LcnApi;
import com.mgp.customer.service.impl.LcnServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;

// name: 服务提供者application.yml中的spring.application.name
// fallback: 断路器执行方法，即方法执行失败调用, LCN使用需要注意
@FeignClient(name="lcn-server-test", fallback = LcnServiceFallback.class)
public interface LcnService extends LcnApi {
}
