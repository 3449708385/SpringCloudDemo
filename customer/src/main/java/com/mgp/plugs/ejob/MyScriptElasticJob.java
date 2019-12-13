package com.mgp.plugs.ejob;

import com.dangdang.ddframe.job.api.script.ScriptJob;
import org.springframework.stereotype.Component;


//这种方式通过注解实现我没有成功，可以直接通过代码或配置添加script任务(scriptCommandLine是要执行的命令)，
//LiteJobConfiguration.newBuilder(new ScriptJobConfiguration(JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount)
//                .shardingItemParameters(shardingItemParameters).jobParameter(jobParameters).failover(true).misfire(true).build(),
//                scriptCommandLine)
// 如果需要可以试试这个：
//https://blog.csdn.net/elim168/article/details/79017452
//https://blog.csdn.net/LOVELONG8808/article/details/80352050
@Component
/*@ElasticScheduler(cron = "0/20 * * * * ?",shardingTotalCount = 4, name = "scriptJobTask",
        shardingItemParameters = "0=a,1=b,2=c,3=d",jobParameters = "a", jobType = "script")*/
public class MyScriptElasticJob implements ScriptJob {
}
