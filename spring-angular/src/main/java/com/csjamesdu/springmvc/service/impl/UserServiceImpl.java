package com.csjamesdu.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csjamesdu.springmvc.dao.UserDao;
import com.csjamesdu.springmvc.model.User;
import com.csjamesdu.springmvc.service.UserService;


import java.util.concurrent.atomic.AtomicLong;
 
 
@Service("userService")
public class UserServiceImpl implements UserService{
     
    private static final AtomicLong counter = new AtomicLong();
     
     
//    static{
//        users= populateDummyUsers();
//    }
	
	@Autowired
	UserDao userDao;
 
    public List<User> findAllUsers() {
        return userDao.list();
    }
     
    public User findByName(String name) {
    	List<User> users = userDao.list();
        for(User user : users){
            if(user.getUsername().equalsIgnoreCase(name)){
                return user;
            }
        }
        return null;
    }
     
    public void saveUser(User user) {
 //       user.setId(counter.incrementAndGet());
        userDao.add(user);
    }
 
    public void updateUser(User user) {
        userDao.update(user);
    }
 
    public void deleteUserById(long id) {
        userDao.delete(findById(id));
    }
 
    public boolean isUserExist(User user) {
        return findByName(user.getUsername())!=null;
    }
     
//    public void deleteAllUsers(){
//        userDao.
//    }
 


	@Override
	public User findById(long id) {
		return userDao.loadById(id);
	}

}
