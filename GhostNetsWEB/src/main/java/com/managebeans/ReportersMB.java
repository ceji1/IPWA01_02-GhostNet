package com.managebeans;

import java.io.Serializable;

import com.dao.IGhostNets;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("repMB")
@SessionScoped
public class ReportersMB implements  Serializable {
	
		@EJB
		private IGhostNets metier ; 
		
		private String reported ; 
		

		public String getReported() {
			if(GhostNetsMB.reported) {
				GhostNetsMB.reported = false ; 
				this.reported = "Ghost net Reported Successfully" ;  
			}
		
			return reported;
		}
		
		public void setReported(String reported) {
			this.reported = reported;
		}

		
		
		public String back() {
			System.out.println("Back button clicked ");
			return "back" ; 
		}

}
