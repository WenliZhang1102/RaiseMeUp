package raisemeup.model.beans;

/**
 * * * @author lekogabor
 */
public abstract class Item {

    private int id;
    private String name;
    private int price;
    private String image;

    /**
     * * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
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
