package com.managebeans;

import java.io.IOException;
import java.io.Serializable;

import com.dao.ISalvagingPerson;
import com.entities.SalvagingPerson;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

@Named("spMB")
@ViewScoped
public class SalvagingPersonMB implements Serializable {
	
	@EJB
	private ISalvagingPerson metier ; 
	
	private String successSignup ; 
	private String loginError ; 
	private String wrongLogin ; 
	
	private SalvagingPerson salvagingPerson = new SalvagingPerson() ;

	public SalvagingPerson getSalvagingPerson() {
		return salvagingPerson;
	}

	public void setSalvagingPerson(SalvagingPerson salvagingPerson) {
		this.salvagingPerson = salvagingPerson;
	} 
	
	
	public String signup() {
		SalvagingPerson sp = metier.addOne(salvagingPerson);
		if(sp != null) {
			successSignup = "Salavaging created successfully "; 
			return "signup";
		}else {
			successSignup = "Salavaging not cretated, try again "; 
			return "signup" ; 
		}
		
	}
	
	public String login() {
		System.out.println(salvagingPerson.getEmail());
		System.out.println(salvagingPerson.getPassword());
		salvagingPerson = metier.login(salvagingPerson.getEmail(), salvagingPerson.getPassword()) ; 
		if( salvagingPerson != null) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true) ; 
	    	session.setAttribute("user", salvagingPerson);
	    	System.out.println("success");
			return "dashboard" ; 

		}else {
			loginError = "Check password / email and try again" ;
			return "login" ; 
		}
		
	}
	
	public void logout() {
		 FacesContext facesContext = FacesContext.getCurrentInstance();
		    ExternalContext externalContext = facesContext.getExternalContext();
		    HttpSession session = (HttpSession) externalContext.getSession(false);
		    if (session != null) {
		        session.invalidate();

		    }
		    try {
		        externalContext.redirect("login.xhtml"); 
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}
	
	
	public void checkLoggedInUser() {
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    HttpSession session = (HttpSession) externalContext.getSession(false);
	    
	    if ((session == null || session.getAttribute("user") == null)) {
	        try {
	        	session.invalidate() ; 
	            externalContext.redirect("login.xhtml");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	
	
	
	public String navigateToLogin() {
		return "login"  ; 
	}
	
	public String navigateToSignup() {
		return "signup"  ; 
	}
	
	public String navigateToLanding() {
		return "landing" ; 
	}

	public String getSuccessSignup() {
		return successSignup;
	}

	public void setSuccessSignup(String successSignup) {
		this.successSignup = successSignup;
	}

	public String getWrongLogin() {
		return wrongLogin;
	}

	public void setWrongLogin(String wrongLogin) {
		this.wrongLogin = wrongLogin;
	}

	public String getLoginError() {
		return loginError;
	}

	public void setLoginError(String loginError) {
		this.loginError = loginError;
	}
	
	

}
