/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raisemeup.model;

import raisemeup.model.beans.Food;
import raisemeup.model.beans.Upgrade;

/**
 *
 * @author Andras
 */
public class ItemVisitor {
    public int visit(Food food) {
        return food.getPrice();
    }
    
    public int visit(Upgrade upgrade) {
        return upgrade.getPrice();
    }
}
