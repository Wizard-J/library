package com.library.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.dao.UserDao;
import com.library.entity.User;

@Service
@Transactional(readOnly=false)
public class UserService {

	@Autowired
    private UserDao userDao;
	
	
	/**
	 * 返回全部用户列表
	 * @return
	 */
	public ArrayList<User> getUserList(){
		return userDao.getAllUserInfo();
	}

	/**
	 * ID查找用户信息
	 * @param id
	 * @return
	 */
    public User selectUserById(String id){
        return userDao.selectUserById(id);
    }
    
    /**
     * 账号查找用户信息
     * @param id
     * @return
     */
    public User selectUserByAccount(String account){
    	return userDao.selectUserByAccount(account);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    public int deleteUserById(String id){
    	return userDao.deleteUser(id);
    }
    
    /**
     * 新增用户
     * @param user
     * @return
     */
    public int createUser(User user){
    	return userDao.createUser(user);
    }
    
   /**
    * 更新用户
    * @param user
    * @return
    */
    public int updateUser(User user){
        return userDao.updateUser(user);
    }

}
