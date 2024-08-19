package com.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ghost_nets")
public class GhostNets implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ; 
	
	private String location ; 
	
	private double latitude;
	private double longitude;
	
	private String status = "Reported" ; 
	
	private double size ;
	
	private String reporterPhone ; 
	private String reporterFullName ; 
	
	@ManyToOne
	private SalvagingPerson salvingPerson; 
	
	


	public GhostNets() {}
	

	  public GhostNets(double latitude, double longitude, String status, double size, SalvagingPerson salvingPerson) {
	        this.latitude = latitude;
	        this.longitude = longitude;
	        this.status = status;
	        this.size = size;
	        this.salvingPerson = salvingPerson;
	    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	  public double getLatitude() {
	        return latitude;
	    }

	    public void setLatitude(double latitude) {
	        this.latitude = latitude;
	    }

	    public double getLongitude() {
	        return longitude;
	    }

	    public void setLongitude(double longitude) {
	        this.longitude = longitude;
	    }

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	} 
	
	public SalvagingPerson getSalvingPerson() {
		return salvingPerson;
	}


	public void setSalvingPerson(SalvagingPerson salvingPerson) {
		this.salvingPerson = salvingPerson;
	}




	public String getReporterPhone() {
		return reporterPhone;
	}


	public void setReporterPhone(String reporterPhone) {
		this.reporterPhone = reporterPhone;
	}


	public String getReporterFullName() {
		return reporterFullName;
	}


	public void setReporterFullName(String reporterFullName) {
		this.reporterFullName = reporterFullName;
	}
	
	
	
	
	

}
