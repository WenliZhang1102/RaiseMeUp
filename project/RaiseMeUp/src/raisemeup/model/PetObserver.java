/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raisemeup.model;

import raisemeup.model.beans.Pet;
import raisemeup.control.RaiseMeUp;
import raisemeup.view.JobDone;
import raisemeup.view.PetWindow;

/**
 *
 * @author Kicsi Andras
 */
public class PetObserver {
    
    private int prevHunger, prevEnergy, prevFun, prevHygiene, prevAge, prevEmotion;
    private boolean prevonjob;
    private int prevjobprogress;
    private boolean left=false;
    
    public PetObserver() {
        prevEnergy = RaiseMeUp.getCurrentPet().getEnergy();
        prevHunger = RaiseMeUp.getCurrentPet().getHunger();
        prevFun = RaiseMeUp.getCurrentPet().getFun();
        prevHygiene = RaiseMeUp.getCurrentPet().getHygiene();
        prevAge = RaiseMeUp.getCurrentPet().getAge();
        prevEmotion = RaiseMeUp.getCurrentPet().getEmotion();
        prevonjob = RaiseMeUp.isOnJob();
        prevjobprogress = RaiseMeUp.getJobprogress();
    }
    
    public void update() {
        boolean change=false;
        boolean jobchange=false;
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
        
        //job
        if(!prevonjob && RaiseMeUp.isOnJob()) {
            RaiseMeUp.setJobprogress(0);
            prevjobprogress=0;
        }
        
        if(prevjobprogress!=RaiseMeUp.getJobprogress()) {
            prevjobprogress=RaiseMeUp.getJobprogress();
            RaiseMeUp.getPetWindow().refreshPBJob();
            jobchange=true;
        }
        
        if(prevonjob != RaiseMeUp.isOnJob()) prevonjob=RaiseMeUp.isOnJob();
        
        
        
        //save changes to database
        if(change) {
            RaiseMeUp.updatePet(RaiseMeUp.getCurrentPet());
        }
        
        if(jobchange) {
            if(RaiseMeUp.getJobprogress()>=RaiseMeUp.getCurrentJob().getLength()) {
                RaiseMeUp.getJobDone().setVisible(true);
                RaiseMeUp.getCurrentPet().setEnergy(RaiseMeUp.getCurrentPet().getEnergy() + RaiseMeUp.getCurrentJob().getImpactenergy());
                RaiseMeUp.getCurrentPet().setHunger(RaiseMeUp.getCurrentPet().getHunger()+ RaiseMeUp.getCurrentJob().getImpacthunger());
                RaiseMeUp.getCurrentPet().setFun(RaiseMeUp.getCurrentPet().getFun() + RaiseMeUp.getCurrentJob().getImpactfun());
                RaiseMeUp.getCurrentPet().setHygiene(RaiseMeUp.getCurrentPet().getHygiene()+ RaiseMeUp.getCurrentJob().getImpacthygiene());
                RaiseMeUp.getCurrentPet().setMoney(RaiseMeUp.getCurrentPet().getMoney()+ RaiseMeUp.getCurrentJob().getReward());
                if(RaiseMeUp.getCurrentPet().getEnergy()<0) RaiseMeUp.getCurrentPet().setEnergy(0);
                if(RaiseMeUp.getCurrentPet().getHunger()<0) RaiseMeUp.getCurrentPet().setHunger(0);
                if(RaiseMeUp.getCurrentPet().getFun()<0) RaiseMeUp.getCurrentPet().setFun(0);
                if(RaiseMeUp.getCurrentPet().getHygiene()<0) RaiseMeUp.getCurrentPet().setHygiene(0);
                RaiseMeUp.getPetWindow().quitJob();
                
                                
                RaiseMeUp.removeJobOwned(RaiseMeUp.getCurrentPet(), RaiseMeUp.getCurrentJob());

                
                RaiseMeUp.setJobprogress(0);
                update();
            }
            else {
                RaiseMeUp.updateJobOwned(RaiseMeUp.getCurrentPet(), RaiseMeUp.getCurrentJob(), RaiseMeUp.getJobprogress());
            }
        }
        
        
        if(RaiseMeUp.getCurrentPet().getEnergy()<=0 && RaiseMeUp.getCurrentPet().getFun()<=0 && RaiseMeUp.getCurrentPet().getHunger()<=0 && RaiseMeUp.getCurrentPet().getHygiene()<=0 && nowDeparting && !left) {
            RaiseMeUp.petLeaves();
            left=true;
        }
    }
}
