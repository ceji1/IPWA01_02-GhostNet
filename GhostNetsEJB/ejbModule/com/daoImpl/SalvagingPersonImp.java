package com.daoImpl;

import java.util.List;

import com.dao.IGhostNets;
import com.dao.ISalvagingPerson;
import com.entities.GhostNets;
import com.entities.SalvagingPerson;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class SalvagingPersonImp implements ISalvagingPerson{
	
	@PersistenceContext
	private EntityManager em ;


	@Override
	public SalvagingPerson addOne(SalvagingPerson salvagingPerson) {
		em.persist(salvagingPerson);
		return salvagingPerson ;  
		
	}

	@Override
	public GhostNets getSalvingPersonByEmailAndPassword(String email, String password) {
		TypedQuery<GhostNets> query = em.createQuery("SELECT s FROM SalvingPerson s WHERE s.email = :email and s.password = :password", GhostNets.class);
        query.setParameter("email", email);
        query.setParameter("password", password);

        return query.getSingleResult() ;		
	}

	@Override
	public SalvagingPerson login(String email, String password) {
		
		try {
			TypedQuery<SalvagingPerson> query = em.createQuery("SELECT s FROM SalvagingPerson s WHERE s.email = :email And s.password = :password", SalvagingPerson.class);
	        query.setParameter("email", email);
	        query.setParameter("password", password);
			return query.getSingleResult() ; 
		}catch(Exception e) {
			System.out.println(e);
			return null ; 
		}
		
	}

	

	
	
	
	
	

}
