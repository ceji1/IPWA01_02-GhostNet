package com.managebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.dao.IGhostNets;
import com.dao.ILocation;
import com.entities.GhostNets;
import com.entities.Location;
import com.entities.SalvagingPerson;

import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("ghostNetMB")
@ViewScoped
public class GhostNetsMB implements Serializable {

    @EJB
    IGhostNets metier;
    @EJB
    ILocation locationMetier;

    private GhostNets ghostNet = new GhostNets();
    private Location location;
    private GhostNets selectedGN = new GhostNets();
    private List<GhostNets> ghostList;
    private List<GhostNets> retrievedLists;
    private List<GhostNets> lookingForList;
    private List<GhostNets> reportedList;
    public static boolean reported = false;
    private int nbReported;
    private int nbRetrieved;
    private int nbLooking;

    public GhostNetsMB() {
        reported = false;
    }

    public void initData() {
        System.out.println("initialize data");
        ghostList = metier.getAll();
    }

    public void add(GhostNets ghostNets) {
        metier.addOne(ghostNets);
    }

    public void update(Long id, GhostNets ghostNets) {
        metier.updateOne(id, ghostNets);
    }

    public void delete(Long id) {
        metier.deleteOne(id);
    }

    public GhostNets getGhostNet() {
        return ghostNet;
    }

    public void setGhostNet(GhostNets ghostNet) {
        this.ghostNet = ghostNet;
    }

    public List<GhostNets> getGhostList() {
        ghostList = metier.getAll();
        return ghostList;
    }

    public void setGhostList(List<GhostNets> ghostList) {
        this.ghostList = ghostList;
    }

    public String reportAnonymously() {
        return "landing?faces-redirect=true";
    }

    public String fillAreport() {
        return "report?faces-redirect=true";
    }

    public String addReport() {
        ghostNet.setLatitude(location.getLatitude());
        ghostNet.setLongitude(location.getLongitude());
        metier.addOne(ghostNet);
        reported = true;
        setGhostNet(null);
        return "reported?reported=report avec success";
    }

    public int getNbReported() {
        nbReported = metier.getNumberOfReportedGN();
        return nbReported;
    }

    public int getNbLooking() {
        nbLooking = metier.getNumberOfSignedUpGN();
        return nbLooking;
    }

    public int getNbRetrieved() {
        nbReported = metier.getNumberOfRetrievedGN();
        return nbRetrieved;
    }

    public String signupFor() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        SalvagingPerson salvagingPerson = (SalvagingPerson) externalContext.getSessionMap().get("user");
        metier.SignupFor(salvagingPerson, this.selectedGN);
        return "dashboard";
    }

    public String markAR() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        metier.markAR(this.selectedGN);
        return "lookingFor";
    }

    public void delete() {
        metier.deleteOne(ghostNet.getId());
    }

    public void initForm() {
        long id;
        id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));

        GhostNets ghostNet = new GhostNets();
        ghostNet = metier.getOne(id);

        if (ghostNet != null)
            this.selectedGN = ghostNet;
    }

    public GhostNets getSelectedGN() {
        return selectedGN;
    }

    public void setSelectedGN(GhostNets selectedGN) {
        this.selectedGN = selectedGN;
    }

    public List<GhostNets> getRetrievedLists() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        SalvagingPerson salvagingPerson = (SalvagingPerson) externalContext.getSessionMap().get("user");
        retrievedLists = metier.getRetrievedList(salvagingPerson);
        return retrievedLists;
    }

    public List<GhostNets> getLookingForList() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        SalvagingPerson salvagingPerson = (SalvagingPerson) externalContext.getSessionMap().get("user");
        lookingForList = metier.getLookingForList(salvagingPerson);
        return lookingForList;
    }

    public List<GhostNets> getReportedList() {
        reportedList = metier.getReportedList();
        return reportedList;
    }

    // Autocomplete method for location
    public List<Location> completeLocation(String q) {
        List<Location> allLocations = locationMetier.getAll();
        List<Location> filteredLocations = new ArrayList<>();

        for (Location location : allLocations) {
            if (location.getName().toLowerCase().contains(q.toLowerCase())) {
                filteredLocations.add(location);
            }
        }
        return filteredLocations;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}   