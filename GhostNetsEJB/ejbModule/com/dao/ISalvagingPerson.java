package com.dao;

import com.entities.GhostNets;
import com.entities.SalvagingPerson;

public interface ISalvagingPerson {
	
	public SalvagingPerson addOne(SalvagingPerson salvingPerson) ; 
	public SalvagingPerson login(String email, String password) ; 
	public GhostNets getSalvingPersonByEmailAndPassword(String email, String password) ; 
	

}
