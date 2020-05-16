package cn.dao;

import cn.entity.User;

public interface LoginMapper {
    User loginUser(String user_code);
}
