package com.sl.controller;

import com.sl.common.SerializedField;
import com.sl.entity.User;
import com.sl.service.impl.UsersServiceImp;
import com.sl.vo.ReturnVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/2 0002.
 */
@Api(value = "用户接口",description = "登录、注册、修改密码、上传头像等")
@RestController
@RequestMapping("/index")
public class UsersController{

    @Autowired
    private UsersServiceImp usersServiceImp;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册接口",notes = "app用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "string", paramType = "string"),
            @ApiImplicitParam(name = "passWord", value = "密码", required = true, dataType = "string", paramType = "string")
    })
    public ReturnVo registUser(String userName, String passWord) {
        ReturnVo obj = new ReturnVo();
        Map<Object, Object> params = new HashMap<Object, Object>();
        if (StringUtils.isEmpty(userName)) {
            obj.setMsg("用户名不能为空");
            obj.setErrCode(0);
        } else {
            params.put("username", userName);
        }
        if (StringUtils.isEmpty(passWord)) {
            obj.setMsg("密码不能为空");
            obj.setErrCode(0);
        } else {
            params.put("password", passWord);
        }
        try {
            usersServiceImp.saveUsers(obj,params);
        } catch (Exception e) {
            e.printStackTrace();
            obj.setMsg("系统异常");
            obj.setErrCode(0);
        }
        return obj;
    }

    @ApiOperation(value = "用户登录",notes = "用户登录验证用户名和密码，返回登录令牌",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名",required = true,dataType = "json",paramType = "string"),
            @ApiImplicitParam(name = "passWord", value = "密码",required = true,dataType = "json",paramType = "string")
    })
    @RequestMapping(value = "/logins",method = RequestMethod.POST)
    public ReturnVo login(String userName,String passWord){
        ReturnVo vo = new ReturnVo();
        if (StringUtils.isEmpty(userName)) {
            vo.setMsg("用户名不能为空");
            vo.setErrCode(0);
            return  vo;
        }
        if (StringUtils.isEmpty(passWord)) {
            vo.setMsg("密码不能为空");
            vo.setErrCode(0);
            return vo;
        }
        usersServiceImp.login(vo,userName,passWord);
        return vo;
    }


    @RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "获取单个用户",notes = "根据id获取某个用户的信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "json", paramType = "string")
    @SerializedField(includes = {"userName","passWord"},encode = false)
    public User findUserById(@PathVariable("id") String id){
        User user = usersServiceImp.findUserById(Long.valueOf(id));
        return user;
    }


    @RequestMapping(value = "/users",method = RequestMethod.GET)
    @ApiOperation(value = "获取所有用户",notes = "获取所有用户的用户信息")
    @SerializedField(excludes = {"passWord"})
    public List<User> findAllUsers(){
        List<User> users = usersServiceImp.findAllUsers();
        return users;
    }

}
