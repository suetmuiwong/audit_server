package com.et.auditServer.modules.sys.dao;

import com.et.auditServer.common.persistence.CrudDao;
import com.et.auditServer.modules.sys.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao extends CrudDao<User> {
    //根据账号密码查询用户
    User getUserByUserName(@Param("userName") String userName);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(@Param("userName") String userName);

    User getUserByPasswordUserName(@Param("userName")String userName, @Param("userPassword") String userPassword);

    void batchInsert(@Param("userList") List<User> userList);

    List<User> selectUserByLikeUserNameOrUserPhone(@Param("userNameOrUserPhone") String userNameOrUserPhone);

    List<User> findByIds(@Param("idList") List<String> idList);

    List<User> selectAllUserList();

    void batchSynUpdate(@Param("updateUserList") List<User> updateUserList);

    /*
     * 根据用户名查询用户信息
     */
    User getUser(@Param("userName")String userName);

}
