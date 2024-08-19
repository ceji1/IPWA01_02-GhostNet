package com.dao;

import java.util.List;

import com.entities.GhostNets;
import com.entities.SalvagingPerson;

public interface IGhostNets {
	
	public List<GhostNets> getAll() ; 
	public int getNumberOfReportedGN() ; 
	public int getNumberOfSignedUpGN() ; 
	public int getNumberOfRetrievedGN() ; 
	public GhostNets getOne(Long id) ; 
	public List<GhostNets> getBySalvingPerson(SalvagingPerson salving) ; 
	public void addOne(GhostNets ghostNets) ; 
	public GhostNets updateOne(Long id, GhostNets ghostNets) ; 
	public void reportGhostNet(Long id, String status) ;
	public void deleteOne(Long id); 
	public GhostNets SignupFor(SalvagingPerson salvagingPerson, GhostNets ghostNet);
	public List<GhostNets> getRetrievedList(SalvagingPerson salvagingPerson); 
	public List<GhostNets> getReportedList(); 
	public List<GhostNets> getLookingForList(SalvagingPerson salvagingPerson);
	public void markAR(GhostNets ghostNet); 

	


}
