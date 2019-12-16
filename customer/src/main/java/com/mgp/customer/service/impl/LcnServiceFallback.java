package com.mgp.customer.service.impl;

import com.codingapi.txlcn.tc.support.DTXUserControls;
import com.codingapi.txlcn.tracing.TracingContext;
import com.mgp.commons.bean.User;
import com.mgp.customer.service.LcnService;
import org.springframework.stereotype.Service;

/*
  用来fegion 容错的
 */
@Service("lcnService")
public class LcnServiceFallback implements LcnService {
    @Override
    public int insertData(User user) {
        //有熔断的话必须加这个，才可以支持LCN事物
        DTXUserControls.rollbackGroup(TracingContext.tracing().groupId());
        return 0;
    }
}
