package com.mgp.lcnservertest.plugs.ejob;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

//执行定时的监听，AbstractDistributeOnceElasticJobListener 是分布式下单节点执行的监听
//ElasticJobListener是全部节点执行的监听
//参考：http://elasticjob.io/docs/elastic-job-lite/02-guide/job-listener/
public class MyElasticJobListener implements ElasticJobListener {
    private static final Logger logger = LoggerFactory.getLogger(MyElasticJobListener.class);

/**
     * 长日期格式*/


    public static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private long beginTime = 0;

    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        beginTime = System.currentTimeMillis();

        logger.info("===>{} JOB BEGIN TIME: {} <===", shardingContexts.getJobName(), beginTime);
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        long endTime = System.currentTimeMillis();
        logger.info("===>{} JOB END TIME: {},TOTAL CAST: {} <===", shardingContexts.getJobName(),
                endTime, endTime - beginTime);
    }

/**
     * 将长整型数字转换为日期格式的字符串
     *
     * @param time
     * @param format
     * @return*/


    public static String convert2String(long time, String format) {
        if (time > 0l) {
            if (StringUtils.isBlank(format))
                format = TIME_FORMAT;

            SimpleDateFormat sf = new SimpleDateFormat(format);
            Date date = new Date(time);

            return sf.format(date);
        }
        return "";
    }
}
