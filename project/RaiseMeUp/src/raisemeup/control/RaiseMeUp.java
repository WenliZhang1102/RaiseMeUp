
package raisemeup.control;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import raisemeup.model.DAO;
import raisemeup.model.beans.Pet;
import raisemeup.model.beans.PetBuilder;
import raisemeup.model.beans.User;
import raisemeup.model.beans.UserBuilder;
import raisemeup.view.ErrorMessage;
import raisemeup.view.Login;
import raisemeup.view.PetChooser;
import raisemeup.view.PetCreator;
import raisemeup.view.PetWindow;
import raisemeup.view.Register;

/**
 *
 * @author Kicsi Andras
 */
public class RaiseMeUp {
    
    private static Login login;
    private static Register register;
    private static PetWindow petWindow;
    private static DAO dao;
    private static ErrorMessage errorMessage;
    private static PetChooser petChooser;
    private static PetCreator petCreator;
    
    
    private static User currentUser;
    
    public static void init() {
        if(getLogin()==null) setLogin(new Login());
        //if(getRegister()==null) setRegister(new Register());
        //if(getPetWindow()==null) setPetWindow(new PetWindow());
        //if(getErrorMessage()==null) setErrorMessage(new ErrorMessage(""));
        
        if(getDao()==null) try {
            setDao(new DAO());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Could not connect to Database!", ex);
        }
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        init();
        getLogin().setVisible(true);
    }

    /**
     * @return the login
     */
    public static Login getLogin() {
        return login;
    }

    /**
     * @param aLogin the login to set
     */
    public static void setLogin(Login aLogin) {
        login = aLogin;
    }

    /**
     * @return the register
     */
    public static Register getRegister() {
        return register;
    }

    /**
     * @param aRegister the register to set
     */
    public static void setRegister(Register aRegister) {
        register = aRegister;
    }
    
    public static BufferedImage resizeImage(BufferedImage originalImage, int type, int maxSize)
    {
        if(originalImage.getWidth()>maxSize || originalImage.getHeight()>maxSize) {
            double scale;
            BufferedImage resizedImage;
            if(originalImage.getWidth()>originalImage.getHeight()) {
                scale=(double)originalImage.getHeight()/originalImage.getWidth();
                resizedImage = new BufferedImage(maxSize, (int)(scale*maxSize), type);
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(originalImage, 0, 0, maxSize, (int)(scale*maxSize), null);
                g.dispose();
            }
            else {
                scale=(double)originalImage.getWidth()/originalImage.getHeight();
                resizedImage = new BufferedImage((int)(scale*maxSize), maxSize, type);
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(originalImage, 0, 0, (int)(scale*maxSize), maxSize, null);
                g.dispose();
            }
            return resizedImage;
        }
        else return originalImage;
    }

    /**
     * @return the petWindow
     */
    public static PetWindow getPetWindow() {
        return petWindow;
    }

    /**
     * @param aPetWindow the petWindow to set
     */
    public static void setPetWindow(PetWindow aPetWindow) {
        petWindow = aPetWindow;
    }

    /**
     * @return the dao
     */
    public static DAO getDao() {
        return dao;
    }

    /**
     * @param aDao the dao to set
     */
    public static void setDao(DAO aDao) {
        dao = aDao;
    }
    
    public static boolean newUser(int id, String email, String username, String password) {
        UserBuilder userbuilder = new UserBuilder();
        userbuilder.setUsername(username);
        userbuilder.setId(id);
        userbuilder.setEmail(email);
        userbuilder.setPassword(password);
        try {
            dao.addUser(userbuilder.createUser());
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Could not add user to database!", ex);
            return false;
        }
        return true;
    }
    
    public static boolean doesUserExist(String username) {
        UserBuilder ub = new UserBuilder().setUsername(username);
        try {
            return dao.checkIfUserExists(ub.createUser());
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot check if user already exists!", ex);
        }
        return true;
    }
    
    public static void login(String username, String password) {
        boolean loggedin=false;
        Map<Integer,User> users = new HashMap<Integer, User>();
        try {
            users = dao.getUser();
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot get user data from database!", ex);
        }
        for(Map.Entry<Integer,User> user : users.entrySet()) {
            if(user.getValue().getUsername().equals(username) && user.getValue().getPassword().equals(password)) {
                setCurrentUser(user.getValue());
                loggedin=true;
                petCreator = new PetCreator();
                login.setVisible(false);
                petCreator.setVisible(true);
                /*petWindow = new PetWindow();
                login.setVisible(false);
                petWindow.setVisible(true);*/
            }
        }
        
        if(!loggedin) {
            RaiseMeUp.setErrorMessage(new ErrorMessage("Failed to log you in. Please try again."));
            RaiseMeUp.getErrorMessage().setVisible(true);
        }
        
    }
    
    public static void bookMyNewAnimal(String animalname, String type, String variant, String imagestring) {
        PetBuilder pb = new PetBuilder().setName(animalname).setType(type).setVariant(variant).setOwner(currentUser.getId()).setImage(imagestring);
        Pet newPet = pb.createPet();
        try {
            dao.addPet(newPet);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot add pet to the database!", ex);
        }
    }

    /**
     * @return the errorMessage
     */
    public static ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param aErrorMessage the errorMessage to set
     */
    public static void setErrorMessage(ErrorMessage aErrorMessage) {
        errorMessage = aErrorMessage;
    }

    /**
     * @return the currentUser
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * @param aCurrentUser the currentUser to set
     */
    public static void setCurrentUser(User aCurrentUser) {
        currentUser = aCurrentUser;
    }
    
    
    
    
}
