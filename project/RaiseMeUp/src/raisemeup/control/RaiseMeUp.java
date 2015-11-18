
package raisemeup.control;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import raisemeup.model.DAO;
import raisemeup.model.beans.Food;
import raisemeup.model.beans.Job;
import raisemeup.model.beans.Pet;
import raisemeup.model.beans.PetBuilder;
import raisemeup.model.beans.Upgrade;
import raisemeup.model.beans.User;
import raisemeup.model.beans.UserBuilder;
import raisemeup.view.AdminFoods;
import raisemeup.view.AdminJobs;
import raisemeup.view.AdminPets;
import raisemeup.view.AdminPetsItems;
import raisemeup.view.AdminPetsJobs;
import raisemeup.view.AdminTiming;
import raisemeup.view.AdminUpgrades;
import raisemeup.view.AdminUsers;
import raisemeup.view.AdminWindow;
import raisemeup.view.ErrorMessage;
import raisemeup.view.JobDone;
import raisemeup.view.JobsWindow;
import raisemeup.view.Login;
import raisemeup.view.MarketWindow;
import raisemeup.view.PetChooser;
import raisemeup.view.PetCreator;
import raisemeup.view.PetWindow;
import raisemeup.view.Register;
import raisemeup.view.UserSettings;

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
    private static AdminUsers adminUsers;
    private static AdminWindow adminWindow;
    private static AdminPets adminPets;
    private static AdminPetsItems adminPetsItems;
    private static AdminFoods adminFoods;
    private static AdminUpgrades adminUpgrades;
    private static AdminJobs adminJobs;
    private static AdminPetsJobs adminPetsJobs;
    private static AdminTiming adminTiming;
    private static JobsWindow jobsWindow;
    private static JobDone jobDone;
    private static MarketWindow marketWindow;
    private static UserSettings userSettings;
    
    private static User currentUser;
    private static Pet currentPet;

    /**
     * @return the currentJob
     */
    public static Job getCurrentJob() {
        return currentJob;
    }

    /**
     * @param aCurrentJob the currentJob to set
     */
    public static void setCurrentJob(Job aCurrentJob) {
        currentJob = aCurrentJob;
    }
    
    private static boolean onJob=false;
    private static Job currentJob;
    private static int jobprogress;
	
    private static float timeModifier=(float)0.06;// ha 1, akkor  1 perc 1 ora 0.05 *600
    
    public static void init() {
        if(getLogin()==null) setLogin(new Login());
        if(getJobDone()==null) setJobDone(new JobDone());
        
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
    
    public static BufferedImage resizeImage(BufferedImage originalImage, int type, int maxWidth, int maxHeight)
    {
        if(originalImage.getWidth()>maxWidth || originalImage.getHeight()>maxHeight) {
            double scale;
            BufferedImage resizedImage;
            if(originalImage.getWidth()>originalImage.getHeight()) {
                scale=(double)originalImage.getHeight()/originalImage.getWidth();
                resizedImage = new BufferedImage(maxWidth, (int)(scale*maxWidth), type);
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(originalImage, 0, 0, maxWidth, (int)(scale*maxWidth), null);
                g.dispose();
            }
            else {
                scale=(double)originalImage.getWidth()/originalImage.getHeight();
                resizedImage = new BufferedImage((int)(scale*maxHeight), maxHeight, type);
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(originalImage, 0, 0, (int)(scale*maxHeight), maxHeight, null);
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
    
    public static void bookMyNewAnimal(String animalname, String type, String variant, String imagestring) {
        PetBuilder pb = new PetBuilder().setName(animalname).setType(type).setVariant(variant).setOwner(currentUser.getId()).setImage(imagestring);
        Pet newPet = pb.createPet();
        try {
            dao.addPet(newPet);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot add pet to the database!", ex);
        }
        petWindow = new PetWindow(newPet);
        getPetCreator().setVisible(false);
        petWindow.setVisible(true);
    }
    
    public static boolean newFood (Food f){
        
        try {
            dao.addFood(f);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Could not add food to database!", ex);
            return false;
        }
        return true;
    }
    
    public static boolean newUpgrade (Upgrade u){
        
        try {
            dao.addUpgrade(u);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Could not add upgrade to database!", ex);
            return false;
        }
        return true;
    }   
    
    public static boolean newJob (Job j){
        
        try {
            dao.addJob(j);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Could not add job to database!", ex);
            return false;
        }
        return true;
    }
    
    public static boolean newFoodOwned (Pet p, Food f, int piece){
        
        try {
            dao.addFoodOwned(p,f, piece);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Could not add owned food to database!", ex);
            return false;
        }
        return true;
    }
    
    public static boolean newUpgradeOwned (Pet p, Upgrade u, int piece){
        
        try {
            dao.addUpgradeOwned(p,u, piece);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Could not add owned upgrade to database!", ex);
            return false;
        }
        return true;
    }
 
    public static boolean newJobOwned (Pet p, Job j, int timeworked){
        
        try {
            dao.addJobOwned(p, j, timeworked);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Could not add owned job to database!", ex);
            return false;
        }
        return true;
    }
    
    
    public static Map<Integer, User> listUsers() {
        Map<Integer,User> users = new HashMap<Integer, User>();
        try {
            users = dao.getUser();
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot get user data from database!", ex);
        }
        
        return users;
    }
    
    public static Map<Integer, Pet> listPets(){
        Map<Integer,Pet> pets = new HashMap<Integer, Pet>();
        try {
            pets = dao.getPet();
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot get pet data from database!", ex);
        }
        
        return pets;
    }
    
    public static Map<Integer, Food> listFoods(){
        Map<Integer,Food> foods = new HashMap<Integer, Food>();
        try {
            foods = dao.getFood();
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot get foods data from database!", ex);
        }
        
        return foods;
    }
    
    public static Map<Integer, Upgrade> listUpgrades(){
        Map<Integer,Upgrade> upgrades = new HashMap<Integer, Upgrade>();
        try {
            upgrades = dao.getUpgrade();
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot get upgrades data from database!", ex);
        }
        
        return upgrades;
    }
    
    public static Map<Integer, Job> listJobs(){
        Map<Integer,Job> jobs = new HashMap<Integer, Job>();
        try {
            jobs = dao.getJob();
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot get jobs data from database!", ex);
        }
        
        return jobs;
    }
    
    
    public static boolean removeUser(User u){
        
        try {
            dao.delUser(u);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot delete user from database!", ex);
        }
        
        return true;
    }
    
    public static boolean removePet(Pet p){
        
        try {
            dao.delPet(p);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot delete pet from database!", ex);
        }
        
        return true;
    }
    
    public static boolean removeFood(Food f){
        
        try {
            dao.delFood(f);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot delete food from database!", ex);
        }
        
        return true;
    }
    
    public static boolean removeUpgrade(Upgrade u){
        
        try {
            dao.delUpgrade(u);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot delete upgrade from database!", ex);
        }
        
        return true;
    }
    
    public static boolean removeJob(Job j){
        
        try {
            dao.delJob(j);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot delete job from database!", ex);
        }
        
        return true;
    }
    
    public static boolean removeFoodOwned(Pet p, Food f){
        
        try {
            dao.delFoodOwned(p, f);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot delete owned food from database!", ex);
        }
        
        return true;
    }
    
    public static boolean removeUpgradeOwned(Pet p, Upgrade u){
        
        try {
            dao.delUpgradeOwned(p, u);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot delete owned upgrade from database!", ex);
        }
        
        return true;
    }
    
    public static boolean removeJobOwned(Pet p, Job j){
        
        try {
            dao.delJobOwned(p, j);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot delete owned job from database!", ex);
        }
        
        return true;
    }
    
    
    public static boolean updateUser(User u){
        try {
            dao.updateUser(u);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot update user in database!", ex);
        }
        
        return true;
    }
    
    public static boolean updatePet(Pet p){
        try {
            dao.updatePet(p);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot update pet in database!", ex);
        }
        
        return true;
    }
    
    public static boolean updateFood(Food f){
        try {
            dao.updateFood(f);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot update food in database!", ex);
        }
        
        return true;
    }
    
    public static boolean updateUpgrade(Upgrade u){
        try {
            dao.updateUpgrade(u);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot update upgrade in database!", ex);
        }
        
        return true;
    }
    
    public static boolean updateJob(Job j){
        try {
            dao.updateJob(j);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot update job in database!", ex);
        }
        
        return true;
    }
    
    public static boolean updateFoodOwned(Pet p, Food f, int piece){
        try {
            dao.updateFoodOwned(p, f, piece);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot update owned food in database!", ex);
        }
        
        return true;
    }
    
    public static boolean updateUpgradeOwned(Pet p, Upgrade u, int piece){
        try {
            dao.updateUpgradeOwned(p, u, piece);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot update owned upgrade in database!", ex);
        }
        
        return true;
    }
     
    public static boolean updateJobOwned(Pet p, Job j, int timeworked){
        try {
            dao.updateJobOwned(p, j, timeworked);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot update owned job in database!", ex);
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
    
    public static boolean doesPetExist(String petname) {
        PetBuilder pb = new PetBuilder().setName(petname).setOwner(currentUser.getId());
        try {
            return dao.checkIfPetExists(pb.createPet());
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot check if pet already exists!", ex);
        }
        return true;
    }
    
    public static void login(String username, String password) {
        boolean loggedin=false;
        Map<Integer,User> users = new HashMap<Integer, User>();
        
        
        
        if ("admin".equals(username) && "admin".equals(password)){
            adminWindow = new AdminWindow();
            login.setVisible(false);
            adminWindow.setVisible(true);
            loggedin = true;
        } else {
        
            try {
                users = dao.getUser();
            } catch (SQLException ex) {
                Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, "Cannot get user data from database!", ex);
            }
            
            for(Map.Entry<Integer,User> user : users.entrySet()) {
                if(user.getValue().getUsername().equals(username) && user.getValue().getPassword().equals(password)) {
                    setCurrentUser(user.getValue());
                    loggedin=true;
                    setPetChooser(new PetChooser());
                    login.setVisible(false);
                    getPetChooser().setVisible(true);
                }
            }
        }
        if(!loggedin) {
            RaiseMeUp.setErrorMessage(new ErrorMessage("Failed to log you in. Please try again."));
            RaiseMeUp.getErrorMessage().setVisible(true);
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

    /**
     * @return the adminWindow
     */
    public static AdminWindow getAdminWindow() {
        return adminWindow;
    }

    /**
     * @param aAdminWindow the adminWindow to set
     */
    public static void setAdminWindow(AdminWindow aAdminWindow) {
        adminWindow = aAdminWindow;
    }

    /**
     * @return the adminUsers
     */
    public static AdminUsers getAdminUsers() {
        return adminUsers;
    }

    /**
     * @param aAdminUsers the adminUsers to set
     */
    public static void setAdminUsers(AdminUsers aAdminUsers) {
        adminUsers = aAdminUsers;
    }

    /**
     * @return the adminPets
     */
    public static AdminPets getAdminPets() {
        return adminPets;
    }

    /**
     * @param aAdminPets the adminPets to set
     */
    public static void setAdminPets(AdminPets aAdminPets) {
        adminPets = aAdminPets;
    }

    /**
     * @return the adminPetsItems
     */
    public static AdminPetsItems getAdminPetsItems() {
        return adminPetsItems;
    }

    /**
     * @param aAdminPetsItems the adminPetsItems to set
     */
    public static void setAdminPetsItems(AdminPetsItems aAdminPetsItems) {
        adminPetsItems = aAdminPetsItems;
    }

    /**
     * @return the adminFoods
     */
    public static AdminFoods getAdminFoods() {
        return adminFoods;
    }

    /**
     * @param aAdminFoods the adminFoods to set
     */
    public static void setAdminFoods(AdminFoods aAdminFoods) {
        adminFoods = aAdminFoods;
    }

    /**
     * @return the adminUpgrades
     */
    public static AdminUpgrades getAdminUpgrades() {
        return adminUpgrades;
    }

    /**
     * @param aAdminUpgrades the adminUpgrades to set
     */
    public static void setAdminUpgrades(AdminUpgrades aAdminUpgrades) {
        adminUpgrades = aAdminUpgrades;
    }

    /**
     * @return the adminJobs
     */
    public static AdminJobs getAdminJobs() {
        return adminJobs;
    }

    /**
     * @param aAdminJobs the adminJobs to set
     */
    public static void setAdminJobs(AdminJobs aAdminJobs) {
        adminJobs = aAdminJobs;
    }

    /**
     * @return the adminPetsJobs
     */
    public static AdminPetsJobs getAdminPetsJobs() {
        return adminPetsJobs;
    }

    /**
     * @param aAdminPetsJobs the adminPetsJobs to set
     */
    public static void setAdminPetsJobs(AdminPetsJobs aAdminPetsJobs) {
        adminPetsJobs = aAdminPetsJobs;
    }
    
    /**
     * @return the petCreator
     */
    public static PetCreator getPetCreator() {
        return petCreator;
    }

    /**
     * @param aPetCreator the petCreator to set
     */
    public static void setPetCreator(PetCreator aPetCreator) {
        petCreator = aPetCreator;
    }

    /**
     * @return the petChooser
     */
    public static PetChooser getPetChooser() {
        return petChooser;
    }

    /**
     * @param aPetChooser the petChooser to set
     */
    public static void setPetChooser(PetChooser aPetChooser) {
        petChooser = aPetChooser;
    }

    /**
     * @return the currentPet
     */
    public static Pet getCurrentPet() {
        return currentPet;
    }

    /**
     * @param aCurrentPet the currentPet to set
     */
    public static void setCurrentPet(Pet aCurrentPet) {
        currentPet = aCurrentPet;
    }

    /**
     * @return the timeModifier
     */
    public static float getTimeModifier() {
        return timeModifier;
    }

    /**
     * @param aTimeModifier the timeModifier to set
     */
    public static void setTimeModifier(float aTimeModifier) {
        timeModifier = aTimeModifier;
    }
    
    public static void petLeaves() {
        petWindow.petLeaves();
        if(RaiseMeUp.getJobsWindow()!=null) RaiseMeUp.getJobsWindow().setVisible(false);
        if(RaiseMeUp.getMarketWindow()!=null) RaiseMeUp.getMarketWindow().setVisible(false);
        RaiseMeUp.setErrorMessage(new ErrorMessage("You have been a bad master, " + currentPet.getName() + " left you."));
        RaiseMeUp.getErrorMessage().setVisible(true);
        try {
            dao.delPet(currentPet);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void deletePet(Pet pet) {
        try {
            dao.delPet(pet);
        } catch (SQLException ex) {
            Logger.getLogger(RaiseMeUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void modifyPet() {
        
    }

    /**
     * @return the adminTiming
     */
    public static AdminTiming getAdminTiming() {
        return adminTiming;
    }

    /**
     * @param aAdminTiming the adminTiming to set
     */
    public static void setAdminTiming(AdminTiming aAdminTiming) {
        adminTiming = aAdminTiming;
    }

    /**
     * @return the jobsWindow
     */
    public static JobsWindow getJobsWindow() {
        return jobsWindow;
    }

    /**
     * @param aJobsWindow the jobsWindow to set
     */
    public static void setJobsWindow(JobsWindow aJobsWindow) {
        jobsWindow = aJobsWindow;
    }

    /**
     * @return the onJob
     */
    public static boolean isOnJob() {
        return onJob;
    }

    /**
     * @param aOnJob the onJob to set
     */
    public static void setOnJob(boolean aOnJob) {
        onJob = aOnJob;
    }

    /**
     * @return the jobprogress
     */
    public static int getJobprogress() {
        return jobprogress;
    }

    /**
     * @param aJobprogress the jobprogress to set
     */
    public static void setJobprogress(int aJobprogress) {
        jobprogress = aJobprogress;
    }

    /**
     * @return the jobDone
     */
    public static JobDone getJobDone() {
        return jobDone;
    }

    /**
     * @param aJobDone the jobDone to set
     */
    public static void setJobDone(JobDone aJobDone) {
        jobDone = aJobDone;
    }

    /**
     * @return the marketWindow
     */
    public static MarketWindow getMarketWindow() {
        return marketWindow;
    }

    /**
     * @param aMarketWindow the marketWindow to set
     */
    public static void setMarketWindow(MarketWindow aMarketWindow) {
        marketWindow = aMarketWindow;
    }

    /**
     * @return the userSettings
     */
    public static UserSettings getUserSettings() {
        return userSettings;
    }

    /**
     * @param aUserSettings the userSettings to set
     */
    public static void setUserSettings(UserSettings aUserSettings) {
        userSettings = aUserSettings;
    }
    
    
}
