/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raisemeup.view.decorator;

import javax.swing.ImageIcon;

/**
 *
 * @author Andras
 */
public class Slot implements Icon{
    
    private ImageIcon icon;
    
    private int xloc, yloc;
    private int xsize, ysize;

    public Slot(Slot slot) {
        this.icon = slot.getIcon();
        xloc=0;
        yloc=0;
        xsize=0;
        ysize=0;
    }
    
    public Slot(ImageIcon icon) {
        this.icon = icon;
        xloc=0;
        yloc=0;
        xsize=0;
        ysize=0;
    }

    @Override
    public ImageIcon get() {
        return getIcon();
    }

    /**
     * @return the icon
     */
    public ImageIcon getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    /**
     * @return the xloc
     */
    public int getXloc() {
        return xloc;
    }

    /**
     * @param xloc the xloc to set
     */
    public void setXloc(int xloc) {
        this.xloc = xloc;
    }

    /**
     * @return the yloc
     */
    public int getYloc() {
        return yloc;
    }

    /**
     * @param yloc the yloc to set
     */
    public void setYloc(int yloc) {
        this.yloc = yloc;
    }

    /**
     * @return the xsize
     */
    public int getXsize() {
        return xsize;
    }

    /**
     * @param xsize the xsize to set
     */
    public void setXsize(int xsize) {
        this.xsize = xsize;
    }

    /**
     * @return the ysize
     */
    public int getYsize() {
        return ysize;
    }

    /**
     * @param ysize the ysize to set
     */
    public void setYsize(int ysize) {
        this.ysize = ysize;
    }
    
}
