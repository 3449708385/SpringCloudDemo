package com.mgp.lcnservertest.service.impl;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.mgp.commons.bean.LcnTest;
import com.mgp.commons.bean.User;
import com.mgp.lcnservertest.dao.LcnTestMapper;
import com.mgp.lcnservertest.service.LcnTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LcnTestServiceImpl implements LcnTestService {

    @Autowired
    private LcnTestMapper lcnTestMapper;

    @Override
    @LcnTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional(rollbackFor=Exception.class)
    public int insertData(User user) {
        LcnTest lcnTest = new LcnTest();
        lcnTest.setAccressId("lcn01");
        lcnTest.setLcnName("test01");
        int i=1/0;
        lcnTestMapper.insert(lcnTest);
        return 0;
    }
}
