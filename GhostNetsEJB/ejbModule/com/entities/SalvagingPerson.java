package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Salvings")
public class SalvagingPerson implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ; 
	
	private String firstName ; 
	
	private String lastName ; 
	
	@Column(unique = true)
	private String email ; 
	
	private String password ;
	
	@OneToMany(mappedBy = "salvingPerson")
    private List<GhostNets> ghostNets = new ArrayList<>();
	



	public SalvagingPerson() {
	}
	
	

	public SalvagingPerson(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	} 
	
	public List<GhostNets> getSalvingPersons() {
		return ghostNets;
	}



	public void setSalvingPersons(List<GhostNets> ghostNets) {
		this.ghostNets = ghostNets;
	}

	
}
