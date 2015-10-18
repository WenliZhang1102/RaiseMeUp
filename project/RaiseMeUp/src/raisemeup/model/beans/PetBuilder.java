/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raisemeup.model.beans;


public class PetBuilder {
    private int owner=0;
    private String name="";
    private String type="";
    private String variant="";
    private String image="";

    public PetBuilder() {
    }

    public PetBuilder setOwner(int owner) {
        this.owner = owner;
        return this;
    }

    public PetBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PetBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public PetBuilder setVariant(String variant) {
        this.variant = variant;
        return this;
    }

    public PetBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public Pet createPet() {
        return new Pet(owner, name, type, variant, image);
    }
    
}
