package cn.config;

import cn.entity.UserDTO;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginRemoteClientFallbackFactory implements FallbackFactory<LoginRemoteClient> {
    private Logger logger =LoggerFactory.getLogger(LoginRemoteClientFallbackFactory.class);
    @Override
    public LoginRemoteClient create(final Throwable throwable) {
        logger.error("LoginRemoteClient回退",throwable);
        return new LoginRemoteClient() {
            @Override
            public String login(UserDTO user) {
                return "fail";
            }
        };
    }
}
