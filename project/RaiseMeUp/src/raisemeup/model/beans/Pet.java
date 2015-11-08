/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raisemeup.model.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lekogabor
 */
public class Pet {
    private int petid;
    private int owner;
    private String name;
    private String type;
    private String variant;
    private int hunger;
    private int energy;
    private int fun;
    private int hygiene;
    private int age;
    private String image;
    private Map<Item, Integer> owneditems = new HashMap<Item, Integer>();
    private Map<Job, Integer> ownedjobs = new HashMap<Job, Integer>();
    
    private int money;

    public Pet(int owner, String name, String type, String variant, String image) {
        this.owner = owner;
        this.name = name;
        this.type = type;
        this.variant = variant;
        this.image = image;
        
        petid=0;
        hunger = 100;
        energy = 100;
        fun = 100;
        hygiene = 100;
        money=100;
        age=0;
    }
    
    public Pet() {
        
    }

    
    

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the hunger
     */
    public int getHunger() {
        return hunger;
    }

    /**
     * @param hunger the hunger to set
     */
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    /**
     * @return the energy
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * @param energy the energy to set
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * @return the fun
     */
    public int getFun() {
        return fun;
    }

    /**
     * @param fun the fun to set
     */
    public void setFun(int fun) {
        this.fun = fun;
    }

    /**
     * @return the hygiene
     */
    public int getHygiene() {
        return hygiene;
    }

    /**
     * @param hygiene the hygiene to set
     */
    public void setHygiene(int hygiene) {
        this.hygiene = hygiene;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }


    /**
     * @return the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the variant
     */
    public String getVariant() {
        return variant;
    }

    /**
     * @param variant the variant to set
     */
    public void setVariant(String variant) {
        this.variant = variant;
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
     * @return the owner
     */
    public int getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(int owner) {
        this.owner = owner;
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

    /**
     * @return the owneditems
     */
    public Map<Item, Integer> getOwneditems() {
        return owneditems;
    }

    /**
     * @param owneditems the owneditems to set
     */
    public void setOwneditems(Map<Item, Integer> owneditems) {
        this.owneditems = owneditems;
    }

    /**
     * @return the ownedjobs
     */
    public Map<Job, Integer> getOwnedjobs() {
        return ownedjobs;
    }

    /**
     * @param ownedjobs the ownedjobs to set
     */
    public void setOwnedjobs(Map<Job, Integer> ownedjobs) {
        this.ownedjobs = ownedjobs;
    }

  
    
    
}
