package cn.config;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloRemoteClientFallbackFactory implements FallbackFactory<HelloRemoteClient> {
    private Logger logger =LoggerFactory.getLogger(HelloRemoteClientFallbackFactory.class);
    @Override
    public HelloRemoteClient create(final Throwable throwable) {
        logger.error("HelloRemoteClient回退",throwable);
        return new HelloRemoteClient() {
            @Override
            public String hello(String id) {
                return "fail";
            }
        };
    }
}
