package com.csjamesdu.springmvc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.csjamesdu.springmvc.dao.UserDao;
import com.csjamesdu.springmvc.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	private SessionFactory sessionFactory;
	
	@Resource(name="LocalSessionFactoryBean")
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> list() {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from User");
		List<User> userList = q.list();
		for(User user: userList){
			logger.info("UserList::" + user);
		}
		
		session.getTransaction().commit();
		session.close();
		return userList;
		
		
	}

	@Override
	public User loadById(Long id) {
		logger.info("User number: "+ id + " loaded successfully");
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		User user = (User)session.get(User.class, id);
		session.getTransaction().commit();
		session.close();
		return user;
	}

	@Override
	public void update(User user) {
		
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		logger.info("User "+user+" has been updated");
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(User user) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		logger.info("User "+user+" has been deleted");
		session.delete(user);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public void add(User user) {
		//logger.info("ADD User: " + user.getName() + " Successfully!");
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}

	
}
