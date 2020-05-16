package cn.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class MyHystrixCommand extends HystrixCommand<String> {
    protected MyHystrixCommand(String name) {
//        super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));
        super(HystrixCommand.Setter
        .withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup"))
        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)));
    }

    @Override
    protected String run() throws Exception {
        try {
            Thread.sleep(1000*10);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Thread.currentThread().getName();
    }
    public String defaultCallHello(){
        return "fail";
    }
}
