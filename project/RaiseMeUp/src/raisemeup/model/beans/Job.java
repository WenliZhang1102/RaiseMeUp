/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raisemeup.model.beans;

/**
 *
 * @author Kicsi Andras
 */
public class Job {
    private int id;
    private int impactenergy;
    private int impacthunger;
    private int impacthygiene;
    private int impactfun;
    private String title;
    private String image;
    private String client;
    private String message;
    private int petid;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the impactenergy
     */
    public int getImpactenergy() {
        return impactenergy;
    }

    /**
     * @param impactenergy the impactenergy to set
     */
    public void setImpactenergy(int impactenergy) {
        this.impactenergy = impactenergy;
    }

    /**
     * @return the impacthunger
     */
    public int getImpacthunger() {
        return impacthunger;
    }

    /**
     * @param impacthunger the impacthunger to set
     */
    public void setImpacthunger(int impacthunger) {
        this.impacthunger = impacthunger;
    }

    /**
     * @return the impacthygiene
     */
    public int getImpacthygiene() {
        return impacthygiene;
    }

    /**
     * @param impacthygiene the impacthygiene to set
     */
    public void setImpacthygiene(int impacthygiene) {
        this.impacthygiene = impacthygiene;
    }

    /**
     * @return the impactfun
     */
    public int getImpactfun() {
        return impactfun;
    }

    /**
     * @param impactfun the impactfun to set
     */
    public void setImpactfun(int impactfun) {
        this.impactfun = impactfun;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the client
     */
    public String getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(String client) {
        this.client = client;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the petid
     */
    public int getPetid() {
        return petid;
    }

    /**
     * @param petid the petid to set
     */
    public void setPetid(int petid) {
        this.petid = petid;
    }
    
    
}
