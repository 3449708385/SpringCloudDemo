package com.mgp.customer.service.impl;

import com.mgp.commons.bean.User;
import com.mgp.customer.service.LcnService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/*
  用来fegion 容错的
 */
@Service("lcnService")
public class LcnServiceFallback implements LcnService {
    @Override
    public int insertData(User user) {
        return 0;
    }
}
