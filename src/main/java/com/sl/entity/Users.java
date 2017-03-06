package com.sl.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/3/2 0002.
 */
@Entity
@Table(name = "users")
public class Users extends BaseEntity{

    private Long userId;
    private String userName;
    private String passWord;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp lastLoginTime;
    private Integer loginState;//0:注销(default)，1:登录
    private Integer userState;//0:关闭，1:开启(default),2:删除

    @JsonIgnore
    private String token;

    public Users(Long userId, String userName, String passWord, Timestamp createTime, Timestamp lastLoginTime, Integer loginState, Integer userState, String token) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
        this.createTime = createTime;
        this.lastLoginTime = lastLoginTime;
        this.loginState = loginState;
        this.userState = userState;
        this.token = token;
    }

    public Users() {
    }

    @Id
    @GeneratedValue
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "pass_word")
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Column(name = "last_login_time")
    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(name = "login_state")
    public Integer getLoginState() {
        return loginState;
    }

    public void setLoginState(Integer loginState) {
        this.loginState = loginState;
    }

    @Column(name = "user_state")
    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", createTime=" + createTime +
                ", lastLoginTime=" + lastLoginTime +
                ", loginState=" + loginState +
                ", userState=" + userState +
                ", token='" + token + '\'' +
                '}';
    }
}
