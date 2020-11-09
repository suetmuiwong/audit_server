package com.et.auditServer.modules.sys.service;

import com.et.auditServer.common.constant.Constant;
import com.et.auditServer.common.exception.BusinessException;
import com.et.auditServer.common.service.CrudService;
import com.et.auditServer.common.utils.DESUtil;
import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.common.utils.JsonReturnCode;
import com.et.auditServer.common.utils.MD5Util;
import com.et.auditServer.modules.sys.cache.errorCode.service.RetCodeTransferService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.et.auditServer.modules.sys.dao.UserDao;
import com.et.auditServer.modules.sys.dto.AddUserDto;
import com.et.auditServer.modules.sys.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: qgp
 * @create: 2019-03-01 17:20
 **/
@Service
public class UserService extends CrudService<UserDao,User> {
    @Autowired
    RetCodeTransferService retCodeTransferService;


    //查询用户列表
    public PageInfo<User>  getUserList( int pageNo, int pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<User> userInfoList = dao.selectAllUserList();
        PageInfo<User> pageInfo = new PageInfo<>(userInfoList);
        logger.info("分页查询用户信息成功");
        return pageInfo;
    }


    //根据userName获取用户数据
    public User getUserByUserName(String userName) {
        return dao.getUserByUserName(userName);
    }

   //根据用户名和密码获取用户数据
    public User getUserByPasswordUserName(String userName, String userPassword) {
        return dao.getUserByPasswordUserName(userName,userPassword);
    }



    //保存用户信息
    @Transactional
    public void addUserInfo(AddUserDto userDto)throws BusinessException {
        String password;
        //解密用户信息
        try {
            password = DESUtil.decryption(userDto.getUserPassword(), Constant.BOND_PSD_SECRETARIES);
        }catch (Exception e){
            throw new BusinessException(retCodeTransferService.getTransfer(JsonReturnCode.PASSWORD_DECRYPTION_ERROR));
        }

        if (StringUtils.isBlank(userDto.getUserName())) {
            throw new BusinessException(retCodeTransferService.getTransfer(JsonReturnCode.PARAM_ERROR));
        }
        User checkUserInfo = dao.getUserByUserName(userDto.getUserName());
        if(checkUserInfo != null){
            logger.info("登录账号已存在");
            throw new BusinessException(retCodeTransferService.getTransfer(JsonReturnCode.LOGINCODE_EXISTS));
        }
        //保存用户信息
        User userInfo = new User();
        userInfo.setUserName(userDto.getUserName());
        userInfo.setUserPhone(userDto.getUserPhone());
        userInfo.setUserEmail(userDto.getUserEmail());

        //设置密码
        userInfo.setUserPassword(MD5Util.getMD5StringBySalt(password, userDto.getUserName(),2));
        userInfo.preInsert(loginCode());
        dao.insertUser(userInfo);
        logger.info("保存用户信息成功");
    }

    //删除用户信息
    @Transactional(readOnly = false)
    public void deleteUserInfo(String userName){
        if(Constant.STR_ONE.equals(userName)) {//系统admin的userName
            throw new BusinessException(retCodeTransferService.getTransfer(JsonReturnCode.SYSTEM_USER));
        }
        logger.info("关联关系删除成功");
        dao.deleteUser(userName);
    }

    //更新用户信息
    @Transactional(readOnly = false)
    public void updateUserInfo(AddUserDto userDto){
        if (StringUtils.isBlank(userDto.getUserName())) {
            throw new BusinessException(retCodeTransferService.getTransfer(JsonReturnCode.PARAM_ERROR));
        }
        if(Constant.STR_ONE.equals(userDto.getUserName())) {//系统admin的userName
            throw new BusinessException(retCodeTransferService.getTransfer(JsonReturnCode.SYSTEM_USER));
        }
        User checkUserInfo = dao.get(userDto.getUserName());
        if(checkUserInfo == null) {
            logger.info("登录账号不存在");
            throw new BusinessException(retCodeTransferService.getTransfer(JsonReturnCode.LOGINCODE_NOT_EXISTS));
        } else if(checkUserInfo != null && !checkUserInfo.getUserName().equals(userDto.getUserName())){
            User user = dao.getUserByUserName(userDto.getUserName());
            if (user != null) {
                logger.info("登录账号已存在");
                throw new BusinessException(retCodeTransferService.getTransfer(JsonReturnCode.LOGINCODE_EXISTS));
            }
        }
        checkUserInfo.setUserName(userDto.getUserName());
        checkUserInfo.setUserPhone(userDto.getUserPhone());
        checkUserInfo.setUserEmail(userDto.getUserEmail());
        checkUserInfo.preUpdate(loginCode());
        logger.info("更新用户信息成功");
        dao.updateUser(checkUserInfo);
    }


    //重置密码
    @Transactional(readOnly = false)
    public JsonResult resetUserInfo(String userName){
        String defaultPasswordStr = "";
        String defaultPassword = null;
        try {
            defaultPassword = DESUtil.decryption(defaultPasswordStr, Constant.BOND_PSD_SECRETARIES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User userInfo = new User();
        userInfo.setUserName(userName);
        userInfo.preUpdate(loginCode());
        userInfo.setUserPassword(MD5Util.getMD5StringBySalt(defaultPassword,userName,2));
        dao.updateUser(userInfo);
        logger.info("重置员工密码成功");
        return JsonResult.success(defaultPassword);
    }


    @Transactional
    public void batchInsert(List<User> userList) {
        dao.batchInsert(userList);
    }


    public User getLoginUserInfo() {
        return dao.getUserByUserName(loginCode());
    }


    public List<User> selectUserByLikeUserNameOrUserPhone(String userNameOrLoginCode) {
        return dao.selectUserByLikeUserNameOrUserPhone(userNameOrLoginCode);
    }

    public List<User> selectAllUserList() {
        return dao.selectAllUserList();
    }

    @Transactional
    public void batchSynUpdate(List<User> updateUserList) {
        dao.batchSynUpdate(updateUserList);
    }
}
