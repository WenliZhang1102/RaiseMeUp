/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raisemeup.view.decorator;

import javax.swing.ImageIcon;

/**
 *
 * @author Kicsi Andras
 */
public class RightUpgradeSlot extends Slot{

    public RightUpgradeSlot(ImageIcon icon) {
        super(icon);
        this.setXloc(360);
        this.setYloc(275);
        this.setXsize(120);
        this.setYsize(153);
    }
    
    public RightUpgradeSlot(Slot slot) {
        super(slot);
        this.setXloc(360);
        this.setYloc(275);
        this.setXsize(120);
        this.setYsize(153);
    }
    
}
