package com.sl.dao;


import com.sl.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 2017/3/2 0002.
 */
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("from Users u where u.userName=:userName")
    Users findUserByName(@Param("userName") String userName);

    @Query("from Users u where u.userName=:userName and u.passWord=:passWord")
    Users login(@Param("userName") String userName, @Param("passWord") String passWord);
}
