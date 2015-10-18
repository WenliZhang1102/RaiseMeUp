/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raisemeup.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import raisemeup.model.beans.Food;
import raisemeup.model.beans.Item;
import raisemeup.model.beans.Job;
import raisemeup.model.beans.Pet;
import raisemeup.model.beans.Upgrade;
import raisemeup.model.beans.User;

/**
 *
 * @author lekogabor
 */
public class DAO {
    
    Map<Integer, User> users = new HashMap<Integer, User>();
    Map<Integer, Pet> pets = new HashMap<Integer, Pet>();
    Map<Integer, Food> foods = new HashMap<Integer, Food>();
    Map<Integer, Upgrade> upgrades = new HashMap<Integer, Upgrade>();
    Map<Integer, Job> jobs = new HashMap<Integer, Job>();
    
    private static final String dbfile = "C:/db/raise.db";
    
    private static final String SQL_addUser = 
		"insert into User (email, username, password) values " +
		"(?,?,?)";
    private static final String SQL_queryUser = "select * from User where username = ?";
    private static final String SQL_delUser = "delete from User where username = ?";
    private static final String SQL_listUsers = "select * from User";
    
    
    private static final String SQL_addPet = 
		"insert into Pet (type, variant, name, hunger, energy, fun, hygiene, age, money, image, userid) values " +
		"(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_addPetsFoods = "insert into FoodOwned (petid, foodid, piece) values (?, ?, ?)";
    private static final String SQL_addPetsUpgrades = "insert into UpgradeOwned (petid, upgradeid, piece) values (?, ?, ?)";
    private static final String SQL_queryPet = "select * from Pet where name = ? and userid = ?";
    private static final String SQL_delPet = "delete from Pet where userid = ? and name = ?";
    private static final String SQL_listPets = "select * from Pet";
    private static final String SQL_listPetsFoods = ""; // Megvalositva a getPets()-en belul
    private static final String SQL_listPetsUpgrades = ""; // Megvalositva a getPets()-en belul
    
    
    private static final String SQL_addFood = 
                "insert into Food (name, price, valuedog, valuecat, valuefish, valuepenguin, image ) values " +
                "(?,?,?,?,?,?,?)";
    private static final String SQL_queryFood = "select * from Food where name = ?";
    private static final String SQL_delFood = "delete from Food where name = ?";
    private static final String SQL_listFoods = "select * from Food";
    
    
    private static final String SQL_addUpgrade = 
                "insert into Upgrade (name, price, property, species, value, image) values " +
                "(?,?,?,?,?,?)";
    private static final String SQL_queryUpgrade = "select * from Upgrade where name = ?";
    private static final String SQL_delUpgrade = "delete from Upgrade where name = ?";
    private static final String SQL_listUpgrades = "select * from Upgrade";
    
    private static final String SQL_addJob = 
                "insert into Job (impactenergy, impacthunger, impacthygiene, impactfun, title, image, client, message, petid) values " +
                "(?,?,?,?,?,?,?,?,?)";
    private static final String SQL_queryJob = "select * from Job where title = ? and petid = ?";
    private static final String SQL_delJob = "delete from Job where title = ? and petid = ?";
    private static final String SQL_listJobs = "select * from Job";
    
    
    //insert into Pet (type, variant, name, hunger, energy, fun, hygiene, age, image, userid) values ("cica","szep","juci",100,100,100,100,0,"",1);
    //insert into User (email, name, username, password) values ("geabei25@gmail.com","Leko Gabor", "gale", "123" );
    
    public DAO() throws ClassNotFoundException{
		Class.forName("org.sqlite.JDBC");
	}
    
    public boolean addUser(User u) throws SQLException{
		
                System.out.println("jdbc:sqlite:"+dbfile);
		if(checkIfUserExists(u))
			return false;

		Connection conn = null;
		PreparedStatement pst = null;
		try {
                        
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			pst = conn.prepareStatement(SQL_addUser);
			int index = 1;
			pst.setString(index++, u.getEmail());
                        pst.setString(index++, u.getUsername());
			pst.setString(index++, u.getPassword());
			
			pst.executeUpdate();
		} finally {
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
    
    public Map<Integer, User> getUser() throws SQLException{
		Connection conn = null;
		Statement st = null;
		//Toroljuk a memoriabol a customereket (azert tartjuk bennt, mert lehetnek kesobb olyan muveletek, melyekhez nem kell frissiteni)
		users.clear();
		
		try {
			//Az adatb�zis kapcsolatunkat a DriverManager seg�ts�g�vel hozzuk l�tre
			//Megadjuk hogy a jdbc milyen driveren keresztul milyen f�jlt keressen
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			
			//a kapcsolat(conn) objektumtol kerunk egy egyszeru (nem parameterezheto) utasitast
			st = conn.createStatement();
			//Az utasitas objektumon keresztul inditunk egy queryt(SQL_listCustomers)
			//Az eredmenyeket egy ResultSet objektumban kapjuk vissza
			ResultSet rs = st.executeQuery(SQL_listUsers);
			//Bejarjuk a visszakapott ResultSet-et (ami a customereket fogja tartalmazni)
			while(rs.next()){
				//Uj Customert hozunk letre
				User u = new User();
				//A customer nevet a resultSet aktualis soraban talalhato name rekordra allitjuk be
				
                                u.setId(rs.getInt("userid"));
                                u.setEmail(rs.getString("email"));
				//A customer korat a resultSet aktualis soraban talalhato age rekordra allitjuk be
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				
				users.put(rs.getInt("userid"), u);
			}
		} finally {
			try {
				if(st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return users;
	}
    
    public boolean checkIfUserExists(User u) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			
			pst = conn.prepareStatement(SQL_queryUser);
			int index = 1;
			pst.setString(index++, u.getUsername());
			rs = pst.executeQuery();
			
			if (rs.next())
				return true;
			return false;			
		} finally {
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	

    public boolean delUser(User u) throws SQLException{
		
                System.out.println("jdbc:sqlite:"+dbfile);

		Connection conn = null;
		PreparedStatement pst = null;
		try {
                        
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			pst = conn.prepareStatement(SQL_delUser);
			int index = 1;
			pst.setString(index++, u.getUsername());
                        
			pst.executeUpdate();
		} finally {
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}


    public boolean addPet(Pet p) throws SQLException{
		
                System.out.println("jdbc:sqlite:"+dbfile);
		if(checkIfPetExists(p))
			return false;

		Connection conn = null;
		PreparedStatement pst = null;
                Connection conn2 = null;
		PreparedStatement pst2 = null;
                Connection conn3 = null;
		PreparedStatement pst3 = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			pst = conn.prepareStatement(SQL_addPet);
			int index = 1;
			pst.setString(index++, p.getType());
                        pst.setString(index++, p.getVariant());
                        pst.setString(index++, p.getName());
                        pst.setInt(index++, p.getHunger());
                        pst.setInt(index++, p.getEnergy());
                        pst.setInt(index++, p.getFun());
                        pst.setInt(index++, p.getHygiene());
                        pst.setInt(index++, p.getAge());
                        pst.setInt(index++, p.getMoney());
                        pst.setString(index++, p.getImage());
                        pst.setInt(index++, p.getOwner());
			
			pst.executeUpdate();
                        
                        conn2 = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			pst2 = conn2.prepareStatement(SQL_addPetsFoods);
                        conn3 = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			pst3 = conn3.prepareStatement(SQL_addPetsUpgrades);
                        
			
                        int index2 = 1;
                        for (Map.Entry<Item, Integer> i : p.getOwneditems().entrySet()){
                            index2 = 1;
                            if (i instanceof Food){
                                pst2.setInt(index2++, p.getPetid());
                                pst2.setInt(index2++, i.getKey().getId());
                                pst2.setInt(index2++, i.getValue());
                                pst2.executeUpdate();
                            } else {
                                pst3.setInt(index2++, p.getPetid());
                                pst3.setInt(index2++, i.getKey().getId());
                                pst3.setInt(index2++, i.getValue());
                                pst3.executeUpdate();
                            }
                        }
                        
                        
		} finally {
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
                        try {
				if(pst2 != null)
					pst2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn2 != null)
					conn2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
                        try {
				if(pst3 != null)
					pst3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn3 != null)
					conn3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
    
    public Map<Integer, Pet> getPet() throws SQLException{
		Connection conn = null;
		Statement st = null;
                Connection conn2 = null;
		Statement st2 = null;
                Connection conn3 = null;
		Statement st3 = null;
		//Toroljuk a memoriabol a customereket (azert tartjuk bennt, mert lehetnek kesobb olyan muveletek, melyekhez nem kell frissiteni)
		pets.clear();
		
		try {
			//Az adatb�zis kapcsolatunkat a DriverManager seg�ts�g�vel hozzuk l�tre
			//Megadjuk hogy a jdbc milyen driveren keresztul milyen f�jlt keressen
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			
			//a kapcsolat(conn) objektumtol kerunk egy egyszeru (nem parameterezheto) utasitast
			st = conn.createStatement();
			//Az utasitas objektumon keresztul inditunk egy queryt(SQL_listCustomers)
			//Az eredmenyeket egy ResultSet objektumban kapjuk vissza
			ResultSet rs = st.executeQuery(SQL_listPets);
			//Bejarjuk a visszakapott ResultSet-et (ami a customereket fogja tartalmazni)
			while(rs.next()){
				//Uj Customert hozunk letre
				Pet p = new Pet();
				//A customer nevet a resultSet aktualis soraban talalhato name rekordra allitjuk be
				p.setPetid(rs.getInt("petid"));
                                p.setType(rs.getString("type"));
                                p.setVariant(rs.getString("variant"));
                                p.setName(rs.getString("name"));
                                p.setHunger(rs.getInt("hunger"));
                                p.setEnergy(rs.getInt("energy"));
                                p.setFun(rs.getInt("fun"));
                                p.setHygiene(rs.getInt("hygiene"));
                                p.setAge(rs.getInt("age"));
                                p.setMoney(rs.getInt("money"));
                                p.setImage(rs.getString("image"));
                                p.setOwner(rs.getInt("userid"));
				
                                
                                conn2 = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
                                st2 = conn2.createStatement();
                                ResultSet rs2 = st2.executeQuery("select Food.foodid, name, price, valuedog, valuecat, valuefish, valuepenguin, image, piece from Food, FoodOwned where FoodOwned.petid = " + rs.getInt("petid") + " and Food.foodid = FoodOwned.foodid");
                                while(rs2.next()){
                                    Food f = new Food();
                                    f.setId(rs2.getInt("foodid"));
                                    f.setName(rs2.getString("name"));
                                    f.setPrice(rs2.getInt("price"));
                                    f.setValuedog(rs2.getInt("valuedog"));
                                    f.setValuecat(rs2.getInt("valuecat"));
                                    f.setValuefish(rs2.getInt("valuefish"));
                                    f.setValuepenguin(rs2.getInt("valuepenguin"));
                                    f.setImage(rs2.getString("image"));
                                    
                                    p.getOwneditems().put(f, rs2.getInt("piece"));
                                }
                                
                                conn3 = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
                                st3 = conn3.createStatement();
                                ResultSet rs3 = st3.executeQuery("select Upgrade.upgradeid, name, price, property, species, value, image, piece from Upgrade, UpgradeOwned where UpgradeOwned.petid = " + rs.getInt("petid") + " and Upgrade.upgradeid = UpgradeOwned.upgradeid");

                                while(rs3.next()){
                                    Upgrade up = new Upgrade();
                                    up.setId(rs3.getInt("upgradeid"));
                                    up.setName(rs3.getString("name"));
                                    up.setPrice(rs3.getInt("price"));
                                    up.setProperty(rs3.getString("property"));
                                    up.setSpecies(rs3.getString("species"));
                                    up.setValue(rs3.getInt("value"));
                                    up.setImage(rs3.getString("image"));
                                    
                                    p.getOwneditems().put(up, rs3.getInt("piece"));
                                }
                                
				pets.put(rs.getInt("petid"), p);
			}
		} finally {
			try {
				if(st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
                        try {
				if(st2 != null)
					st2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn2 != null)
					conn2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
                        try {
				if(st3 != null)
					st3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn3 != null)
					conn3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return pets;
	}
    
    public boolean checkIfPetExists(Pet p) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			
			pst = conn.prepareStatement(SQL_queryPet);
			int index = 1;
			
                        pst.setString(index++, p.getName());
			pst.setInt(index++, p.getOwner());
                        rs = pst.executeQuery();
			
			if (rs.next())
				return true;
			return false;			
		} finally {
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	

    public boolean delPet(Pet p) throws SQLException{
		
                System.out.println("jdbc:sqlite:"+dbfile);

		Connection conn = null;
		PreparedStatement pst = null;
		try {
                        
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			pst = conn.prepareStatement(SQL_delPet);
			int index = 1;
			pst.setInt(index++, p.getOwner());
                        pst.setString(index++, p.getName());
                        
			pst.executeUpdate();
		} finally {
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

    
    public boolean addFood(Food f) throws SQLException{
		
                System.out.println("jdbc:sqlite:"+dbfile);
		if(checkIfFoodExists(f))
			return false;

		Connection conn = null;
		PreparedStatement pst = null;
		try {
                        
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			pst = conn.prepareStatement(SQL_addFood);
			int index = 1;
			pst.setString(index++, f.getName());
                        pst.setInt(index++, f.getPrice());
                        pst.setInt(index++, f.getValuedog());
                        pst.setInt(index++, f.getValuecat());
                        pst.setInt(index++, f.getValuefish());
                        pst.setInt(index++, f.getValuepenguin());
                        pst.setString(index++, f.getImage());
                        			
			pst.executeUpdate();
		} finally {
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
    
    public Map<Integer, Food> getFood() throws SQLException{
		Connection conn = null;
		Statement st = null;
		//Toroljuk a memoriabol a customereket (azert tartjuk bennt, mert lehetnek kesobb olyan muveletek, melyekhez nem kell frissiteni)
		foods.clear();
		
		try {
			//Az adatb�zis kapcsolatunkat a DriverManager seg�ts�g�vel hozzuk l�tre
			//Megadjuk hogy a jdbc milyen driveren keresztul milyen f�jlt keressen
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			
			//a kapcsolat(conn) objektumtol kerunk egy egyszeru (nem parameterezheto) utasitast
			st = conn.createStatement();
			//Az utasitas objektumon keresztul inditunk egy queryt(SQL_listCustomers)
			//Az eredmenyeket egy ResultSet objektumban kapjuk vissza
			ResultSet rs = st.executeQuery(SQL_listFoods);
			//Bejarjuk a visszakapott ResultSet-et (ami a customereket fogja tartalmazni)
			while(rs.next()){
				//Uj Customert hozunk letre
				Food f = new Food();
				//A customer nevet a resultSet aktualis soraban talalhato name rekordra allitjuk be
                                f.setId(rs.getInt("foodid"));
                                f.setName(rs.getString("name"));
                                f.setPrice(rs.getInt("price"));
                                f.setValuedog(rs.getInt("valuedog"));
                                f.setValuecat(rs.getInt("valuecat"));
                                f.setValuefish(rs.getInt("valuefish"));
                                f.setValuepenguin(rs.getInt("valuepenguin"));
                                f.setImage(rs.getString("image"));
                                				
				foods.put(rs.getInt("foodid"), f);
			}
		} finally {
			try {
				if(st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return foods;
	}
    
    public boolean checkIfFoodExists(Food f) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			
			pst = conn.prepareStatement(SQL_queryFood);
			int index = 1;
			pst.setString(index++, f.getName());
			rs = pst.executeQuery();
			
			if (rs.next())
				return true;
			return false;			
		} finally {
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	

    public boolean delFood(Food f) throws SQLException{
		
                System.out.println("jdbc:sqlite:"+dbfile);

		Connection conn = null;
		PreparedStatement pst = null;
		try {
                        
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			pst = conn.prepareStatement(SQL_delFood);
			int index = 1;
			pst.setString(index++, f.getName());
                        
			pst.executeUpdate();
		} finally {
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
    
    
    public boolean addUpgrade(Upgrade up) throws SQLException{
		
                System.out.println("jdbc:sqlite:"+dbfile);
		if(checkIfUpgradeExists(up))
			return false;

		Connection conn = null;
		PreparedStatement pst = null;
		try {
                        
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			pst = conn.prepareStatement(SQL_addUpgrade);
			int index = 1;
                        pst.setString(index++, up.getName());
                        pst.setInt(index++, up.getPrice());
                        pst.setString(index++, up.getProperty());
                        pst.setString(index++, up.getSpecies());
                        pst.setInt(index++, up.getValue());
                        pst.setString(index++, up.getImage());
                        
			pst.executeUpdate();
		} finally {
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
    
    public Map<Integer, Upgrade> getUpgrade() throws SQLException{
		Connection conn = null;
		Statement st = null;
		//Toroljuk a memoriabol a customereket (azert tartjuk bennt, mert lehetnek kesobb olyan muveletek, melyekhez nem kell frissiteni)
		upgrades.clear();
		
		try {
			//Az adatb�zis kapcsolatunkat a DriverManager seg�ts�g�vel hozzuk l�tre
			//Megadjuk hogy a jdbc milyen driveren keresztul milyen f�jlt keressen
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			
			//a kapcsolat(conn) objektumtol kerunk egy egyszeru (nem parameterezheto) utasitast
			st = conn.createStatement();
			//Az utasitas objektumon keresztul inditunk egy queryt(SQL_listCustomers)
			//Az eredmenyeket egy ResultSet objektumban kapjuk vissza
			ResultSet rs = st.executeQuery(SQL_listUpgrades);
			//Bejarjuk a visszakapott ResultSet-et (ami a customereket fogja tartalmazni)
			while(rs.next()){
				//Uj Customert hozunk letre
				Upgrade up = new Upgrade();
				//A customer nevet a resultSet aktualis soraban talalhato name rekordra allitjuk be
                                up.setId(rs.getInt("upgradeid"));
                                up.setName(rs.getString("name"));
                                up.setPrice(rs.getInt("price"));
                                up.setProperty(rs.getString("property"));
                                up.setSpecies(rs.getString("species"));
                               	up.setValue(rs.getInt("value"));
                                up.setImage(rs.getString("image"));
                                
				upgrades.put(rs.getInt("upgadeid"), up);
			}
		} finally {
			try {
				if(st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return upgrades;
	}
    
    public boolean checkIfUpgradeExists(Upgrade up) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			
			pst = conn.prepareStatement(SQL_queryUpgrade);
			int index = 1;
			pst.setString(index++, up.getName());
			rs = pst.executeQuery();
			
			if (rs.next())
				return true;
			return false;			
		} finally {
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	

    public boolean delUpgrade(Upgrade up) throws SQLException{
		
                System.out.println("jdbc:sqlite:"+dbfile);

		Connection conn = null;
		PreparedStatement pst = null;
		try {
                        
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			pst = conn.prepareStatement(SQL_delUpgrade);
			int index = 1;
			pst.setString(index++, up.getName());
                        
			pst.executeUpdate();
		} finally {
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
 
    
    public boolean addJob(Job j) throws SQLException{
		
                System.out.println("jdbc:sqlite:"+dbfile);
		if(checkIfJobExists(j))
			return false;

		Connection conn = null;
		PreparedStatement pst = null;
		try {
                        
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			pst = conn.prepareStatement(SQL_addJob);
			int index = 1;
                        pst.setInt(index++, j.getImpactenergy());
                        pst.setInt(index++, j.getImpacthunger());
                        pst.setInt(index++, j.getImpacthygiene());
                        pst.setInt(index++, j.getImpactfun());
                        pst.setString(index++, j.getTitle());
                        pst.setString(index++, j.getImage());
                        pst.setString(index++, j.getClient());
                        pst.setString(index++, j.getMessage());
                        pst.setInt(index++, j.getPetid());
                        
			pst.executeUpdate();
		} finally {
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
    
    public Map<Integer, Job> getJob() throws SQLException{
		Connection conn = null;
		Statement st = null;
		//Toroljuk a memoriabol a customereket (azert tartjuk bennt, mert lehetnek kesobb olyan muveletek, melyekhez nem kell frissiteni)
		jobs.clear();
		
		try {
			//Az adatb�zis kapcsolatunkat a DriverManager seg�ts�g�vel hozzuk l�tre
			//Megadjuk hogy a jdbc milyen driveren keresztul milyen f�jlt keressen
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			
			//a kapcsolat(conn) objektumtol kerunk egy egyszeru (nem parameterezheto) utasitast
			st = conn.createStatement();
			//Az utasitas objektumon keresztul inditunk egy queryt(SQL_listCustomers)
			//Az eredmenyeket egy ResultSet objektumban kapjuk vissza
			ResultSet rs = st.executeQuery(SQL_listJobs);
			//Bejarjuk a visszakapott ResultSet-et (ami a customereket fogja tartalmazni)
			while(rs.next()){
				//Uj Customert hozunk letre
				Job j = new Job();
				//A customer nevet a resultSet aktualis soraban talalhato name rekordra allitjuk be
				j.setId(rs.getInt("id"));
                                j.setImpactenergy(rs.getInt("impactenergy"));
                                j.setImpacthunger(rs.getInt("impacthunger"));
                                j.setImpacthygiene(rs.getInt("impacthygiene"));
                                j.setImpactfun(rs.getInt("impactfun"));
                                j.setTitle(rs.getString("title"));
                                j.setImage(rs.getString("image"));
                                j.setClient(rs.getString("client"));
                                j.setMessage(rs.getString("message"));
                                j.setPetid(rs.getInt("petid"));
                                
				jobs.put(rs.getInt("id"), j);
			}
		} finally {
			try {
				if(st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return jobs;
	}
    
    public boolean checkIfJobExists(Job j) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			
			pst = conn.prepareStatement(SQL_queryJob);
			int index = 1;
			pst.setString(index++, j.getTitle());
                        pst.setInt(index++, j.getPetid());
			rs = pst.executeQuery();
			
			if (rs.next())
				return true;
			return false;			
		} finally {
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	

    public boolean delJob(Job j) throws SQLException{
		
                System.out.println("jdbc:sqlite:"+dbfile);

		Connection conn = null;
		PreparedStatement pst = null;
		try {
                        
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			pst = conn.prepareStatement(SQL_delJob);
			int index = 1;
			pst.setString(index++, j.getTitle());
                        pst.setInt(index++, j.getPetid());
                        
			pst.executeUpdate();
		} finally {
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

    
    
}
