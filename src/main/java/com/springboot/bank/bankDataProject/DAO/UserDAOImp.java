package com.springboot.bank.bankDataProject.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bank.bankDataProject.POJO.User;

@Repository
public class UserDAOImp implements UserDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public User findByUsername(String username) {
		
//		get the current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
//		now retrieve/read from database using username
		Query<User> query = session.createQuery("from User where userName=:uName", User.class);
		query.setParameter("uName", username);
		
		User user = null;
		
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			user = null;
		}

		return user;
	}

	@Override
	public void save(User user) {
//		get the current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
//		save the user in database
		session.saveOrUpdate(user);
	}

}
