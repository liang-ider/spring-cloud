package cn.servers;

import cn.dao.LoginMapper;
import cn.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServerImpl implements LoginServer {
    @Resource
    LoginMapper loginMapper;
    @Override
    public boolean loginUser(User user) {
        System.out.println(user.getUser_code()+":"+user.getPassword()+"===================");
        User user1 = loginMapper.loginUser(user.getUser_code());
        if (user1!=null){
            if (user.getPassword().equals(user1.getPassword())){
                return true;
            }
        }
        return false;
    }
}
