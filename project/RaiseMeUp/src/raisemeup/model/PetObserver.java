/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raisemeup.model;

import raisemeup.model.beans.Pet;
import raisemeup.control.RaiseMeUp;

/**
 *
 * @author Kicsi Andras
 */
public class PetObserver {
    
    private int prevHunger, prevEnergy, prevFun, prevHygiene, prevAge, prevEmotion;
    
    public PetObserver() {
        prevEnergy = RaiseMeUp.getCurrentPet().getEnergy();
        prevHunger = RaiseMeUp.getCurrentPet().getHunger();
        prevFun = RaiseMeUp.getCurrentPet().getFun();
        prevHygiene = RaiseMeUp.getCurrentPet().getHygiene();
        prevAge = RaiseMeUp.getCurrentPet().getAge();
        prevEmotion = RaiseMeUp.getCurrentPet().getEmotion();
    }
    
    public void update() {
        boolean change=false;
        boolean nowDeparting=prevEnergy!=0 || prevFun!=0 || prevHunger!=0 || prevHygiene!=0;
        
        if(prevEnergy!=RaiseMeUp.getCurrentPet().getEnergy()) {
            prevEnergy = RaiseMeUp.getCurrentPet().getEnergy();
            RaiseMeUp.getPetWindow().refreshPBEnergy();
            change=true;
        }
        if(prevHunger!=RaiseMeUp.getCurrentPet().getHunger()) {
            prevHunger = RaiseMeUp.getCurrentPet().getHunger();
            RaiseMeUp.getPetWindow().refreshPBHunger();
            change=true;
        }
        if(prevFun!=RaiseMeUp.getCurrentPet().getFun()) {
            prevFun = RaiseMeUp.getCurrentPet().getFun();
            RaiseMeUp.getPetWindow().refreshPBFun();
            change=true;
        }
        if(prevHygiene!=RaiseMeUp.getCurrentPet().getHygiene()) {
            prevHygiene = RaiseMeUp.getCurrentPet().getHygiene();
            RaiseMeUp.getPetWindow().refreshPBHygiene();
            change=true;
        }
        if(prevAge!=RaiseMeUp.getCurrentPet().getAge()) {
            prevAge = RaiseMeUp.getCurrentPet().getAge();
            change=true;
        }
        
        if(prevEmotion!=RaiseMeUp.getCurrentPet().getEmotion()) {
            prevEmotion=RaiseMeUp.getCurrentPet().getEmotion();
            RaiseMeUp.getPetWindow().refreshEmotion();
            change=true;
        }
        
        //save changes to database
        if(change) {
            RaiseMeUp.updatePet(RaiseMeUp.getCurrentPet());
        }
        
        if(RaiseMeUp.getCurrentPet().getEnergy()==0 && RaiseMeUp.getCurrentPet().getFun()==0 && RaiseMeUp.getCurrentPet().getHunger()==0 && RaiseMeUp.getCurrentPet().getHygiene()==0 && nowDeparting) {
            RaiseMeUp.petLeaves();
        }
    }
}
