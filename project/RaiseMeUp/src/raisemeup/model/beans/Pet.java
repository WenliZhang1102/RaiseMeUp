/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raisemeup.model.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kicsi Andras
 */
public class Pet {
    private User owner;
    private String name;
    private int hunger;
    private int energy;
    private int fun;
    private int hygiene;
    private int age;
    private List<Item> owneditems = new ArrayList<Item>();
    private int money;

    /**
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(User owner) {
        this.owner = owner;
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
     * @return the owneditems
     */
    public List<Item> getOwneditems() {
        return owneditems;
    }

    /**
     * @param owneditems the owneditems to set
     */
    public void setOwneditems(List<Item> owneditems) {
        this.owneditems = owneditems;
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
    
    
}
