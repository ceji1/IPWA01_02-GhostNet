package com.daoImpl;

import java.util.List;

import com.dao.IGhostNets;
import com.entities.GhostNets;
import com.entities.SalvagingPerson;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class GhostNetsImp implements IGhostNets  {

	@PersistenceContext(unitName = "UP_GHOSTNETS" )
	private EntityManager em ; 
	
	
	@Override
	public List<GhostNets> getAll() {
		Query req = em.createQuery("Select g from GhostNets g ") ; 
		return req.getResultList();
	}


	@Override
	public GhostNets getOne(Long id) {
		GhostNets ghostNets = em.find(GhostNets.class, id) ; 
		if(ghostNets == null) throw new RuntimeException("Data not found")  ; 
		return ghostNets ; 
	}


	@Override
	public void addOne(GhostNets ghostNets) {
		em.persist(ghostNets);
	}


	@Override 
	public GhostNets updateOne(Long id , GhostNets ghostNets) {
		GhostNets ghNets =  getOne(id) ; 
		ghNets.setLocation(ghostNets.getLocation());
		ghNets.setSize(ghostNets.getSize());
		ghNets.setStatus(ghostNets.getStatus());
		ghNets.setSalvingPerson(ghostNets.getSalvingPerson());
		em.persist(ghNets);		
		return ghNets;
	}


	@Override
	public void reportGhostNet(Long id, String status) {
		GhostNets ghostNets = getOne(id); 
		if(ghostNets == null) throw new RuntimeException("Data not found")  ; 
		em.persist(ghostNets);
		
	}


	@Override
	public void deleteOne(Long id) {
		GhostNets ghost = getOne(id) ; 
		em.remove(ghost);
		
	}


	@Override
	public List<GhostNets> getBySalvingPerson(SalvagingPerson salvingPerson) {
		TypedQuery<GhostNets> query = em.createQuery("SELECT g FROM GhostNets g WHERE g.salvingPerson = :salvingPerson", GhostNets.class);
        query.setParameter("salvingPerson", salvingPerson);

        return query.getResultList();
	}

	@Override
	public int getNumberOfReportedGN() {
	    TypedQuery<Long> query = em.createQuery("SELECT COUNT(g) FROM GhostNets g WHERE g.status = :status", Long.class);
	    query.setParameter("status", "Reported");
	    return query.getSingleResult().intValue();
	}

	@Override
	public int getNumberOfSignedUpGN() {
	    TypedQuery<Long> query = em.createQuery("SELECT COUNT(g) FROM GhostNets g WHERE g.status = :status", Long.class);
	    query.setParameter("status", "Looking");
	    return query.getSingleResult().intValue();
	}

	@Override
	public int getNumberOfRetrievedGN() {
	    TypedQuery<Long> query = em.createQuery("SELECT COUNT(g) FROM GhostNets g WHERE g.status = :status", Long.class);
	    query.setParameter("status", "Retrieved");
	    return query.getSingleResult().intValue();
	}


	@Override
	public GhostNets SignupFor(SalvagingPerson salvagingPerson,  GhostNets ghostNet) {
		GhostNets ghostNets = getOne(ghostNet.getId()) ; 
		ghostNets.setStatus("Looking");
		ghostNets.setSalvingPerson(salvagingPerson);
		em.persist(ghostNets);
		return ghostNets ; 
	}


	@Override
	public List<GhostNets> getRetrievedList(SalvagingPerson salvagingPerson) {
		TypedQuery<GhostNets> query = em.createQuery("SELECT g FROM GhostNets g WHERE g.status = :status and g.salvingPerson = :salvingPerson", GhostNets.class);
        query.setParameter("status", "Retrieved");
        query.setParameter("salvingPerson", salvagingPerson);
        return query.getResultList();
	}


	@Override
	public List<GhostNets> getReportedList() {
		TypedQuery<GhostNets> query = em.createQuery("SELECT g FROM GhostNets g WHERE g.status = :status", GhostNets.class);
        query.setParameter("status", "Reported");
        return query.getResultList();
	}


	@Override
	public List<GhostNets> getLookingForList(SalvagingPerson salvagingPerson) {
		TypedQuery<GhostNets> query = em.createQuery("SELECT g FROM GhostNets g WHERE g.status = :status and g.salvingPerson = :salvingPerson", GhostNets.class);
        query.setParameter("status", "Looking");
        query.setParameter("salvingPerson", salvagingPerson);
        return query.getResultList();
	}


	@Override
	public void markAR(GhostNets ghostNet) {
		GhostNets ghostNets = getOne(ghostNet.getId()) ; 
		ghostNets.setStatus("Retrieved");
		em.persist(ghostNets);
	}

	

}
