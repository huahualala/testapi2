package com.sl.service.impl;

import com.sl.dao.UsersRepository;
import com.sl.entity.User;
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
        User user = new User();
        try {
            User oldUser = usersRepository.findUserByName(params.get("username").toString());
            if (oldUser == null){
                user.setUserName(params.get("username").toString());
                user.setPassWord(DigestUtils.md5DigestAsHex(params.get("password").toString().getBytes()));
                user.setToken(DesUtil.encrypt(params.get("username").toString()));
                user.setCreateTime(new Timestamp(System.currentTimeMillis()));
                user.setUserState(1);
                user.setLoginState(0);
                usersRepository.save(user);
            }else{
                vo.setErrCode(0);
                vo.setMsg("用户名已存在，请重新输入！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User findUserById(Long id){
        return usersRepository.findOne(id);
    }

    public List<User> findAllUsers(){
        return usersRepository.findAll();
    }

    public void login(ReturnVo vo,String username,String password){
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = usersRepository.login(username,password);
        if(user == null){
            vo.setErrCode(0);
            vo.setMsg("登录失败，请检查用户名、密码和登录令牌");
        }else{
            user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
            user.setLoginState(1);
            HashMap<String, Object> result = new HashMap<>();
            result.put("token", user.getToken());
            result.put("business_type",user.getBusiness().getBusinessType().getBusinessTypeId());
            vo.setDatas((Map)result);
        }
    }

}
