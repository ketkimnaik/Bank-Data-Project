package com.springboot.bank.bankDataProject.DAO;


import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bank.bankDataProject.POJO.SwiftDetails;

@Repository
public class SwiftDAOImp implements SwiftDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public SwiftDetails findBySwift(String code) {
		
//		get the current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
//		now retrieve/read from database using email
		Query<SwiftDetails> query = session.createQuery("from SwiftDetails where swift=:uCode", SwiftDetails.class);
		query.setParameter("uCode", code);
		
		SwiftDetails swiftDetails = null;
		
		try {
			swiftDetails = query.getSingleResult();
		} catch (Exception e) {
			swiftDetails = null;
		}

		return swiftDetails;
	}

}
