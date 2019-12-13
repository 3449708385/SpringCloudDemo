package com.mgp.lcnservertest.plugs.ejob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.util.ArrayList;
import java.util.List;

@ElasticScheduler(cron = "0/20 * * * * ?",shardingTotalCount = 2, name = "dataFlowJobTask",
        shardingItemParameters = "0=a,1=b",jobParameters = "dataFlow", jobType = "dataFlow")
public class MyDataFlowElasticJob  implements DataflowJob<String> {

    @Override
    public List<String> fetchData(ShardingContext context) {
        List<String> data = new ArrayList<String>();
        switch (context.getShardingItem()) {
            case 0:
                data.add("1");
                data.add("2");
                data.add("3");
                return data;
           /* case 1:
                data.add("4");
                data.add("5");
                data.add("6");
                return data;
            case 2:
                data.add("7");
                data.add("8");
                data.add("9");
                return data;*/
            default:
                data.add("10");
                return data;
        }
    }

    @Override
    public void processData(ShardingContext shardingContext, List<String> data) {
        // process data
        System.out.println(shardingContext.getShardingItem());
        System.out.println(shardingContext.getShardingParameter());
        System.out.println(data.toString());
    }
}
