package com.mgp.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

@Component("myZuullFilter")
public class MyZuullFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";//这里是设置过滤器类型
    }

    @Override
    public int filterOrder() {
        return 0;  //设置执行顺序
    }

    @Override
    public boolean shouldFilter() {
        return true;  //该过滤器需要执行
    }

    @Override
    public Object run() {
        //这里进行验证，验证通过设置可以路由，不通过直接输出返回
        //参考：https://www.cnblogs.com/a8457013/p/8352349.html
        RequestContext requestContext= RequestContext.getCurrentContext();
        System.out.println("filter");
        System.out.println(requestContext.getRequest().getMethod());
        //requestContext.setSendZuulResponse(true);//默认为true
        //requestContext.setResponseStatusCode(200);
        return null;
    }
}
