package cn.config;

import cn.entity.UserDTO;

public class LoginRemoteClientFallback implements LoginRemoteClient {
    @Override
    public String login(UserDTO user) {
        return "fail";
    }
}
