package com.daoImpl;

import java.util.List;

import com.dao.IGhostNets;
import com.dao.ILocation;
import com.entities.GhostNets;
import com.entities.Location;
import com.entities.SalvagingPerson;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class LocationImpl implements ILocation  {

	@PersistenceContext(unitName = "UP_GHOSTNETS" )
	private EntityManager em ; 
	
	
	@Override
    public List<Location> getAll() {
        TypedQuery<Location> query = em.createQuery("SELECT l FROM Location l", Location.class);
        return query.getResultList();
    }


	
}
