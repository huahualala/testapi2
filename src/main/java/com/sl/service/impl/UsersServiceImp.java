package com.sl.service.impl;

import com.sl.dao.UsersRepository;
import com.sl.entity.Users;
import com.sl.utils.DesUtil;
import com.sl.vo.ReturnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/2 0002.
 */
@Service
public class UsersServiceImp {

    @Autowired
    private UsersRepository usersRepository;

    @Transactional
    public void saveUsers(ReturnVo vo,Map<Object, Object> params){
        Users users = new Users();
        try {
            Users oldUser = usersRepository.findUserByName(params.get("username").toString());
            if (oldUser == null){
                users.setUserName(params.get("username").toString());
                users.setPassWord(DigestUtils.md5DigestAsHex(params.get("password").toString().getBytes()));
                users.setToken(DesUtil.encrypt(params.get("username").toString()));
                users.setCreateTime(new Timestamp(System.currentTimeMillis()));
                users.setUserState(1);
                users.setLoginState(0);
                usersRepository.save(users);
            }else{
                vo.setErrCode(0);
                vo.setMsg("用户名已存在，请重新输入！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Users findUserById(Long id){
        return usersRepository.findOne(id);
    }

    public List<Users> findAllUsers(){
        return usersRepository.findAll();
    }

    public void login(ReturnVo vo,String username,String password){
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        Users users = usersRepository.login(username,password);
        if(users == null){
            vo.setErrCode(0);
            vo.setMsg("登录失败，请检查用户名、密码和登录令牌");
        }else{
            users.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
            users.setLoginState(1);
            HashMap<String, Object> result = new HashMap<>();
            result.put("token",users.getToken());
            vo.setDatas((Map)result);
        }
    }

}
