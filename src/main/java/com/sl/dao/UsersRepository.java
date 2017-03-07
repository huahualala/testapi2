package com.sl.dao;


import com.sl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 2017/3/2 0002.
 */
public interface UsersRepository extends JpaRepository<User, Long> {

    @Query("from User u where u.userName=:userName")
    User findUserByName(@Param("userName") String userName);

    @Query("from User u where u.userName=:userName and u.passWord=:passWord")
    User login(@Param("userName") String userName, @Param("passWord") String passWord);
}
