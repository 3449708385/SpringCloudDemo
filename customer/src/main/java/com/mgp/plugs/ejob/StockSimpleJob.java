package com.mgp.plugs.ejob;


import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.springframework.stereotype.Component;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/7/17 17:18
 * 分片必须是一个项目启动多次，不局限于ip，他是通过ip或任务标识区别的。
 * 注意：不同项目相同包名+类名，后启动的项目不会执行定时
 */
@Component
/*@ElasticScheduler(cron = "0/20 * * * * ?",shardingTotalCount = 4, name = "simpleJobTask",
        shardingItemParameters = "0=a,1=b,2=c,3=d",jobParameters = "a", jobType = "simple")*/
public class StockSimpleJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
       /* System.out.println(String.format("------Thread ID: %s, 任務總片數: %s, " +
                        "當前分片項: %s.當前參數: %s," +
                        "當前任務名稱: %s.當前任務參數: %s"
                ,
                Thread.currentThread().getId(),
                shardingContext.getShardingTotalCount(),
                shardingContext.getShardingItem(),
                shardingContext.getShardingParameter(),
                shardingContext.getJobName(),
                shardingContext.getJobParameter()

        ));
        System.out.println("simpleJob 线程开始执行");*/

       switch(shardingContext.getShardingItem()){
          case 0:
              System.out.println(shardingContext.getShardingParameter());
              break;
          case 1:
              System.out.println(shardingContext.getShardingParameter());
              break;
          case 2:
              System.out.println(shardingContext.getShardingParameter());
              try {
                  Thread.sleep(10000);
              }catch(Exception e){

              }
              break;
          default:
              System.out.println(shardingContext.getShardingParameter());
              try {
                  Thread.sleep(10000);
              }catch(Exception e){

              }
              break;
       }
    }
}
