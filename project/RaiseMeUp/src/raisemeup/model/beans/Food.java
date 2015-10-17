/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raisemeup.model.beans;

/**
 *
 * @author lekogabor
 */
public class Food extends Item{
    
    private int valuedog;
    private int valuecat;
    private int valuefish;
    private int valuepenguin;
    private String image;

    /**
     * @return the valuedog
     */
    public int getValuedog() {
        return valuedog;
    }

    /**
     * @param valuedog the valuedog to set
     */
    public void setValuedog(int valuedog) {
        this.valuedog = valuedog;
    }

    /**
     * @return the valuecat
     */
    public int getValuecat() {
        return valuecat;
    }

    /**
     * @param valuecat the valuecat to set
     */
    public void setValuecat(int valuecat) {
        this.valuecat = valuecat;
    }

    /**
     * @return the valuefish
     */
    public int getValuefish() {
        return valuefish;
    }

    /**
     * @param valuefish the valuefish to set
     */
    public void setValuefish(int valuefish) {
        this.valuefish = valuefish;
    }

    /**
     * @return the valuepenguin
     */
    public int getValuepenguin() {
        return valuepenguin;
    }

    /**
     * @param valuepenguin the valuepenguin to set
     */
    public void setValuepenguin(int valuepenguin) {
        this.valuepenguin = valuepenguin;
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
    
    
    
}
